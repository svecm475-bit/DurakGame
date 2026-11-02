
import model.Card
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import view.TUI

import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TUISpec extends AnyWordSpec with Matchers {

  "view.TUI" should {

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
  }
}