package engine.mvc

import engine.utils.Lazy

trait Fabric

object Fabric {

  trait StatelessFabric[Graphics, CurrEnv <: Environment[CurrEnv]] extends Fabric {

    type View = View.StatelessView[Graphics, CurrEnv]

    type Controller = Controller.StatelessController[Graphics, CurrEnv]

    protected val controller: Controller

    protected val view: View

    protected def view(fabric: CurrEnv => Graphics): View =
      View.stateless(fabric)

    protected def view(fabric: => Graphics): View =
      View.stateless(fabric)

    protected def emptyController(): Controller =
      Controller.emptyStateless[Graphics, CurrEnv]()

    def build()(implicit env: CurrEnv): Graphics = controller(view())

    def apply()(implicit env: CurrEnv): Graphics = build()

    def buildLazy()(implicit env: CurrEnv): Lazy[Graphics] = Lazy(build())

  }

  trait StatefulFabric[Graphics, State, CurrEnv <: Environment[CurrEnv]] extends Fabric {

    import engine.mvc.{Model => TypedModel}

    type Model = TypedModel[State]

    type View = View.StatefulView[Graphics, State, CurrEnv]

    type Controller = Controller.StatefulController[Graphics, State, CurrEnv]

    protected val controller: Controller

    protected val view: View

    protected val model: Model

    protected def model(state: State): Model = TypedModel.model(state)

    protected def view(fabric: State => Graphics): View =
      View.stateful(fabric)

    protected def view(fabric: (State, CurrEnv) => Graphics): View =
      View.stateful(fabric)

    protected def emptyController(): Controller =
      Controller.emptyStateful[Graphics, State, CurrEnv]()

    def build()(implicit env: CurrEnv): Graphics = {
      val state = model()
      controller(view(state), state)
    }

    def apply()(implicit env: CurrEnv): Graphics = build()

    def buildLazy()(implicit env: CurrEnv): Lazy[Graphics] = Lazy(build())

  }

}
