package engine.mvc

import engine.mvc.GenericController.{StatefulController, StatelessController}
import engine.mvc.GenericView.View
import engine.utils.Lazy
import scalafx.scene.Node

trait GenericComponent[Graphics <: Node] {

  this: GenericView[Graphics]
    with GenericController[Graphics] =>

}

object GenericComponent {

  trait StatelessComponent[Graphics <: Node] extends GenericComponent[Graphics] {

    this: StatelessController[Graphics]
      with View[Graphics] =>

    protected val component: Lazy[Graphics] = Lazy(control(view))

  }

  trait StatefulComponent[Graphics <: Node, State] extends GenericComponent[Graphics] {

    this: StatefulController[Graphics, State]
      with View[Graphics]
      with Model[State]=>

    protected val component: Lazy[Graphics] = Lazy(control(view, state))

  }

}