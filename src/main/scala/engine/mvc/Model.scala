package engine.mvc

trait Model[State] {

  def apply(): State = state()

  def state(): State

}

object Model {

  def model[State](modelState: State): Model[State] =
    new Model[State] {
      override val state: State = modelState
    }

}