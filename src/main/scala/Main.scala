import scala.annotation.tailrec
import scala.io.StdIn.readLine

@main def main(): Unit =
  // Create a full 36-card deck and snuffle it
//  val player1 = new Player(List.empty[Card])
//  val player2 = new Player(List.empty[Card])
//  val deck = Deck.createFull36Deck()
//  deck.shuffle()
//  val trumpCard = deck.drawTrumpCard
//  player1.drawUpToSix(deck)
//  player2.drawUpToSix(deck)
  val game = Game.startGame()

  //Initialize TUI with the current game state variables
  val tui = TUI(
    trumpCard = game.trumpCard,
    cardsInDeck = game.deck.getSize,
    opponentCardsCount = game.player2.getHandSize,
    attackingCard = None, // temporarily
    defendingCard = None,
    playerHand = game.player1.getHand
  )

  // Display the game field
  println(DurakField.displayField(
    trumpCard = game.trumpCard,
    cardsInDeck = game.deck.getSize,
    opponentCardsCount = game.player2.getHandSize,
    attackingCard = None, // temporarily
    defendingCard = None,
    playerHand = game.player1.getHand
  ))

  println("Print command:")

  // Main game loop for handling user console commands
  @tailrec
  def gameLoop(): Unit =
    print("> ")
    val input = readLine()
    tui.processInput(input, game)

    if input != "exit" && input != "q" then gameLoop()

  gameLoop()
