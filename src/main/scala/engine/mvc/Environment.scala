package engine.mvc

import engine.utils.Self.CovariantSelf

trait Environment[+Subtype] extends CovariantSelf[Subtype] {

  this: Subtype =>

}

object Environment {

  trait EnvironmentSubstitutions[CurrEnv <: Environment[CurrEnv]] {

    type StatelessFabric[Graphics] = Fabric.StatelessFabric[Graphics, CurrEnv]

    type StatefulFabric[Graphics, State] = Fabric.StatefulFabric[Graphics, State, CurrEnv]

  }

  trait EmptyEnv extends Environment[EmptyEnv]

  implicit object EmptyEnv extends EmptyEnv

}
