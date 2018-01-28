package me.peproll.sqlite

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class ConnectionOptions(memory: Boolean = false,
                        readonly: Boolean = false,
                        fileMustExist: Boolean = false)
    extends js.Object
