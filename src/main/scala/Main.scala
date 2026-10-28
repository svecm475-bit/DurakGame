import scala.annotation.tailrec
import scala.io.StdIn.readLine

@main def main(): Unit =
  // Create a full 36-card deck and snuffle it
  val deck = Deck.createFull36Deck()
  deck.shuffle()

  // Give cards to the player and pick a trump card
  val myHand = deck.drawCards(6)
  val trumpCard = deck.drawCard

  //Initialize TUI with the current game state variables
  val tui = TUI(
    trumpCard = trumpCard,
    cardsInDeck = deck.getSize,
    opponentCardsCount = 6,
    attackingCard = None, // temporarily
    defendingCard = None,
    playerHand = myHand
  )

  // Display the game field
  println(DurakField.displayField(
    trumpCard = trumpCard,
    cardsInDeck = deck.getSize,
    opponentCardsCount = 6,
    attackingCard = None, // temporarily
    defendingCard = None,
    playerHand = myHand
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
