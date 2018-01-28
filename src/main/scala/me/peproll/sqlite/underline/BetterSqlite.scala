package me.peproll.sqlite.underline

import me.peproll.sqlite.ConnectionOptions

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("better-sqlite3", JSImport.Namespace, "better-sqlite3")
@js.native
private[sqlite] object BetterSqlite extends js.Object {
  def apply(path: String, options: ConnectionOptions = js.native): Database = js.native
}
