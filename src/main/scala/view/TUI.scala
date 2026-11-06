package view

import controller.GameController
import util.Observer

import scala.io.StdIn.readLine
class TUI(val controller: GameController) extends Observer{

  def displayGame() : Unit = {
    print("\u001b[H\u001b[2J")
    Console.flush()

    println(DurakField.displayField(
      trumpCard = controller.getTrumpCard,
      cardsInDeck = controller.getCardsInDeck,
      opponentCardsCount = controller.getOpponentCardsCount,
      attackingCard = controller.getAttackingCard,
      defendingCard = controller.getDefendingCard,
      playerHand = controller.getPlayerHand
    ))
  }
  
  def gameLoop(): Unit =
    val input = readLine()

    input match {
      case "exit" | "q" =>
        println("Exiting...")

      case "show" =>
        displayGame()

      case "play" =>
        println("Choose index of the card you want to play:")
        val index = readLine().toInt
        controller.processPlayingCard(index)
      case _ =>
        println("Unknown command")
    }

    if input != "exit" && input != "q" then gameLoop()

  def update(): Unit =
    displayGame()
}
