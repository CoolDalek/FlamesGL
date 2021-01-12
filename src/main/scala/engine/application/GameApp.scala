package engine.application

import engine.application.GameLauncher.GameStage
import engine.events.KeyboardHandlers
import engine.utils.Lazy
import org.jbox2d.dynamics.World
import scalafx.scene.{Node, Parent, Scene}

trait GameApp[Renderer <: Node, Root <: Parent] extends GameLauncher {

  val renderComponent: Lazy[Renderer]

  val rootComponent: Lazy[Root]

  val window: Lazy[Scene]

  val stage: Lazy[GameStage]

  val keyboardHandlers: Lazy[KeyboardHandlers]

  val gameWorld: Lazy[World]

  override def boot(): GameStage = stage()

}