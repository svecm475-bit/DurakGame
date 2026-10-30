import scala.io.StdIn.readLine
class TUI(var trumpCard: Card, var cardsInDeck: Int, var opponentCardsCount: Int,
          var attackingCard: Option[Card],
           var defendingCard: Option[Card],
           var playerHand: List[Card]) {
  // Processes user command input from the console
  def displayCard(card: Option[Card]): Unit = {
    println(DurakField.displayField(
      trumpCard,
      cardsInDeck,
      opponentCardsCount,
      card,
      defendingCard,
      playerHand
    ))
  }
  def processPlayingCard(game: Game): Unit = {
    println("Choose index of the card you want to play:")
    val index = readLine().toInt
    println(index)
    val card: Option[Card] = game.player1.playCard(index)
    displayCard(card)
  }
  def processInput(input: String, game: Game): Unit = {
    input match
      case "exit" | "q" => println("Exiting...")
      case "show" =>
        // Print the dynamic game field using current state parameters
        println(DurakField.displayField(
          game.trumpCard,
          game.deck.getSize,
          game.player2.getHandSize,
          attackingCard,
          defendingCard,
          game.player1.getHand
        ))
      case "play" => processPlayingCard(game)
      case _ => println("Unknown command")
  }
}
