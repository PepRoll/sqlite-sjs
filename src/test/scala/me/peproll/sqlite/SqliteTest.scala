package me.peproll.sqlite

import me.peproll.sqlite.underline.Sqlite
import utest._

import scala.scalajs.js

object SqliteTest extends TestSuite {

  val testPath = "target/temp.db"

  val tests = Tests {

    val db = Sqlite(testPath, new ConnectionOptions(memory = true))

    "create table" - {
      db.exec(
        """
          |CREATE TABLE IF NOT EXISTS test (
          |id INTEGER PRIMARY KEY,
          |name TEXT NOT NULL)
        """.stripMargin)

      val statement = db.prepare("SELECT name FROM sqlite_master WHERE type='table' AND name=?")
      val response = statement.get[js.Object]("test").toOption
      assert(response.isDefined)
    }

  }

}