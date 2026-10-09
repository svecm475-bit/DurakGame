object DurakField {

  def main(args: Array[String]): Unit = {
    // Game state variables
    val trumpCard = "10♥"
    val cardsInDeck = 24
    val opponentCards = 6

    // Cards currently played on the table
    val attackingCard = "7♦"
    val defendingCard = "Jack♦"

    // Player's hand
    val myCards = "6♥  9♣  King♦  Ace♥"

    // Construct the text view of the playing field
    val field =
      s"""
         |=== CARD GAME: DURAK ===
         |Trump: $trumpCard | Cards left in deck: $cardsInDeck
         |--------------------------------
         |Opponent cards: $opponentCards
         |
         |TABLE:
         |  Attack:  $attackingCard
         |  Defend:  $defendingCard
         |
         |YOUR HAND:
         |  $myCards
         |================================
       """.stripMargin

    // Output the field to the console
    println(field)
  }
}