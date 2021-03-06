package me.peproll.sqlite.migrations

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
private[migrations] trait Fs extends js.Object {
  def readdir(path: String, callback: js.Function2[js.Error, js.Array[String], Any]): Unit =
    js.native

  def readFile(file: String,
               encoding: String,
               callback: js.Function2[js.Error, String, Any]): Unit =
    js.native
}

@js.native
@JSImport("fs", JSImport.Namespace, "fs")
private[migrations] object Fs extends Fs
