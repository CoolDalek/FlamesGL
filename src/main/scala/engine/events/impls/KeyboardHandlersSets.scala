package engine.events.impls

import engine.events.KeyboardHandlers
import scalafx.Includes._
import scalafx.event.subscriptions.Subscription
import scalafx.scene.Scene
import scalafx.scene.input.KeyEvent

import scala.collection.mutable

class KeyboardHandlersSets(val scene: Scene) extends KeyboardHandlers {

  val released: mutable.Set[KeyEvent => Unit] =
    mutable.Set.empty[KeyEvent => Unit]

  val pressed: mutable.Set[KeyEvent => Unit] =
    mutable.Set.empty[KeyEvent => Unit]

  val typed: mutable.Set[KeyEvent => Unit] =
    mutable.Set.empty[KeyEvent => Unit]

  def callHandlers(handlers: mutable.Set[KeyEvent => Unit]): KeyEvent => Unit =
    (e: KeyEvent) => {
      handlers.foreach(_(e))
    }

  scene.onKeyReleased = callHandlers(released)

  scene.onKeyPressed = callHandlers(pressed)

  scene.onKeyTyped = callHandlers(typed)

  def registerHandler(handler: KeyEvent => Unit,
                      handlers: mutable.Set[KeyEvent => Unit]): Subscription = {
    handlers.addOne(handler)
    () => handlers.remove(handler)
  }

  override def onKeyReleased(handler: KeyEvent => Unit): Subscription =
    registerHandler(handler, released)

  override def onKeyPressed(handler: KeyEvent => Unit): Subscription =
    registerHandler(handler, pressed)

  override def onKeyTyped(handler: KeyEvent => Unit): Subscription =
    registerHandler(handler, typed)

}
