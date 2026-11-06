import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import model.Game
import controller.GameController
import view.TUI

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}

class TUISpec extends AnyWordSpec with Matchers {

  private def createTUI(): (Game, GameController, TUI) = {
    val game = Game.startGame()
    val controller = new GameController(game)
    val tui = new TUI(controller)

    (game, controller, tui)
  }

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

  private def runCommands(tui: TUI, commands: String): String = {
    val input = new ByteArrayInputStream(commands.getBytes("UTF-8"))

    try {
      captureOutput {
        Console.withIn(input) {
          tui.gameLoop()
        }
      }
    } finally {
      input.close()
    }
  }

  "view.TUI" should {

    "display the current game field" in {
      val (game, _, tui) = createTUI()

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
      val (game, controller, tui) = createTUI()
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
      val (_, _, tui) = createTUI()

      val result = runCommands(tui, "show\nq\n")

      result should include("CARD GAME: DURAK")
      result should include("YOUR HAND:")
      result should include("Exiting...")
    }

    "process a card when the user enters play" in {
      val (game, controller, tui) = createTUI()
      controller.add(tui)

      val playedCard = game.player1.getHand.head

      val result = runCommands(tui, "play\n0\nq\n")

      game.attackingCard shouldBe Some(playedCard)
      game.player1.getHandSize shouldBe 5

      result should include("Choose index")
      result should include(s"Attack:  $playedCard")
      result should include("Exiting...")
    }

    "handle an unknown command" in {
      val (_, _, tui) = createTUI()

      val result = runCommands(tui, "unknown\nq\n")

      result should include("Unknown command")
      result should include("Exiting...")
    }

    "exit when the user enters exit" in {
      val (_, _, tui) = createTUI()

      val result = runCommands(tui, "exit\n")

      result should include("Exiting...")
    }

    "exit when the user enters q" in {
      val (_, _, tui) = createTUI()

      val result = runCommands(tui, "q\n")

      result should include("Exiting...")
    }
  }
}
