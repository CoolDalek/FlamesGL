package engine.fxAdditions

import scalafx.Includes._
import scalafx.event.subscriptions.Subscription
import scalafx.scene.paint.Color
import scalafx.scene.{Node, Scene}

object Includes
  extends SizeObservable.Implicits
    with SizeObserver.Implicits {


  implicit class UberMegaCoolColor(val color: Color) extends AnyVal {

    def hex: String = color.delegate.toString.replace("0x", "#")

  }

  implicit class UberMegaCoolNode(val node: Node) extends AnyVal {

    def onSceneInit(block: => Unit): Unit = {
      if(node.scene() == null) {
        lazy val subscription: Subscription =
        node.scene.onChange {
          block
          subscription.cancel()
        }
        subscription
      } else {
        block
      }
    }

    def onSceneInit(block: Scene => Unit): Unit = {
      if(node.scene() == null) {
        lazy val subscription: Subscription =
          node.scene.onChange { (_, _, scene) =>
            block(scene)
            subscription.cancel()
          }
        subscription
      } else {
        block(node.scene())
      }
    }

  }

}
