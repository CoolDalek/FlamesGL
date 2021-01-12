package engine.concurrent

import scalafx.application.Platform

import scala.concurrent.ExecutionContext

object ExecutionContextFX extends ExecutionContext {

  override def execute(runnable: Runnable): Unit =
    Platform.runLater(runnable)

  override def reportFailure(cause: Throwable): Unit =
    ExecutionContext.defaultReporter(cause)

}