package engine.mvc

trait Controller

object Controller {

  trait StatelessController[Graphics, CurrEnv <: Environment[CurrEnv]] extends Controller {

    def apply(graphics: Graphics)(implicit env: CurrEnv): Graphics = control(graphics)

    def control(graphics: Graphics)(implicit env: CurrEnv): Graphics

  }

  trait StatefulController[Graphics, State, CurrEnv <: Environment[CurrEnv]] extends Controller {

    def apply(graphics: Graphics, state: State)(implicit env: CurrEnv): Graphics = control(graphics, state)

    def control(graphics: Graphics, state: State)(implicit env: CurrEnv): Graphics

  }

  def emptyStateless[Graphics, CurrEnv <: Environment[CurrEnv]](): StatelessController[Graphics, CurrEnv] =
    new StatelessController[Graphics, CurrEnv] {

      def control(graphics: Graphics)(implicit env: CurrEnv): Graphics = graphics

    }

  def emptyStateful[Graphics, State, CurrEnv <: Environment[CurrEnv]](): StatefulController[Graphics, State, CurrEnv] =
    new StatefulController[Graphics, State, CurrEnv] {

      def control(graphics: Graphics, state: State)(implicit env: CurrEnv): Graphics = graphics

    }

}
