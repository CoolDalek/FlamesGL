package engine.mvc

import scalafx.scene.Node

trait GenericController[Graphics <: Node]

object GenericController {

  trait StatelessController[Graphics <: Node] extends GenericController[Graphics] {

    def control(node: Graphics): Graphics

  }

  trait StatefulController[Graphics <: Node, State] extends GenericController[Graphics] {

    def control(node: Graphics, model: State): Graphics

  }

}