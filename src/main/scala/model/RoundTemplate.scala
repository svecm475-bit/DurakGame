package model

abstract class RoundTemplate {

  final def finishRound(game: Game): Unit = {
    game.changeState(RoundFinished)
    processCards(game)
    drawCards(game)
    startNextRound(game)
    updateRoles(game)
  }

  protected def processCards(game: Game): Unit

  private def drawCards(game: Game): Unit = {
    game.attacker.drawUpToSix(game.deck)
    game.defender.drawUpToSix(game.deck)
  }

  private def startNextRound(game: Game): Unit = {
    game.attackingCard = None
    game.defendingCard = None
    game.changeState(RoundStarted)
  }

  protected def updateRoles(game: Game): Unit = {
    // pass
  }
}