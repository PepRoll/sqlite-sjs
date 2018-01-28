package me.peproll.sqlite.migrations

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
trait Path extends js.Object {
  def resolve(args: String*): String = js.native
  def join(path1: String, path2: String*): String = js.native
}

@js.native
@JSImport("path", JSImport.Namespace, "path")
object Path extends Path
