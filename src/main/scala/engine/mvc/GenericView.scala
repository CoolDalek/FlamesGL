package engine.mvc

import scalafx.scene.Node

trait GenericView[Graphics <: Node]

object GenericView {

  trait View[Graphics <: Node] extends GenericView[Graphics] {

    def view: Graphics

  }

}