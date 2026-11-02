package controller

import model.Game
import view.TUI

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GameControllerSpec extends AnyWordSpec with Matchers {

  "GameController" should {

    "process show command" in {
      val game = Game.startGame()
      val tui = new TUI(
        game.trumpCard,
        game.deck.getSize,
        game.player2.getHandSize,
        None,
        None,
        game.player1.getHand
      )

      val controller = new GameController(game, tui)
      val output = new ByteArrayOutputStream()

      Console.withOut(new PrintStream(output)) {
        controller.processInput("show")
      }

      output.toString("UTF-8") should include("CARD GAME: DURAK")
    }

    "process play command" in {
      val game = Game.startGame()
      val tui = new TUI(
        game.trumpCard,
        game.deck.getSize,
        game.player2.getHandSize,
        None,
        None,
        game.player1.getHand
      )

      val controller = new GameController(game, tui)
      val firstCard = game.player1.getHand.head

      val input = new ByteArrayInputStream("0\n".getBytes("UTF-8"))
      val output = new ByteArrayOutputStream()

      Console.withIn(input) {
        Console.withOut(new PrintStream(output)) {
          controller.processInput("play")
        }
      }

      game.player1.getHandSize shouldBe 5
      output.toString("UTF-8") should include(firstCard.toString)
    }

    "handle invalid card index" in {
      val game = Game.startGame()
      val tui = new TUI(
        game.trumpCard,
        game.deck.getSize,
        game.player2.getHandSize,
        None,
        None,
        game.player1.getHand
      )

      val controller = new GameController(game, tui)
      val input = new ByteArrayInputStream("99\n".getBytes("UTF-8"))
      val output = new ByteArrayOutputStream()

      Console.withIn(input) {
        Console.withOut(new PrintStream(output)) {
          controller.processInput("play")
        }
      }

      game.player1.getHandSize shouldBe 6
      output.toString("UTF-8") should include("No card found")
    }

    "process exit command" in {
      val game = Game.startGame()
      val tui = new TUI(
        game.trumpCard,
        game.deck.getSize,
        game.player2.getHandSize,
        None,
        None,
        game.player1.getHand
      )

      val controller = new GameController(game, tui)
      val output = new ByteArrayOutputStream()

      Console.withOut(new PrintStream(output)) {
        controller.processInput("exit")
      }

      output.toString("UTF-8") should include("Exiting...")
    }

    "process q command" in {
      val game = Game.startGame()
      val tui = new TUI(
        game.trumpCard,
        game.deck.getSize,
        game.player2.getHandSize,
        None,
        None,
        game.player1.getHand
      )

      val controller = new GameController(game, tui)
      val output = new ByteArrayOutputStream()

      Console.withOut(new PrintStream(output)) {
        controller.processInput("q")
      }

      output.toString("UTF-8") should include("Exiting...")
    }

    "process unknown command" in {
      val game = Game.startGame()
      val tui = new TUI(
        game.trumpCard,
        game.deck.getSize,
        game.player2.getHandSize,
        None,
        None,
        game.player1.getHand
      )

      val controller = new GameController(game, tui)
      val output = new ByteArrayOutputStream()

      Console.withOut(new PrintStream(output)) {
        controller.processInput("invalid")
      }

      output.toString("UTF-8") should include("Unknown command")
    }

    "stop game loop after exit command" in {
      val game = Game.startGame()
      val tui = new TUI(
        game.trumpCard,
        game.deck.getSize,
        game.player2.getHandSize,
        None,
        None,
        game.player1.getHand
      )

      val controller = new GameController(game, tui)

      val input = new ByteArrayInputStream("exit\n".getBytes("UTF-8"))
      val output = new ByteArrayOutputStream()

      Console.withIn(input) {
        Console.withOut(new PrintStream(output)) {
          controller.gameLoop()
        }
      }

      output.toString("UTF-8") should include("Exiting...")
    }

    "stop game loop after q command" in {
      val game = Game.startGame()
      val tui = new TUI(
        game.trumpCard,
        game.deck.getSize,
        game.player2.getHandSize,
        None,
        None,
        game.player1.getHand
      )

      val controller = new GameController(game, tui)

      val input = new ByteArrayInputStream("q\n".getBytes("UTF-8"))
      val output = new ByteArrayOutputStream()

      Console.withIn(input) {
        Console.withOut(new PrintStream(output)) {
          controller.gameLoop()
        }
      }

      output.toString("UTF-8") should include("Exiting...")
    }
  }
}
