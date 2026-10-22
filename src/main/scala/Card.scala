class Card(val rank: Int, val suit: String) {
  def canBeat(other: Card, trumpSuit: String): Boolean = {
    other.suit == this.suit && other.rank < this.rank || this.suit == trumpSuit && other.suit != trumpSuit
  }
}
