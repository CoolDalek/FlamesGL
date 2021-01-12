package engine.application

import engine.application.Launcher.{PrimaryStage, _}
import javafx.application.Application
import javafx.stage.{Stage => jfxStage}
import scalafx.stage.{Stage => sfxStage}

trait Launcher {

  def main(args: Array[String]): Unit = {
    ActiveApp = this
    Application.launch(classOf[LaunchHelper], args: _*)
  }

  def boot(): PrimaryStage

}

object Launcher {

  private[application] class LaunchHelper extends Application {

    override def start(primaryStage: jfxStage): Unit = {
      ActiveJFXApp = this
      Stage = primaryStage
      ActiveApp.boot().show()
    }

  }

  private[application] var ActiveJFXApp: Application = _

  private[application] var ActiveApp: Launcher = _

  private[application] var Stage: jfxStage = _

  class PrimaryStage extends sfxStage(Stage)

}