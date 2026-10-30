import scala.annotation.tailrec
import scala.io.StdIn.readLine

@main def main(): Unit =
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

    if input != "exit" then gameLoop()

  gameLoop()
