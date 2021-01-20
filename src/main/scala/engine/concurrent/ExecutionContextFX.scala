package engine.concurrent

import engine.utils.Self.InvariantSelf
import scalafx.application.Platform

import scala.concurrent.ExecutionContext

object ExecutionContextFX
  extends ExecutionContext
    with InvariantSelf[ExecutionContextFX.type] {

  override def execute(runnable: Runnable): Unit =
    Platform.runLater(runnable)

  override def reportFailure(cause: Throwable): Unit =
    ExecutionContext.defaultReporter(cause)

}