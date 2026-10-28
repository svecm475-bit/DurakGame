
class TUI(var trumpCard: Card, var cardsInDeck: Int, var opponentCardsCount: Int,
          var attackingCard: Option[Card],
           var defendingCard: Option[Card],
           var playerHand: List[Card]) {
  // Processes user command input from the console
  def processInput(input: String): Unit = {
    input match
      case "exit" => println("Exit")
      case "show" =>
        // Print the dynamic game field using current state parameters
        println(DurakField.displayField(
          trumpCard,
          cardsInDeck,
          opponentCardsCount,
          attackingCard,
          defendingCard,
          playerHand
        ))
      case _ => println("Unknown command")
  }
}
