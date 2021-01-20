package engine.mvc

import engine.utils.Lazy

import scala.language.implicitConversions

trait Fabric

object Fabric {

  trait StatelessFabric[Graphics, CurrEnv <: Environment[CurrEnv]] extends Fabric {

    type View = CurrEnv => Graphics

    type Controller = (Graphics, CurrEnv) => Graphics

    protected val controller: Controller

    protected val view: View

    protected implicit def view(fabric: => Graphics): View =
      (_: CurrEnv) => fabric

    protected def view(fabric: CurrEnv => Graphics): View = fabric

    protected def emptyController(): Controller = (graphics: Graphics, _: CurrEnv) => graphics

    def build()(implicit env: CurrEnv): Graphics = controller(view(env), env)

    def apply()(implicit env: CurrEnv): Graphics = build()

    def buildLazy()(implicit env: CurrEnv): Lazy[Graphics] = Lazy(build())

  }

  trait StatefulFabric[Graphics, Model, CurrEnv <: Environment[CurrEnv]] extends Fabric {

    type View = (Model, CurrEnv) => Graphics

    type Controller = (Graphics, Model, CurrEnv) => Graphics

    protected val controller: Controller

    protected val view: View

    protected val model: Model

    protected implicit def view(fabric: Model => Graphics): View =
      (model: Model, _: CurrEnv) => fabric(model)

    protected def view(fabric: (Model, CurrEnv) => Graphics): View = fabric

    protected def emptyController(): Controller = (graphics: Graphics, _: Model, _: CurrEnv) => graphics

    def build()(implicit env: CurrEnv): Graphics = controller(view(model, env), model, env)

    def apply()(implicit env: CurrEnv): Graphics = build()

    def buildLazy()(implicit env: CurrEnv): Lazy[Graphics] = Lazy(build())

  }

}
