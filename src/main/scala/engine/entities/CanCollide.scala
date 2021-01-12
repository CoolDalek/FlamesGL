package engine.entities

trait CanCollide {

  this: Entity[_] =>

  println(s"Hello ${entity.graphics}")

}
