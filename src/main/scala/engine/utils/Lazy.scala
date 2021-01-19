package engine.utils

import engine.utils.impl.LazyDefault

trait Lazy[T] {

  def apply(): T

  def map[R](f: T => R): Lazy[R]

  def onInit(f: T => Unit): Unit

}

object Lazy {

  def apply[T](value: => T): Lazy[T] = new LazyDefault[T](value)

}
