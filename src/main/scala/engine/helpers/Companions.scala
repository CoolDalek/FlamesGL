package engine.helpers

import scalafx.scene.Node
import scalafx.scene.layout.AnchorPane

object Companions {

  def setAnchors(node: Node, tb: Double, rl: Double): Unit =
    AnchorPane.setAnchors(node, tb, rl, tb, rl)

  def setAnchors(node: Node, trbl: Double): Unit =
    AnchorPane.setAnchors(node, trbl, trbl, trbl, trbl)

}
