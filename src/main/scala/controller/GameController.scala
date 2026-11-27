package controller

import command.{Invoker, PlayCardCommand}
import model.{Card, Game}
import util.Observable

import scala.util.{Failure, Success}

class GameController (game: Game) extends Observable{
  private val invoker = new Invoker()
  def processPlayingCard(index: Int): Option[Card] = {
    val command = new PlayCardCommand(game, index)

    invoker.doStep(command) match {
      case Success(_) =>
        notifyObservers()
        command.getPlayedCard

      case Failure(error) =>
        println(s"Cannot play card: ${error.getMessage}")
        None
    }
  }

  def undo(): Unit = {
    invoker.undoStep()
    notifyObservers()
  }

  def getPlayerHand: List[Card] = {
    game.player1.getHand
  }

  def finishRound(successfulDefense: Boolean): Unit = {
    game.finishRound(successfulDefense)
    notifyObservers()
  }
  def getTrumpCard: Card = game.trumpCard

  def getCardsInDeck: Int = game.deck.getSize

  def getOpponentCardsCount: Int = game.player2.getHandSize

  def getAttackingCard: Option[Card] = game.attackingCard

  def getDefendingCard: Option[Card] = game.defendingCard
}
