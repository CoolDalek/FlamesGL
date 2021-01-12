package engine.application

import engine.application.GameLauncher.GameStage
import engine.events.KeyboardHandlers
import engine.events.impls.KeyboardHandlersSets
import engine.utils.Lazy
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.World
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import scalafx.scene.layout.HBox

trait BasicGame extends GameApp[Canvas, HBox] {

  val renderComponent: Lazy[Canvas] = Lazy(new Canvas(300, 400))

  val rootComponent: Lazy[HBox] = renderComponent.map(new HBox(_))

  val window: Lazy[Scene] =
    rootComponent.map { component =>

      new Scene {
        root = component
      }

    }

  val stage: Lazy[GameStage] =
    window.map { mainScene =>

      new GameStage {

        icons += new Image("icon.png")

        title = "Flames App"

        maximized = true

        resizable = false

        scene = mainScene

      }

    }

  val keyboardHandlers: Lazy[KeyboardHandlers] = window.map(new KeyboardHandlersSets(_))

  val gameWorld: Lazy[World] = Lazy(new World(new Vec2()))

}
