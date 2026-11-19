
import model.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class RoundFinishedStateSpec extends AnyFunSuite {

  test("should remain in RoundFinishedState for any event") {
    val state = new RoundFinishedState

    state.handle(CardAttacked) shouldBe state
    state.handle(CardDefended) shouldBe state
    state.handle(RoundFinished) shouldBe state
  }

  test("should print a message when trying to play a card") {
    val game = Game.startGame()
    val state = new RoundFinishedState
    val card = game.player1.getHand.head

    val output = new java.io.ByteArrayOutputStream()
    Console.withOut(output) {
      state.playCard(game, card)
    }

    output.toString should include ("Round is finished!")
  }
}

