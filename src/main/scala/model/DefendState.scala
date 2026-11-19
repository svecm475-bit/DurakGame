package model

class DefendState extends GameState {
  override def handle(event: GameEvent): GameState =
    event match {
      case CardDefended => new AttackState()
      case RoundFinished => new RoundFinishedState()
      case _ => this
    }
  override def playCard(game: Game, card: Card): Unit = {
    game.defendingCard = Some(card)
    game.changeState(CardDefended)
  }

  override def currentPlayer(game: Game): Option[Player] = {
    Some(game.defender)
  }
}
