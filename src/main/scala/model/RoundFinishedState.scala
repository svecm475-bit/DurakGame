package model

class RoundFinishedState extends GameState {
  override def handle(event: GameEvent): GameState = this

  override def playCard(game: Game, card: Card): Unit = {
    println("Round is finished!")
  }
}
