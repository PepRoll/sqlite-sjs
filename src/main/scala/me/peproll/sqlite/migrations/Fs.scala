package me.peproll.sqlite.migrations

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
trait Fs extends js.Object {
  def readdir(path: String, callback: js.Function2[js.Error, js.Array[String], Any]): Unit = js.native
  def readFile(file: String, encoding: String, callback: js.Function2[js.Error, String, Any]): Unit =
    js.native
  def unlinkSync(path: String): Unit = js.native
}

@js.native
@JSImport("fs", JSImport.Namespace, "fs")
object Fs extends Fs