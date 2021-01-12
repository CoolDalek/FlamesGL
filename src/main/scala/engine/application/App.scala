package engine.application

import engine.application.GameLauncher.GameStage
import engine.events.KeyboardHandlers
import engine.utils.Lazy
import scalafx.scene.{Node, Parent, Scene}

trait App[Renderer <: Node, Root <: Parent] {

  val renderComponent: Lazy[Renderer]

  val rootComponent: Lazy[Root]

  val window: Lazy[Scene]

  val stage: Lazy[GameStage]

  val keyboardHandlers: Lazy[KeyboardHandlers]

}
