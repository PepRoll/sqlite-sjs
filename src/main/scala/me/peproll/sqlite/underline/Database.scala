package me.peproll.sqlite.underline

import me.peproll.sqlite._

import scala.scalajs.js

@js.native
private[sqlite] trait Database extends js.Object {

  val open: Boolean = js.native
  val inTransaction: Boolean = js.native
  val name: String = js.native
  val memory: Boolean = js.native
  val readonly: Boolean = js.native

  def prepare(sql: String): Statement = js.native
  def transaction(sql: js.Array[String]): Transaction = js.native
  def pragma(sql: String, simplify: Boolean = js.native): js.Object = js.native
  def checkpoint(databaseName: String = js.native): Database = js.native
  def register(options: FunctionOptions = js.native, function: js.Function): Database = js.native
  def loadExtension(path: String): Database = js.native
  def exec(sql: String): Database = js.native
  def close(): Database = js.native
}
