package engine.helpers

import javafx.scene.{image => jfxsi, paint => jfxsp}
import scalafx.delegate.SFXDelegate
import scalafx.scene.image.Image
import scalafx.scene.paint.Paint

import scala.language.implicitConversions

object ImagePattern {

  implicit def sfxImagePattern2jfx(ip: ImagePattern): jfxsp.ImagePattern = if(ip != null) ip.delegate else null

}

class ImagePattern(override val delegate: jfxsp.ImagePattern) extends Paint(delegate) with SFXDelegate[jfxsp.Paint] {

  def this(image: Image) = this(new jfxsp.ImagePattern(image))

  def this(image: Image, x: Double, y: Double, width: Double, height: Double, proportional: Boolean) =
    this(new jfxsp.ImagePattern(image, x, y, width, height, proportional))

  def image: jfxsi.Image = delegate.getImage

  def x: Double = delegate.getX

  def y: Double = delegate.getY

  def width: Double = delegate.getWidth

  def height: Double = delegate.getHeight

  def proportional: Boolean = delegate.isProportional

  def opaque: Boolean = delegate.isOpaque

}
