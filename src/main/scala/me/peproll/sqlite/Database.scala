package me.peproll.sqlite

import me.peproll.sqlite.migrations.Migrations

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel, ScalaJSDefined}
import me.peproll.sqlite.{underline => u}

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

class Database private[sqlite] (underline: u.Database) {

  @JSExport
  def open: Boolean = underline.open

  @JSExport
  def inTransaction: Boolean = underline.inTransaction

  @JSExport
  def name: String = underline.name

  @JSExport
  def memory: Boolean = underline.memory

  @JSExport
  def readonly: Boolean = underline.readonly

  @JSExport
  def prepare(sql: String): Statement = underline.prepare(sql)

  @JSExport
  def transactionStatements(sql: js.Array[String]): Transaction = underline.transaction(sql)

  @JSExport
  def pragma(sql: String, simplify: Boolean): js.Object = underline.pragma(sql, simplify)

  @JSExport
  def checkpoint(databaseName: String): Database = {
    underline.checkpoint(databaseName)
    this
  }

  @JSExport
  def register(options: FunctionOptions, function: js.Function): Database = {
    underline.register(options, function)
    this
  }

  @JSExport
  def loadExtension(path: String): Database = {
    underline.loadExtension(path)
    this
  }

  @JSExport
  def exec(sql: String): Database = {
    underline.exec(sql)
    this
  }

  @JSExport
  def close(): Database = {
    underline.close()
    this
  }

  @JSExport
  def migrate(force: Boolean = false,
              table: String = "migrations",
              migrationsPath: String = "./migrations"): js.Promise[Unit] =
    Migrations.migrate(this, force, table, migrationsPath).toJSPromise

  @JSExport
  def transaction(function: js.Function0[js.Any]): Unit = {
    exec("BEGIN TRANSACTION")
    try {
      function()
      exec("COMMIT")
      ()
    } catch {
      case e: Throwable =>
        exec("ROLLBACK")
        throw e
    }
  }
}
