package me.peproll.sqlite.nodejs

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@js.native
@JSImport("errors", "SystemError", "errors.SystemError")
class SystemError(message0: String = js.native) extends Error(message0) {

  val code: String = js.native
  val errno: String | Int = js.native
  val syscall: String = js.native
  val path: String = js.native
  val address: String = js.native
  val port: Int = js.native
}

@js.native
@JSImport("errors", JSImport.Namespace, "errors")
object SystemError extends Error
