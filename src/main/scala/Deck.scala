class Deck(var list: List[Card]) {
  // Get size of the deck
  def getSize: Int = {
    list.size
  }
  // Take 1 card from the deck
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
}
