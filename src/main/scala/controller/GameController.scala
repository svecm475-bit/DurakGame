package controller

import scala.io.StdIn.readLine
import model.{Card,Game}
import view.{TUI,DurakField}

class GameController (game: Game, tui: TUI) {
  def gameLoop(): Unit =
    print("> ")
    val input = readLine()
    processInput(input)

    if input != "exit" && input != "q" then gameLoop()
  
  def processPlayingCard(): Unit = {
        println("Choose index of the card you want to play:")
        val index = readLine().toInt
        println(index)
        val card: Option[Card] = game.player1.playCard(index)
        tui.displayCard(card)
      }

  def processInput(input: String): Unit = {
        input match
          case "exit" | "q" => println("Exiting...")
          case "show" =>
            // Print the dynamic game field using current state parameters
            tui.displayGame(game)
          case "play" => processPlayingCard()
          case _ => println("Unknown command")
      }


}
