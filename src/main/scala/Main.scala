import controller.GameController
import model.Game
import view.{DurakField, TUI}

import scala.annotation.tailrec
import scala.io.StdIn.readLine

@main def main(): Unit = {
  // Create a full 36-card deck and snuffle it
  val game = Game.startGame()

  //Initialize view.TUI with the current game state variables
  val tui = TUI(
    trumpCard = game.trumpCard,
    cardsInDeck = game.deck.getSize,
    opponentCardsCount = game.player2.getHandSize,
    attackingCard = None, // temporarily
    defendingCard = None,
    playerHand = game.player1.getHand
  )

  tui.displayGame(game)
  println("Print command: ")

  val controller = new GameController(game, tui)
  controller.gameLoop()
}

