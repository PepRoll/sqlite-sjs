package me.peproll.sqlite

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("better-sqlite3", JSImport.Namespace, "sqlite")
@js.native
object Sqlite extends js.Object {
  def apply(path: String,
            options: ConnectionOptions = js.native): Database = js.native
}