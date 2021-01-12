package engine.time.impls

import engine.time.TimerComponents
import javafx.animation.AnimationTimer
import scalafx.event.subscriptions.Subscription

import scala.collection.concurrent.TrieMap

trait TimerComponentsAnimation extends TimerComponents {

  override val timer: Timer = TimerImpl

  private object TimerImpl extends Timer {

    val tickingHandlers: TrieMap[String, Double => Unit] =
      TrieMap.empty[String, Double => Unit]

    val animationTimer: AnimationTimer =
      new AnimationTimer {
        var lastTime = 0L

        override def handle(now: Long): Unit = {
          val delta = now - lastTime
          if(lastTime > 0) {
            tickingHandlers.foreach { case (_, func) =>
              func(delta.toDouble)
            }
          }
          lastTime = now
        }

      }

    override def handle(handler: Double => Unit): Subscription = {
      val name = handler.toString()
      tickingHandlers.addOne(name -> handler)
      () => tickingHandlers.remove(name)
    }

    override def start(): Unit = animationTimer.start()

    override def stop(): Unit = animationTimer.stop()

  }


}
