package engine.application

import engine.application.GameLauncher.GameStage
import engine.utils.Lazy
import org.jbox2d.dynamics.World
import scalafx.scene.{Node, Parent}

trait GameApp[Renderer <: Node, Root <: Parent]
  extends GameLauncher with App[Renderer, Root] {

  val gameWorld: Lazy[World]

  override def boot(): GameStage = stage()

}