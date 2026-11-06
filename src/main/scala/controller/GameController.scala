package controller

import model.{Card, Game}
import util.Observable

class GameController (game: Game) extends Observable{
  def processPlayingCard(index: Int): Option[Card] = {
    val card = game.player1.playCard(index)

    if (card.isDefined) {
      game.attackingCard = card
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
