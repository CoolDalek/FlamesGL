package engine.utils

import java.util.concurrent.ConcurrentLinkedQueue

trait Lazy[T] {

  def apply(): T

  def map[R](f: T => R): Lazy[R]

  def onInit(f: T => Unit): Unit

}

object Lazy {

  private class LazyDefault[T](value: => T) extends Lazy[T] {

    val callbacks = new ConcurrentLinkedQueue[T => Unit]()

    @volatile
    var isInit = false

    lazy val _value: T = {
      val result = value
      isInit = true
      callbacks.forEach(_(result))
      callbacks.clear()
      result
    }

    override def apply(): T = _value

    override def map[R](f: T => R): Lazy[R] = new LazyDefault[R](f(_value))

    override def onInit(f: T => Unit): Unit =
      if(isInit) {
        f(_value)
      } else {
        callbacks.add(f)
      }

  }

  def apply[T](value: => T): Lazy[T] = new LazyDefault[T](value)

}
