package engine.time

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.TimeUnit
import scala.language.implicitConversions

//TODO: make wrapper when java get the value types

sealed trait TinyTime {

  def toTimeUnit: TimeUnit

}

object TinyTime {

  case object NanoSeconds extends TinyTime {

    override def toTimeUnit: TimeUnit = TimeUnit.NANOSECONDS

  }
  case object MicroSeconds extends TinyTime {

    override def toTimeUnit: TimeUnit = TimeUnit.MICROSECONDS

  }
  case object MilliSeconds extends TinyTime {

    override def toTimeUnit: TimeUnit = TimeUnit.MILLISECONDS

  }
  case object Seconds extends TinyTime {

    override def toTimeUnit: TimeUnit = TimeUnit.SECONDS

  }

  def convertFromNanos(duration: Double, to: TinyTime): Double =
    to match {
      case NanoSeconds =>
        duration
      case MicroSeconds =>
        duration / 1e3
      case MilliSeconds =>
        duration / 1e6
      case Seconds =>
        duration / 1e9
    }

  def convert(from: TinyTime, duration: Double, to: TinyTime): Double =
    from match {
      case NanoSeconds =>
        convertFromNanos(duration, to)
      case MicroSeconds =>
        convertFromNanos(duration * 1e3, to)
      case MilliSeconds =>
        convertFromNanos(duration * 1e6, to)
      case Seconds =>
        convertFromNanos(duration * 1e9, to)
    }

  def fromTimeUnit(tu: TimeUnit): TinyTime =
    tu match {
      case TimeUnit.NANOSECONDS =>
        NanoSeconds
      case TimeUnit.MICROSECONDS =>
        MicroSeconds
      case TimeUnit.MILLISECONDS =>
        MilliSeconds
      case TimeUnit.SECONDS =>
        Seconds
      case unit: TimeUnit =>
        throw new IllegalArgumentException(s"TimeUnit $unit too big for TinyTime.")
    }

  object UnsafeConversions {

    implicit def tu2tiny(tu: TimeUnit): TinyTime = fromTimeUnit(tu)

  }

}