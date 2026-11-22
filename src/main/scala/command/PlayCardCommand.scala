package command

import model.{Game, GameBackup, Card}


class PlayCardCommand(game: Game, index: Int) extends Command{
  private var backup:Option[GameBackup] =None
  private var playedCard:Option[Card] = None

  override def execute(): Unit = {
    // Create a backup before changing the game
    backup = Some(game.createBackup())
    // Get the selected card from the current player's hand
    val card = game.state.currentPlayer(game).flatMap(_.playCard(index))
      .getOrElse(throw new NoSuchElementException("Card not found"))

    playedCard = Some(card)
    game.playCard(card)
  }

  override def undo(): Unit = {
    backup match {
      case Some(savedGame) => game.restoreBackup(savedGame)
      case None => throw new IllegalArgumentException("Command was not executed")
    }
  }

  def getPlayedCard: Option[Card] = playedCard

}
