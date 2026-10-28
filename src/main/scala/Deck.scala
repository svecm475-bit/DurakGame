class Deck(var list: List[Card]) {
  def getSize: Int = {
    list.size
  }
  def drawCard: Card = {
    if(this.getSize == 0){
      throw new NoSuchElementException("Deck is empty")
    }
    val card = list.head
    // new list
    list = list.tail
    // return Card
    card
  }
  // Shuffles the remaining cards in the deck randomly
  def shuffle(): Unit = {
    list = scala.util.Random.shuffle(list)
  }

  def drawCards(amount: Int): List[Card] = {
    var drawn = List.empty[Card]
    while (drawn.size < amount && list.nonEmpty) {
      val card = drawCard
      drawn = drawn.appended(card)
    }
    drawn
  }
}
object Deck {
  // Method to create a standard 36-card deck using for-expression
  def createFull36Deck(): Deck ={
    val suits = List("♥", "♦", "♣", "♠")
    val ranks = (6 to 14).toList

    // Generate all 36 Card objects by iterating over suits and ranks
    val allCards = for (suit <- suits; rank <- ranks) yield Card(rank,suit)

    new Deck(allCards)
  }
}
