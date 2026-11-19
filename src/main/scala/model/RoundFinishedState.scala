package model

class RoundFinishedState extends GameState {
  override def handle(event: GameEvent): GameState =
    event match {
      case RoundStarted => new AttackState()
      case _ => this
    }
  override def playCard(game: Game, card: Card): Unit = {
    println("Round is finished!")
  }
  override def currentPlayer(game: Game): Option[Player] = {
    None
  }
}
