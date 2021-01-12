package engine.application

import engine.application.Launcher.PrimaryStage
import scalafx.scene.{Node, Parent}

trait App[Renderer <: Node, Root <: Parent]
  extends Launcher with UI[Renderer, Root] {

  override def boot(): PrimaryStage = stage()

}
