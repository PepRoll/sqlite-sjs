package me.peproll.sqlite

import scala.scalajs.js

@js.native
trait Transaction extends js.Object {

  val source: String = js.native

  def run(bindParameters: Any*): Info = js.native
  def bind(bindParameters: Any*): Transaction = js.native
}