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
    hand.lift(index) match {
      case Some(card) =>
        hand = hand.patch(index, Nil, 1)
        Some(card)

      case None =>
        println("No card found")
        None
    }
  
  }

  def drawUpToSix(deck: Deck): Unit = {
    while (getHandSize < 6 && deck.getSize > 0) {
      addCard(deck.drawCard)
    }
  }
}
