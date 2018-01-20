package me.peproll.sqlite

import scala.scalajs.js
import scala.scalajs.js.Any
import scala.scalajs.js.annotation.ScalaJSDefined

@js.native
trait Statement extends js.Object {

  val source: String = js.native
  val returnsData: Boolean = js.native

  def run(bindParameters: Any*): Info = js.native
  def get[T <: js.Any](bindParameters: Any*): js.UndefOr[T] = js.native
  def all[T <: js.Any](bindParameters: Any*): js.Array[T] = js.native
  def iterate[T <: js.Any](bindParameters: Any*): js.Iterator[T] = js.native
  def pluck(toggleState: Boolean): Statement = js.native
  def bind(bindParameters: Any*): Statement = js.native
}

@ScalaJSDefined
class FunctionOptions(name: String, deterministic: Boolean, varargs: Boolean)
  extends js.Object