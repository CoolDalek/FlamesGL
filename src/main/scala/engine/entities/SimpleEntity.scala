package engine.entities

import org.jbox2d.dynamics.Body
import scalafx.scene.Node

class SimpleEntity[T <: Node](node: T, body: Body) extends Entity[Node] with CanCollide {

  override lazy val graphics: Node = node

  override lazy val physics: Body = body

}

object SimpleEntity {

  def apply[T <: Node](node: T, body: Body): SimpleEntity[T] = new SimpleEntity(node, body)

}
