object DurakField {
  def displayField(
    // Redners the current state of the game board into a formatted string
    trumpCard: Card,
    cardsInDeck: Int,
    opponentCardsCount: Int,
    attackingCard: Option[Card],
    defendingCard: Option[Card],
    playerHand: List[Card]
                  ): String = {

    // Format attacking card, show dash if empty
    val attackStr = if attackingCard.isDefined
      then attackingCard.get.toString
      else "-"

    val defendStr = if defendingCard.isDefined
      then defendingCard.get.toString
      else "-"


    // Combine all cards in the player's hand into a single string
    var handStr = ""
    for(card <- playerHand) {
      handStr = handStr + card.toString + " "
    }
    // Construct the text view of the playing field
    val field = {
      s"""
         |=== CARD GAME: DURAK ===
         |Trump: $trumpCard| Cards left in deck: $cardsInDeck
         |--------------------------------
         |Opponent cards: $opponentCardsCount
         |
         |TABLE:
         |  Attack:  $attackStr
         |  Defend:  $defendStr
         |
         |YOUR HAND:
         |  $handStr
         |================================
           """.stripMargin
    }
    // Return field
    field
  }
}