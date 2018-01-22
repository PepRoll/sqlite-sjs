package me.peproll.sqlite

import me.peproll.sqlite.nodejs.{Fs, Path}

import scala.concurrent.Future
import scala.async.Async._
import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

object Migration {

  private val MigrationFormat = """^(\\d+).(.*?)\\.sql$""".r

  def migrate(force: Boolean, table: String, migrationsPath: String): Future[Unit] = async {

    val migrations = await(readMigrations(migrationsPath))


  }

  private def readMigrations(migrationsPath: String): Future[Seq[MigrationFile]] =
    for {

      rawFiles <- Fs.readdirFuture(Path.resolve(migrationsPath))

      file <- Future.traverse {

      }

        rawFiles
        .collect({
          case MigrationFormat(id, name, filename) if id.forall(_.isDigit) => async {

            val file = await(Fs.readFileFuture(
              Path.join(migrationsPath, filename), "UTF-8")
            )

            val (up, down) = file.


          }
        })

      if migrations.nonEmpty

      file <- Future.traverse(migrations) { migration =>
        Fs.readFileFuture(Path.join(migrationsPath, migration.filename))
      }
    } yield file

}

@ScalaJSDefined
case class MigrationFile(id: Int,
                         name: String,
                         filename: String,
                         up: String = "",
                         down: String = "") extends js.Object
