package me.peproll.sqlite

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class ConnectionOptions(val memory: Boolean = false,
                        val readonly: Boolean = false,
                        val fileMustExist: Boolean = false)
    extends js.Object
