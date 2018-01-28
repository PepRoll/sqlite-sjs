package me.peproll.sqlite

import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.scalajs.runtime.wrapJavaScriptException

package object migrations {

  implicit final class FsOps(val fs: Fs) extends AnyVal {
    def readdirFuture(path: String): Future[js.Array[String]] =
      functionToFuture[js.Error, js.Array[String]](fs.readdir(path, _))

    def readFileFuture(path: String, encoding: String): Future[String] =
      functionToFuture[js.Error, String](fs.readFile(path, encoding, _))
  }

  def functionToFuture[E, T](f: js.Function2[E, T, Any] => Unit): Future[T] = {
    val promise = Promise[T]()
    f((error, result) => {
      if (error == null || js.isUndefined(error))
        promise.success(result)
      else
        promise.failure(wrapJavaScriptException(error))
    })
    promise.future
  }

}
