package command

import model.{Game, GameBackup, Card}
import scala.util.{Try, Success, Failure}

class PlayCardCommand(game: Game, index: Int) extends Command{
  private var backup:Option[GameBackup] =None
  private var playedCard:Option[Card] = None

  override def execute(): Try[Unit] = Try {
    val player = game.state.currentPlayer(game)
      .getOrElse(throw new IllegalStateException("No active player"))

    val savedGame = game.createBackup()

    val card = player.playCard(index)
      .getOrElse(throw new NoSuchElementException("Card not found"))

    game.playCard(card)

    backup = Some(savedGame)
    playedCard = Some(card)
  }

  override def undo(): Unit = {
    backup match {
      case Some(savedGame) => game.restoreBackup(savedGame)
      case None => throw new IllegalArgumentException("Command was not executed")
    }
  }

  def getPlayedCard: Option[Card] = playedCard

}
