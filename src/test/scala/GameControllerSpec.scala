
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import controller.GameController
import model.{Card, Deck, Game, Player}
import util.Observer

class GameControllerSpec extends AnyWordSpec with Matchers {

  // Creates a predictable game for testing
  private def createGame(): Game = {
    val player1 = new Player(List(
      Card(7, "♥"),
      Card(8, "♣"),
      Card(9, "♦")
    ))

    val player2 = new Player(List(
      Card(6, "♠"),
      Card(10, "♥")
    ))

    val deck = Deck.createFull36Deck()
    val trumpCard = Card(14, "♠")

    new Game(player1, player2, deck, trumpCard)
  }

  "GameController" should {

    "return the player's hand" in {
      val game = createGame()
      val controller = new GameController(game)

      controller.getPlayerHand shouldBe List(
        Card(7, "♥"),
        Card(8, "♣"),
        Card(9, "♦")
      )
    }

    "return the trump card" in {
      val game = createGame()
      val controller = new GameController(game)

      controller.getTrumpCard shouldBe game.trumpCard
    }

    "return the number of cards in the deck" in {
      val game = createGame()
      val controller = new GameController(game)

      controller.getCardsInDeck shouldBe game.deck.getSize
    }

    "return the number of opponent cards" in {
      val game = createGame()
      val controller = new GameController(game)

      controller.getOpponentCardsCount shouldBe 2
    }

    "return an empty attacking card initially" in {
      val controller = new GameController(createGame())

      controller.getAttackingCard shouldBe None
    }

    "return an empty defending card initially" in {
      val controller = new GameController(createGame())

      controller.getDefendingCard shouldBe None
    }

    "return the attacking card after it changes" in {
      val game = createGame()
      val controller = new GameController(game)

      val card = Card(7, "♥")
      game.attackingCard = Some(card)

      controller.getAttackingCard shouldBe Some(card)
    }

    "return the defending card after it changes" in {
      val game = createGame()
      val controller = new GameController(game)

      val card = Card(8, "♥")
      game.defendingCard = Some(card)

      controller.getDefendingCard shouldBe Some(card)
    }

    "play a valid card and update the game" in {
      val game = createGame()
      val controller = new GameController(game)

      val result = controller.processPlayingCard(0)

      result shouldBe Some(Card(7, "♥"))
      controller.getAttackingCard shouldBe Some(Card(7, "♥"))
      controller.getPlayerHand shouldBe List(
        Card(8, "♣"),
        Card(9, "♦")
      )
    }

    "notify observers after a successful move" in {
      val controller = new GameController(createGame())

      var notificationCount = 0

      val observer = new Observer {
        override def update(): Unit = {
          notificationCount += 1
        }
      }

      controller.add(observer)
      controller.processPlayingCard(0)

      notificationCount shouldBe 1
    }

    "not change the game when the card index is invalid" in {
      val game = createGame()
      val controller = new GameController(game)

      val originalHand = controller.getPlayerHand

      val result = controller.processPlayingCard(99)

      result shouldBe None
      controller.getPlayerHand shouldBe originalHand
      controller.getAttackingCard shouldBe None
    }

    "not notify observers after an invalid move" in {
      val controller = new GameController(createGame())

      var notificationCount = 0

      val observer = new Observer {
        override def update(): Unit = {
          notificationCount += 1
        }
      }

      controller.add(observer)
      controller.processPlayingCard(99)

      notificationCount shouldBe 0
    }
  }
}
