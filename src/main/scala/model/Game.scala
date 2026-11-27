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
  def finishRound(successfulDefense: Boolean): Unit = {
    val round: RoundTemplate =
      if (successfulDefense) new SuccessfulDefense()
      else new FailedDefense()

    round.finishRound(this)
  }
  def createBackup(): GameBackup = {
    GameBackup(
      player1Hand = player1.getHand,
      player2Hand = player2.getHand,
      deckCards = deck.list,
      discardCards = discardPile,
      attacker = attacker,
      defender = defender,
      state = state,
      attackingCard = attackingCard,
      defendingCard = defendingCard
    )
  }
  def restoreBackup(backup: GameBackup): Unit = {
    player1.hand = backup.player1Hand
    player2.hand = backup.player2Hand
    deck.list = backup.deckCards
    discardPile = backup.discardCards
    attacker = backup.attacker
    defender = backup.defender
    state = backup.state
    attackingCard = backup.attackingCard
    defendingCard = backup.defendingCard
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
