
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.ByteArrayInputStream

class TUISpec extends AnyWordSpec with Matchers {

  "TUI" should {

    "display the playing field" in {
      val trump = Card(14, "♠")
      val playerHand = List(Card(7, "♥"))

      val tui = new TUI(
        trump, 24, 6, None, None, playerHand
      )

      val output = new ByteArrayOutputStream()

      Console.withOut(new PrintStream(output)) {
        tui.displayCard(None)
      }

      val result = output.toString("UTF-8")

      result should include("CARD GAME: DURAK")
      result should include("Opponent cards: 6")
      result should include("7♥")
      result should include("Attack:  -")
    }

    "display an attacking card" in {
      val trump = Card(14, "♠")
      val attack = Card(7, "♥")

      val tui = new TUI(
        trump, 24, 6, None, None, List.empty[Card]
      )

      val output = new ByteArrayOutputStream()

      Console.withOut(new PrintStream(output)) {
        tui.displayCard(Some(attack))
      }

      output.toString("UTF-8") should include("Attack:  7♥")
    }

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

      val output = new ByteArrayOutputStream()

      Console.withOut(new PrintStream(output)) {
        tui.processInput("show", game)
      }

      val result = output.toString("UTF-8")

      result should include("CARD GAME: DURAK")
      result should include("Opponent cards: 6")
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

      val firstCard = game.player1.getHand.head
      val input = new ByteArrayInputStream("0\n".getBytes("UTF-8"))
      val output = new ByteArrayOutputStream()

      Console.withIn(input) {
        Console.withOut(new PrintStream(output)) {
          tui.processInput("play", game)
        }
      }

      game.player1.getHandSize shouldBe 5

      val result = output.toString("UTF-8")
      result should include("Choose index")
      result should include(firstCard.toString)
    }

    "process invalid card index" in {
      val game = Game.startGame()

      val tui = new TUI(
        game.trumpCard,
        game.deck.getSize,
        game.player2.getHandSize,
        None,
        None,
        game.player1.getHand
      )

      val input = new ByteArrayInputStream("99\n".getBytes("UTF-8"))
      val output = new ByteArrayOutputStream()

      Console.withIn(input) {
        Console.withOut(new PrintStream(output)) {
          tui.processInput("play", game)
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

      val output = new ByteArrayOutputStream()

      Console.withOut(new PrintStream(output)) {
        tui.processInput("exit", game)
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

      val output = new ByteArrayOutputStream()

      Console.withOut(new PrintStream(output)) {
        tui.processInput("q", game)
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

      val output = new ByteArrayOutputStream()

      Console.withOut(new PrintStream(output)) {
        tui.processInput("invalid", game)
      }

      output.toString("UTF-8") should include("Unknown command")
    }
  }
}

