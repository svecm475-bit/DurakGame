package command

import model.*
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import scala.util.Success

class PlayCardCommandSpec extends AnyWordSpec with Matchers {

  "PlayCardCommand" should {
    "play a card successfully" in {
      val game = Game.startGame()
      val firstCard = game.attacker.getHand.head
      val command = new PlayCardCommand(game, 0)

      command.execute() shouldBe Success(())

      command.getPlayedCard shouldBe Some(firstCard)
      game.attackingCard shouldBe Some(firstCard)
      game.attacker.getHand should not contain firstCard
      game.state shouldBe a[DefendState]
    }
    "restore the game after undo" in {
      val game = Game.startGame()
      val command = new PlayCardCommand(game, 0)

      val originalHand = game.player1.getHand
      val originalState = game.state
      val originalDeck = game.deck.list

      command.execute() shouldBe Success(())

      game.player1.getHand should not be originalHand
      game.state shouldBe a[DefendState]

      command.undo()

      game.player1.getHand shouldBe originalHand
      game.state shouldBe originalState
      game.deck.list shouldBe originalDeck
      game.attackingCard shouldBe None
      game.defendingCard shouldBe None
    }
    "return Failure when card index is invalid" in {
      val game = Game.startGame()
      val originalHand = game.player1.getHand
      val command = new PlayCardCommand(game, 100)

      val result = command.execute()

      result.isFailure shouldBe true
      command.getPlayedCard shouldBe None
      game.player1.getHand shouldBe originalHand
      game.attackingCard shouldBe None
      game.state shouldBe a[AttackState]
    }
    "throw an exception when undo is called before execute" in {
      val game = Game.startGame()
      val command = new PlayCardCommand(game, 0)

      val originalHand = game.player1.getHand

      intercept[IllegalArgumentException] {
        command.undo()
      }

      game.player1.getHand shouldBe originalHand
      game.state shouldBe a[AttackState]
    }
  }
}