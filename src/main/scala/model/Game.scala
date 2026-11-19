package model

class Game(val player1: Player,
            val player2: Player,
            val deck: Deck,
            val trumpCard: Card,
            var attackingCard: Option[Card]=None,
            var defendingCard: Option[Card]=None
          ) {
  var state : GameState = new AttackState()
  var discardPile: List[Card] = List.empty[Card]
  var attacker : Player = player1
  var defender : Player = player2

  def playCard(card: Card): Unit = {
    state.playCard(this, card)
  }
  def changeState(event: GameEvent): Unit = {
    state = state.handle(event)
  }
}

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
