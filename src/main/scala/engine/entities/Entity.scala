package engine.entities

import org.jbox2d.dynamics.Body
import scalafx.scene.Node

import scala.collection.mutable.ListBuffer
import scala.language.implicitConversions

trait Entity[T <: Node] {

  protected val initCode: ListBuffer[() => Unit] = ListBuffer.empty

  val entity: Entity[T] = this

  def graphics: T

  def physics: Body

  override def toString: String = s"Entity, $graphics, $physics"

}

object Entity {

  implicit def entity2graphics[T <: Node](entity: Entity[T]): T = entity.graphics

  implicit def entity2physics(entity: Entity[_]): Body = entity.physics

}