package engine.fxAdditions

import scalafx.beans.property.ReadOnlyDoubleProperty

trait SizeObservable[T] {

  def width(resizable: T): ReadOnlyDoubleProperty

  def height(resizable: T): ReadOnlyDoubleProperty

}

object SizeObservable {

  def apply[T](getWidth: T => ReadOnlyDoubleProperty,
               getHeight: T => ReadOnlyDoubleProperty): SizeObservable[T] =
    new SizeObservable[T] {

      override def width(resizable: T): ReadOnlyDoubleProperty = getWidth(resizable)

      override def height(resizable: T): ReadOnlyDoubleProperty = getHeight(resizable)

    }

  trait SizeObservableImplicits {
    import scalafx.scene.Scene
    import scalafx.stage.Window

    implicit val sceneSizeObservable: SizeObservable[Scene] =
      SizeObservable[Scene](_.width, _.height)

    implicit val windowSizeObservable: SizeObservable[Window] =
      SizeObservable[Window](_.width, _.height)

  }

}