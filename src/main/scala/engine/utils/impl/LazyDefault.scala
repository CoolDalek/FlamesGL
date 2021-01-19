package engine.utils.impl

import java.util.concurrent.ConcurrentLinkedQueue

import engine.utils.Lazy

class LazyDefault[T](value: => T) extends Lazy[T] {

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