package me.peproll.sqlite.migrations

import me.peproll.sqlite.Database

import scala.async.Async._
import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

private[sqlite] object Migrations {

  def migrate(db: Database, force: Boolean, table: String, migrationsPath: String): Future[Unit] =
    async {

      val migrations = await(readMigrations(migrationsPath)).sortBy(_.id)

      db.exec(s"""
         |CREATE TABLE IF NOT EXISTS "$table" (
         |  id   INTEGER PRIMARY KEY,
         |  name TEXT    NOT NULL,
         |  up   TEXT    NOT NULL,
         |  down TEXT    NOT NULL
         |)
      """.stripMargin)

      val lastMigrationId = db
        .prepare(s"""
         |SELECT id FROM "$table" ORDER BY id ASC
      """.stripMargin)
        .get[MigrationId]()
        .map(_.id)
        .getOrElse(0)


      migrations filter (_.id > lastMigrationId) foreach { migration =>
        db.transaction(() => {
          db.exec(migration.up)
          db.prepare(
            s"""
INSERT INTO "$table" (id, name, up, down) VALUES (?, ?, ?, ?)
            """.stripMargin)
            .run(
              migration.id,
              migration.name,
              migration.up,
              migration.down
            )
        })
      }
    }

  @ScalaJSDefined
  private final class MigrationId(val id: Int) extends js.Object

  private case class MigrationRecord(id: Int, name: String, up: String = "", down: String = "")

  private val MigrationFormat = """^((\d+).(.*)\.sql)$""".r

  private def readMigrations(migrationsPath: String): Future[Seq[MigrationRecord]] = {

    def readMigrationRecord(id: Int, name: String, filename: String): Future[MigrationRecord] =
      Fs.readFileFuture(Path.join(migrationsPath, filename), "UTF-8")
        .map {
          _.split("(?mi)^--\\s+?down\\b", 2) match {
            case Array(up, down) =>
              MigrationRecord(
                id.toInt,
                name,
                up.replaceAll("(?mi)^-- .*?$", "").trim(), // Remove comments
                down.trim()
              )
            case _ =>
              throw new Exception(s"The $filename file does not contain '-- Down' separator")
          }
        }

    for {

      files <- Fs.readdirFuture(Path.resolve(migrationsPath))

      recordFutures = files
        .collect({
          case MigrationFormat(filename, id, name) if id.forall(_.isDigit) =>
            readMigrationRecord(id.toInt, name, filename)
        })
        .toSeq

      _ = if (recordFutures.isEmpty)
        Future.failed(new Exception(s"Migrations not found in '$migrationsPath'"))

      record <- Future.sequence(recordFutures)

    } yield record
  }

}
