package me.peproll.sqlite

import me.peproll.sqlite.underline.BetterSqlite

import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("sqlite-sjs")
object Sqlite {

  def apply(path: String, options: ConnectionOptions): Database =
    new Database(BetterSqlite(path, options))

}
