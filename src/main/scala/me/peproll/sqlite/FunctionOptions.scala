package me.peproll.sqlite

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class FunctionOptions(val name: String, val deterministic: Boolean, val varargs: Boolean)
  extends js.Object
