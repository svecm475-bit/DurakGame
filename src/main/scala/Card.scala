// case class automatically generates getters (val), equals, hashCode, toString, and copy methods
case class Card(rank: Int,suit: String) {
  def canBeat(other: Card, trumpSuit: String): Boolean = {
    other.suit == this.suit && other.rank < this.rank || this.suit == trumpSuit && other.suit != trumpSuit
  }
  // Returns a readable string representation of the card (6♥,J♠,A♦)
  override def toString: String = {
    val rankStr = rank match
      case 11 => "J"
      case 12 => "Q"
      case 13 => "K"
      case 14 => "A"
      case r => r.toString
    s"$rankStr$suit"
  }
}
