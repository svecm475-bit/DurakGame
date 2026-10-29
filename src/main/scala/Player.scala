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
  def playCard(card: Card): Card = {
    if (hand.contains(card)){
      hand = hand.filterNot(_ == card)
      card
    } else {
      throw new NoSuchElementException
    }
  }

  def drawUpToSix(deck: Deck): Unit = {
    while (getHandSize < 6 && deck.getSize > 0) {
      addCard(deck.drawCard)
    }
  }
}
