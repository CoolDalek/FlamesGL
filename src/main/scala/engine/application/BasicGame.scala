package engine.application

import engine.application.Launcher.PrimaryStage
import engine.events.KeyboardHandlers
import engine.events.impls.KeyboardHandlersSets
import engine.utils.Lazy
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.World
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import scalafx.scene.layout.AnchorPane

trait BasicGame extends Game[Canvas, AnchorPane] {

  val renderComponent: Lazy[Canvas] = Lazy(new Canvas())

  val rootComponent: Lazy[AnchorPane] =
    renderComponent.map { render =>
      new AnchorPane() {
        children = render
      }
    }

  val scene: Lazy[Scene] =
    rootComponent.map { component =>

      new Scene {
        root = component
      }

    }

  val stage: Lazy[PrimaryStage] =
    scene.map { mainScene =>

      new PrimaryStage {

        icons += new Image("icon.png")

        title = "Flames App"

        maximized = true

        resizable = false

        scene = mainScene

      }

    }

  stage.onInit { it =>
    val canvas = renderComponent()
    canvas.width <== it.width
    canvas.height <== it.height
  }

  val keyboardHandlers: Lazy[KeyboardHandlers] = scene.map(new KeyboardHandlersSets(_))

  val gameWorld: Lazy[World] = Lazy(new World(new Vec2()))

}