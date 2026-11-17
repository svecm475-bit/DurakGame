package controller

import model.{AttackState, Card, CardAttacked, CardDefended, DefendState, Game}
import util.Observable

class GameController (game: Game) extends Observable{
  def processPlayingCard(index: Int): Option[Card] = {
    val card = game.player1.playCard(index)

    if (card.isDefined) {
      // Check the current game state to determine which event should be triggered.
      game.state match {
        case _: AttackState =>
          game.state.playCard(game, card.get)
          game.changeState(CardAttacked)
        case _: DefendState =>
          game.state.playCard(game, card.get)
          game.changeState(CardDefended)
      }
      game.printState()
      notifyObservers()
    }

    card
  }

  def getPlayerHand: List[Card] = {
    game.player1.getHand
  }

  def getTrumpCard: Card = game.trumpCard

  def getCardsInDeck: Int = game.deck.getSize

  def getOpponentCardsCount: Int = game.player2.getHandSize

  def getAttackingCard: Option[Card] = game.attackingCard

  def getDefendingCard: Option[Card] = game.defendingCard
}
