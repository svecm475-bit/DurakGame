package model

// Represents an event that can occur during the game.
trait GameEvent

// Fired when the attacking player plays a card.
case object CardAttacked extends GameEvent
// Fired when the defending player beats an attacking card.
case object CardDefended extends GameEvent
// Fired when the current round is finished.
case object RoundFinished extends GameEvent

case object RoundStarted extends GameEvent
