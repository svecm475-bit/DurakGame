class Game(val player1: Player,
                val player2: Player,
                val deck: Deck,
                val trumpCard: Card) 

object Game {
  def startGame(): Game = {
    val player1 = new Player(List.empty[Card])
    val player2 = new Player(List.empty[Card])
    val deck = Deck.createFull36Deck()
    deck.shuffle()
    val trumpCard = deck.drawTrumpCard
    player1.drawUpToSix(deck)
    player2.drawUpToSix(deck)

    new Game(player1, player2, deck, trumpCard)
  }
}
