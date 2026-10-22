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

}
