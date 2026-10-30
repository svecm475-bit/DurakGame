import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class GameSpec extends AnyWordSpec with Matchers {
  "Game" should {
    "start game" in {
      val game = Game.startGame()
      game should not be null
      game.player1 should not be null
      game.player2 should not be null
      game.deck should not be null
      game.trumpCard should not be null

      game.player1.hand.size shouldBe 6
      game.player2.hand.size shouldBe 6

    }
  }

}
