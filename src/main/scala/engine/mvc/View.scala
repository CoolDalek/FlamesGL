package engine.mvc

trait View

object View {

  trait StatelessView[Graphics, CurrEnv <: Environment[CurrEnv]] extends View {

    def apply()(implicit env: CurrEnv): Graphics = graphics()

    def graphics()(implicit env: CurrEnv): Graphics

  }

  trait StatefulView[Graphics, State, CurrEnv <: Environment[CurrEnv]] extends View {

    def apply(state: State)(implicit env: CurrEnv): Graphics = graphics(state)

    def graphics(state: State)(implicit env: CurrEnv): Graphics

  }

  def stateless[Graphics, CurrEnv <: Environment[CurrEnv]]
  (fabric: CurrEnv => Graphics): StatelessView[Graphics, CurrEnv] =
    new StatelessView[Graphics, CurrEnv] {

      def graphics()(implicit env: CurrEnv): Graphics = fabric(env)

    }

  def stateless[Graphics, CurrEnv <: Environment[CurrEnv]](fabric: => Graphics): StatelessView[Graphics, CurrEnv] =
    new StatelessView[Graphics, CurrEnv] {

      def graphics()(implicit env: CurrEnv): Graphics = fabric

    }

  def stateful[Graphics, State, CurrEnv <: Environment[CurrEnv]]
  (fabric: (State, CurrEnv) => Graphics): StatefulView[Graphics, State, CurrEnv] =
    new StatefulView[Graphics, State, CurrEnv] {

      def graphics(state: State)(implicit env: CurrEnv): Graphics = fabric(state, env)

    }

  def stateful[Graphics, State, CurrEnv <: Environment[CurrEnv]]
  (fabric: State => Graphics): StatefulView[Graphics, State, CurrEnv] =
    new StatefulView[Graphics, State, CurrEnv] {

      def graphics(state: State)(implicit env: CurrEnv): Graphics = fabric(state)

    }

}