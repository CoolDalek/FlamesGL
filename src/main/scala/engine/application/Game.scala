package engine.application

import engine.utils.Lazy
import org.jbox2d.dynamics.World
import scalafx.scene.{Node, Parent}

trait Game[Renderer <: Node, Root <: Parent]
  extends App[Renderer, Root] {

  val gameWorld: Lazy[World]

}