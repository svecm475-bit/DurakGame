package model

class AttackState extends GameState {
  override def handle(event: GameEvent): GameState =
    event match {
      case CardAttacked => new DefendState()
      case _ => this
    }

  override def playCard(game: Game, card: Card): Unit = {
    game.attackingCard = Some(card)
    game.changeState(CardAttacked)
  }

  override def currentPlayer(game: Game): Option[Player] = {
    Some(game.attacker)
  }
}
