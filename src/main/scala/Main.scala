import controller.GameController
import model.Game
import view.{DurakField, TUI}

import scala.annotation.tailrec
import scala.io.StdIn.readLine

@main def main(): Unit = {
  // Create a full 36-card deck and snuffle it
  val game = Game.startGame()
  val controller = new GameController(game)
  val tui = new TUI(controller)

  controller.add(tui)
  tui.displayGame()
  tui.gameLoop()
}

