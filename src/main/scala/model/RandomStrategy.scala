package model

import scala.util.Random

class RandomStrategy extends BotStrategy {
  override def chooseCard(hand: List[model.Card]): Option[model.Card] = {
    Random.shuffle(hand).headOption
  }
}
