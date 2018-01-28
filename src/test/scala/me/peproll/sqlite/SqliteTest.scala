package me.peproll.sqlite

import me.peproll.sqlite.migrations.Fs
import utest._

import scala.async.Async._
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js

object SqliteTest extends TestSuite {

  val testPath = "target/temp.db"
  val migrationsPath = "./src/test/resources"

  val tests = Tests {

    def initDb(): Database = {
      Fs.unlinkSync(testPath)
      Sqlite(testPath, new ConnectionOptions(memory = true))
    }

    "create table" - {
      val db = initDb()
      db.exec("""
          |CREATE TABLE IF NOT EXISTS test (
          |id INTEGER PRIMARY KEY,
          |name TEXT NOT NULL)
        """.stripMargin)

      val statement = db.prepare("SELECT name FROM sqlite_master WHERE type='table' AND name=?")
      val response = statement.get[js.Object]("test").toOption
      assert(response.isDefined)
      db.close()
    }

    "migrations" - async {
      val db = initDb()
      await(db.migrate(migrationsPath = migrationsPath).toFuture)
      val smt = db.prepare("SELECT name FROM sqlite_master WHERE type='table' AND name in (?, ?, ?)")
      val result = smt.all[js.Object]("Post", "Category", "Test")
      assert(result.length == 3)
      db.close()
    }

  }

}