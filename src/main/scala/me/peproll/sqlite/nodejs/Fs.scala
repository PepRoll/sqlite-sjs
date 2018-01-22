package me.peproll.sqlite.nodejs

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
trait Fs extends js.Object {
  def readdir(path: String, callback: js.Function2[SystemError, js.Array[String], Any]): Unit = js.native
  def readFile(file: String, encoding: String, callback: js.Function2[SystemError, String, Any]): Unit =
    js.native
}

@js.native
@JSImport("fs", JSImport.Namespace)
object Fs extends Fs
