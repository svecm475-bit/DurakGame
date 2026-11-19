package model

class SuccessfulDefense extends RoundTemplate {
  override protected def processCards(game: Game): Unit = {
    game.attackingCard.foreach { card =>
      game.discardPile = game.discardPile :+ card
    }

    game.defendingCard.foreach { card =>
      game.discardPile = game.discardPile :+ card
    }
  }

  override protected def updateRoles(game: Game): Unit = {
    val previousAttacker = game.attacker

    game.attacker = game.defender
    game.defender = previousAttacker
  }
}