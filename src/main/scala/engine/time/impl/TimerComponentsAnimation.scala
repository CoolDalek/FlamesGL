package engine.time.impl

import engine.time.TimerComponents
import javafx.animation.AnimationTimer
import scalafx.event.subscriptions.Subscription

import scala.collection.mutable

trait TimerComponentsAnimation extends TimerComponents {

  override val timer: Timer = TimerImpl

  private object TimerImpl extends Timer {

    val tickingHandlers: mutable.Set[Double => Unit] =
      mutable.Set.empty[Double => Unit]

    val animationTimer: AnimationTimer =
      new AnimationTimer {
        var lastTime = 0L

        override def handle(now: Long): Unit = {
          val delta = now - lastTime
          if(lastTime > 0) {
            tickingHandlers.foreach { func =>
              func(delta.toDouble)
            }
          }
          lastTime = now
        }

      }

    override def handle(handler: Double => Unit): Subscription = {
      tickingHandlers.addOne(handler)
      () => tickingHandlers.remove(handler)
    }

    override def start(): Unit = animationTimer.start()

    override def stop(): Unit = animationTimer.stop()

  }


}
