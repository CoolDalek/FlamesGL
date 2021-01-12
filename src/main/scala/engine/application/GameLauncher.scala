package engine.application

import engine.application.GameLauncher.{GameStage, _}
import javafx.application.Application
import javafx.stage.{Stage => jfxStage}
import scalafx.stage.{Stage => sfxStage}

trait GameLauncher {

  def main(args: Array[String]): Unit = {
    ActiveGameApp = this
    Application.launch(classOf[LaunchHelper], args: _*)
  }

  def boot(): GameStage

}

object GameLauncher {

  private[application] class LaunchHelper extends Application {

    override def start(primaryStage: jfxStage): Unit = {
      ActiveJFXApp = this
      Stage = primaryStage
      ActiveGameApp.boot().show()
    }

  }

  private[application] var ActiveJFXApp: Application = _

  private[application] var ActiveGameApp: GameLauncher = _

  private[application] var Stage: jfxStage = _

  class GameStage extends sfxStage(Stage)

}