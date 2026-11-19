package model

class FailedDefense extends RoundTemplate {

  override protected def processCards(game: Game): Unit = {
    game.attackingCard.foreach { card =>
      game.defender.addCard(card)
    }

    game.defendingCard.foreach { card =>
      game.defender.addCard(card)
    }
  }
}