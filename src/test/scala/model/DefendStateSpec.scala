
import model.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class DefendStateSpec extends AnyFunSuite {

  test("should switch to AttackState when CardDefended event occurs") {
    val state = new DefendState

    state.handle(CardDefended) shouldBe a[AttackState]
  }

  test("should switch to RoundFinishedState when RoundFinished event occurs") {
    val state = new DefendState

    state.handle(RoundFinished) shouldBe a[RoundFinishedState]
  }

  test("should remain in DefendState for an unsupported event") {
    val state = new DefendState

    state.handle(CardAttacked) shouldBe state
  }

  test("should set the defending card when playing a card") {
    val game = Game.startGame()
    val state = new DefendState
    val card = game.player1.getHand.head

    state.playCard(game, card)

    game.defendingCard shouldBe Some(card)
  }
}

