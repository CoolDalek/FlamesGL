import engine.application.BasicGame
import scalafx.Includes._
import scalafx.scene.input.{MouseButton, MouseEvent}

object Main extends BasicGame {

  renderComponent.onInit { component =>

    def controls(e: MouseEvent): Unit = {
      val graphics2D = component.graphicsContext2D

      e.button match {
        case MouseButton.Primary =>
          graphics2D.fillOval(e.x, e.y, 10, 10)
        case MouseButton.Secondary =>
          graphics2D.clearRect(e.x, e.y, 10, 10)
        case _ => ()
      }

    }

    component.onMousePressed = controls

    component.onMouseDragged = controls

  }

}