import scala.annotation.tailrec
import scala.io.StdIn.readLine

@main def main(): Unit =
  // Create a full 36-card deck and snuffle it
  val deck = Deck.createFull36Deck()
  deck.shuffle()
  val trumpCard = deck.drawTrumpCard

  // Give cards to the player
  val player1 = new Player(List.empty[Card])
  player1.drawUpToSix(deck)
  val player2 = new Player(List.empty[Card])
  player2.drawUpToSix(deck)

  //Initialize TUI with the current game state variables
  val tui = TUI(
    trumpCard = trumpCard,
    cardsInDeck = deck.getSize,
    opponentCardsCount = player2.getHandSize,
    attackingCard = None, // temporarily
    defendingCard = None,
    playerHand = player1.getHand
  )

  // Display the game field
  println(DurakField.displayField(
    trumpCard = trumpCard,
    cardsInDeck = deck.getSize,
    opponentCardsCount = player2.getHandSize,
    attackingCard = None, // temporarily
    defendingCard = None,
    playerHand = player1.getHand
  ))

  println("Print command:")

  // Main game loop for handling user console commands
  @tailrec
  def gameLoop(): Unit =
    print("> ")
    val input = readLine()
    tui.processInput(input)

    if input != "exit" && input != "q" then gameLoop()

  gameLoop()
