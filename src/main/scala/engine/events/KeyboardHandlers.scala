package engine.events

import scalafx.event.subscriptions.Subscription
import scalafx.scene.input.KeyEvent

trait KeyboardHandlers {

  def onKeyReleased(handler: KeyEvent => Unit): Subscription

  def onKeyPressed(handler: KeyEvent => Unit): Subscription

  def onKeyTyped(handler: KeyEvent => Unit): Subscription

}
