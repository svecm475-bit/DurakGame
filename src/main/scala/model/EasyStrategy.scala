package model

class EasyStrategy extends BotStrategy {
  override def chooseCard(hand: List[Card]): Option[Card] = {
    hand.headOption
  }
}
