package model

trait GameState { //interface
  // Handles an event and returns the resulting game state.
  def handle(event: GameEvent) : GameState
  // Executes a card play according to the current game state.
  def playCard(game: Game, card: Card) : Unit
}
