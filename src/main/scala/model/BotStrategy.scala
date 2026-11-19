package model

trait BotStrategy {
  def chooseCard(hand: List[Card]): Option[Card]
}
