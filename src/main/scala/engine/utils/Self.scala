package engine.utils

trait Self

object Self {

  trait InvariantSelf[T] extends Self {

    this: T =>

    implicit def self: T = this

  }


  trait CovariantSelf[+T] extends Self {

    this: T =>

    implicit def self: T = this

  }

}