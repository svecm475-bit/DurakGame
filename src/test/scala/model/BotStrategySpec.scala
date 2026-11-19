package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class BotStrategySpec extends AnyWordSpec with Matchers {
  "EasyStrategy" should {
    "choose the first card" in {
      val hand = List(Card(7, "♥"), Card(8, "♠"))
      val strategy = new EasyStrategy()

      strategy.chooseCard(hand) shouldBe Some(Card(7, "♥"))
    }
    "return None when hand is empty" in {
      val strategy = new EasyStrategy()

      strategy.chooseCard(List.empty[Card]) shouldBe None
    }
  }
  "RandomStrategy" should {
    "choose a card from the hand" in {
      val hand = List(Card(7, "♥"), Card(8, "♠"), Card(9, "♦"))
      val strategy = new RandomStrategy()

      val result = strategy.chooseCard(hand)

      result should not be None
      hand should contain(result.get)
    }

    "return None when hand is empty" in {
      val strategy = new RandomStrategy()

      strategy.chooseCard(List.empty[Card]) shouldBe None
    }
  }
}
