package engine.time

import scalafx.event.subscriptions.Subscription

trait TimerComponents {

  val timer: Timer

  trait Timer {

    def handle(handler: Double => Unit): Subscription

    def handle(timeUnit: TinyTime)(handler: Double => Unit): Subscription = {
      val handlerWithConversion =
        (nanos: Double) => {
          val convertedTime = TinyTime.convertFromNanos(nanos, timeUnit)
          handler(convertedTime)
        }
      handle(handlerWithConversion)
    }

    def start(): Unit

    def stop(): Unit

  }

}
