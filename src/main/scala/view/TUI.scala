package view

import model.{Card, Game}

import scala.io.StdIn.readLine
class TUI(var trumpCard: Card, var cardsInDeck: Int, var opponentCardsCount: Int,
          var attackingCard: Option[Card],
           var defendingCard: Option[Card],
           var playerHand: List[Card]) {
  // Processes user command input from the console
  def displayCard(card: Option[Card]): Unit = {
    println(DurakField.displayField(
      trumpCard,
      cardsInDeck,
      opponentCardsCount,
      card,
      defendingCard,
      playerHand
    ))
  }
  
  def displayGame(game: Game) : Unit = {
    println(DurakField.displayField(
      trumpCard = game.trumpCard,
      cardsInDeck = game.deck.getSize,
      opponentCardsCount = game.player2.getHandSize,
      attackingCard = None, // temporarily
      defendingCard = None,
      playerHand = game.player1.getHand
    ))
  }
}
