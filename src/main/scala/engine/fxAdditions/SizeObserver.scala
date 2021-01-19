package engine.fxAdditions

import scalafx.beans.property.ReadOnlyDoubleProperty
import scalafx.event.subscriptions.Subscription

class SizeObserver[T](resizable: T, observable: SizeObservable[_ >: T]) {

  private def height: ReadOnlyDoubleProperty = observable.height(resizable)

  private def width: ReadOnlyDoubleProperty = observable.width(resizable)

  def onSizeChange(onWidth: => Unit, onHeight: => Unit): (Subscription, Subscription) = (
    width.onChange(onWidth),
    height.onChange(onHeight)
  )

  def onSizeChange(onWidth: Double => Unit,
                   onHeight: Double => Unit): (Subscription, Subscription) = (
    width.onChange(onWidth(width())),
    height.onChange(onHeight(height()))
  )

  def onSizeChange(onWidth: (Double, Double) => Unit,
                   onHeight: (Double, Double) => Unit): (Subscription, Subscription) = (
    width.onChange(onWidth(width(), height())),
    height.onChange(onHeight(width(), height()))
  )

  def onSizeChange(onAny: (Double, Double) => Unit): (Subscription, Subscription) = (
    width.onChange(onAny(width(), height())),
    height.onChange(onAny(width(), height()))
  )

  def onSizeChange(onAny: => Unit): (Subscription, Subscription) = (
    width.onChange(onAny),
    height.onChange(onAny)
  )

}

object SizeObserver {

  trait Implicits {
    import scala.language.implicitConversions

    implicit def sizeObserverSyntax[T](resizable: T)
                                      (implicit observable: SizeObservable[_ >: T]): SizeObserver[T] =
      new SizeObserver[T](resizable, observable)

  }

}