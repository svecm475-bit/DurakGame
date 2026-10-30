import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class GameSpec extends AnyWordSpec with Matchers {

  "Game" should {

    "start a new game with two players, a deck and a trump card" in {
      val game = Game.startGame()

      game should not be null
      game.player1 should not be null
      game.player2 should not be null
      game.deck should not be null
      game.trumpCard should not be null
    }

    "deal six cards to each player" in {
      val game = Game.startGame()

      game.player1.hand.size shouldBe 6
      game.player2.hand.size shouldBe 6
    }

    "create two different players" in {
      val game = Game.startGame()

      game.player1 should not be theSameInstanceAs(game.player2)
    }

    "leave cards in the deck after dealing" in {
      val game = Game.startGame()

      game.deck.getSize shouldBe 24
    }
  }
}
