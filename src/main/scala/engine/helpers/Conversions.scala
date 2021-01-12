package engine.helpers

import org.jbox2d.common.Vec2

import scala.language.implicitConversions

object Conversions {

  def jBoxAbsSizeToFx(size: Float): Double = size * 10

  def jBoxRelSizeToFx(size: Float, side: Double): Double = side * size / 100

  def jBoxXToFx(x: Float, width: Double): Double = jBoxRelSizeToFx(x, width)

  def jBoxYToFx(y: Float, height: Double): Double = height - jBoxRelSizeToFx(y, height)

  def FxAbsSizeToJBox(size: Double): Float = (size / 10).toFloat

  def FxRelSizeToJBox(size: Double, side: Double): Float = (size * 100 / side).toFloat

  def FxXtoJBox(x: Double, width: Double): Float = FxRelSizeToJBox(x, width)

  def FxYtoJBox(y: Double, height: Double): Float = 100f - FxRelSizeToJBox(y, height)

  implicit class DoubleAsFX(val n: Double) extends AnyVal {

    def toJBoxAbsSize: Float = Conversions.FxAbsSizeToJBox(n)

    def toJBoxRelSize(side: Double): Float = Conversions.FxRelSizeToJBox(n, side)

    def toJBoxX(width: Double): Float = Conversions.FxXtoJBox(n, width)

    def toJBoxY(height: Double): Float = Conversions.FxYtoJBox(n, height)

  }

  implicit class FloatAsJBox(val n: Float) extends AnyVal {

    def toFxAbsSize: Double = Conversions.jBoxAbsSizeToFx(n)

    def toFxRelSize(side: Double): Double = Conversions.jBoxRelSizeToFx(n, side)

    def toFxX(width: Double): Double = Conversions.jBoxXToFx(n, width)

    def toFxY(height: Double): Double = Conversions.jBoxYToFx(n, height)

  }

  implicit def vec2toTuple(vec: Vec2): (Float, Float) = (vec.x, vec.y)

}