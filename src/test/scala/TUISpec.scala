import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import model.Game
import controller.GameController
import view.TUI

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}
import java.nio.charset.StandardCharsets

class TUISpec extends AnyWordSpec with Matchers {

  // Captures everything printed by TUI
  private def captureOutput(action: => Unit): String = {
    val output = new ByteArrayOutputStream()
    val stream = new PrintStream(output, true, "UTF-8")

    try {
      Console.withOut(stream) {
        action
      }
      output.toString("UTF-8")
    } finally {
      stream.close()
    }
  }

  // Simulates commands entered by the user
  private def withInput(input: String)(action: => Unit): Unit = {
    val stream = new ByteArrayInputStream(
      input.getBytes(StandardCharsets.UTF_8)
    )

    try {
      Console.withIn(stream) {
        action
      }
    } finally {
      stream.close()
    }
  }

  "view.TUI" should {

    "display the current game field" in {
      val game = Game.startGame()
      val controller = new GameController(game)
      val tui = new TUI(controller)

      val result = captureOutput {
        tui.displayGame()
      }

      result should include("CARD GAME: DURAK")
      result should include("Opponent cards: 6")
      result should include("TABLE:")
      result should include("YOUR HAND:")
      result should include("Attack:")
      result should include("Defend:")
      result should include(game.trumpCard.toString)
    }

    "display the updated field when notified" in {
      val game = Game.startGame()
      val controller = new GameController(game)
      val tui = new TUI(controller)

      controller.add(tui)

      val playedCard = game.player1.getHand.head

      val result = captureOutput {
        controller.processPlayingCard(0)
      }

      game.attackingCard shouldBe Some(playedCard)
      game.player1.getHandSize shouldBe 5

      result should include("CARD GAME: DURAK")
      result should include(s"Attack:  $playedCard")
    }

    "display the game when the user enters show" in {
      val game = Game.startGame()
      val controller = new GameController(game)
      val tui = new TUI(controller)

      val result = captureOutput {
        withInput("show\nq\n") {
          tui.gameLoop()
        }
      }

      result should include("CARD GAME: DURAK")
      result should include("YOUR HAND:")
      result should include("Exiting...")
    }

    "process a card when the user enters play" in {
      val game = Game.startGame()
      val controller = new GameController(game)
      val tui = new TUI(controller)

      controller.add(tui)

      val playedCard = game.player1.getHand.head

      val result = captureOutput {
        withInput("play\n0\nq\n") {
          tui.gameLoop()
        }
      }

      game.attackingCard shouldBe Some(playedCard)
      game.player1.getHandSize shouldBe 5

      result should include("Choose index")
      result should include(s"Attack:  $playedCard")
      result should include("Exiting...")
    }

    "handle an unknown command" in {
      val game = Game.startGame()
      val controller = new GameController(game)
      val tui = new TUI(controller)

      val result = captureOutput {
        withInput("unknown\nq\n") {
          tui.gameLoop()
        }
      }

      result should include("Unknown command")
      result should include("Exiting...")
    }

    "exit when the user enters exit" in {
      val game = Game.startGame()
      val controller = new GameController(game)
      val tui = new TUI(controller)

      val result = captureOutput {
        withInput("exit\n") {
          tui.gameLoop()
        }
      }

      result should include("Exiting...")
    }

    "exit when the user enters q" in {
      val game = Game.startGame()
      val controller = new GameController(game)
      val tui = new TUI(controller)

      val result = captureOutput {
        withInput("q\n") {
          tui.gameLoop()
        }
      }

      result should include("Exiting...")
    }
  }
}
