package engine.helpers

import scalafx.Includes._
import scalafx.event.subscriptions.Subscription
import scalafx.scene.paint.Color
import scalafx.scene.{Node, Scene}
import scalafx.stage.Window

object Includes {

  implicit class UberMegaCoolColor(val color: Color) extends AnyVal {

    def hex: String = color.delegate.toString.replace("0x", "#")

  }

  implicit class UberMegaCoolScene(val scene: Scene) extends AnyVal {

    def onSizeChange(onWidth: => Unit, onHeight: => Unit): (Subscription, Subscription) = (
      scene.width.onChange(onWidth),
      scene.height.onChange(onHeight)
    )

    def onSizeChange(onWidth: Double => Unit, onHeight: Double => Unit): (Subscription, Subscription) = (
      scene.width.onChange(onWidth(scene.width())),
      scene.height.onChange(onHeight(scene.height()))
    )

    def onSizeChange(onWidth: (Double, Double) => Unit,
                     onHeight: (Double, Double) => Unit): (Subscription, Subscription) = (
      scene.width.onChange(onWidth(scene.width(), scene.height())),
      scene.height.onChange(onHeight(scene.width(), scene.height()))
    )

    def onSizeChange(onAny: (Double, Double) => Unit): (Subscription, Subscription) = (
      scene.width.onChange(onAny(scene.width(), scene.height())),
      scene.height.onChange(onAny(scene.width(), scene.height()))
    )

    def onSizeChange(onAny: => Unit): (Subscription, Subscription) = (
      scene.width.onChange(onAny),
      scene.height.onChange(onAny)
    )

  }

  implicit class UberMegaWindowScene(val window: Window) extends AnyVal {

    def onSizeChange(onWidth: => Unit, onHeight: => Unit): (Subscription, Subscription) = (
      window.width.onChange(onWidth),
      window.height.onChange(onHeight)
    )

    def onSizeChange(onWidth: Double => Unit, onHeight: Double => Unit): (Subscription, Subscription) = (
      window.width.onChange(onWidth(window.width())),
      window.height.onChange(onHeight(window.height()))
    )

    def onSizeChange(onWidth: (Double, Double) => Unit,
                     onHeight: (Double, Double) => Unit): (Subscription, Subscription) = (
      window.width.onChange(onWidth(window.width(), window.height())),
      window.height.onChange(onHeight(window.width(), window.height()))
    )

    def onSizeChange(onAny: (Double, Double) => Unit): (Subscription, Subscription) = (
      window.width.onChange(onAny(window.width(), window.height())),
      window.height.onChange(onAny(window.width(), window.height()))
    )

    def onSizeChange(onAny: => Unit): (Subscription, Subscription) = (
      window.width.onChange(onAny),
      window.height.onChange(onAny)
    )

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
