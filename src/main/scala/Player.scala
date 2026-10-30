class Player(var hand: List[Card]) {
  def getHandSize: Int = {
    hand.size
  }
  def getHand: List[Card] = {
    hand
  }
  def addCard(card: Card): Unit = {
    hand = hand.appended(card)
  }
  def playCard(index: Int): Option[Card] = {
    if (hand.contains(hand(index))){
      hand = hand.filterNot(_ == hand(index))
      hand.lift(index)
    } else {
      println("No Card found")
      None
    }
  }

  def drawUpToSix(deck: Deck): Unit = {
    while (getHandSize < 6 && deck.getSize > 0) {
      addCard(deck.drawCard)
    }
  }
}
