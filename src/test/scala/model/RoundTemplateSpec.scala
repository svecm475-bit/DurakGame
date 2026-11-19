package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class RoundTemplateSpec extends AnyWordSpec with Matchers {

  "SuccessfulDefense" should {
    "finish the round and switch player roles" in {
      val game = Game.startGame()

      val originalAttacker = game.attacker
      val originalDefender = game.defender

      game.attackingCard = Some(Card(7, "♥"))
      game.defendingCard = Some(Card(8, "♥"))

      val round = new SuccessfulDefense()
      round.finishRound(game)

      game.attacker shouldBe originalDefender
      game.defender shouldBe originalAttacker

      game.discardPile should contain (Card(7, "♥"))
      game.discardPile should contain (Card(8, "♥"))

      game.attackingCard shouldBe None
      game.defendingCard shouldBe None
      game.state shouldBe a[AttackState]
    }
  }
  "FailedDefense" should {
    "finish the round without switching player roles" in {
      val game = Game.startGame()

      val originalAttacker = game.attacker
      val originalDefender = game.defender

      val attackCard = Card(7, "♥")
      val defenseCard = Card(8, "♥")

      game.attackingCard = Some(attackCard)
      game.defendingCard = Some(defenseCard)

      val initialHandSize = game.defender.getHandSize

      val round = new FailedDefense()
      round.finishRound(game)

      game.attacker shouldBe originalAttacker
      game.defender shouldBe originalDefender

      game.defender.getHand should contain(attackCard)
      game.defender.getHand should contain(defenseCard)
      game.defender.getHandSize shouldBe initialHandSize + 2

      game.attackingCard shouldBe None
      game.defendingCard shouldBe None
      game.state shouldBe a[AttackState]
    }
  }
}