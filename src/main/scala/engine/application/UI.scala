package engine.application

import engine.application.Launcher.PrimaryStage
import engine.events.KeyboardHandlers
import engine.utils.Lazy
import scalafx.scene.{Node, Parent, Scene}

trait UI[Renderer <: Node, Root <: Parent] {

  val renderComponent: Lazy[Renderer]

  val rootComponent: Lazy[Root]

  val window: Lazy[Scene]

  val stage: Lazy[PrimaryStage]

  val keyboardHandlers: Lazy[KeyboardHandlers]

}
