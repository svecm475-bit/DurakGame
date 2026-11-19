package model

import model.{Card, Deck, Game, Player}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameSpec extends AnyWordSpec with Matchers {

  "Game" should {

    "start a new game with two players, a deck and a trump card" in {
      val game = Game.startGame()

      game should not be null
      game.player1 should not be null
      game.player2 should not be null
      game.deck should not be null
      game.trumpCard should not be null
    }

    "deal six cards to each player" in {
      val game = Game.startGame()

      game.player1.hand.size shouldBe 6
      game.player2.hand.size shouldBe 6
    }

    "create two different players" in {
      val game = Game.startGame()

      game.player1 should not be theSameInstanceAs(game.player2)
    }

    "leave cards in the deck after dealing" in {
      val game = Game.startGame()

      game.deck.getSize shouldBe 24
    }
    "initialize class" in {
      val ace = Card(14, "Spades")
      val king = Card(13, "Hearts")
      val lst = List(ace, king)

      val deck = new Deck(lst)
      val player1 = new Player(List.empty[Card])
      val player2 = new Player(List.empty[Card])

      val trumpCard = Card(12, "Hearts")

      val game = new Game(player1, player2, deck, trumpCard)

      game.player1 shouldBe player1
      game.player2 shouldBe player2
      game.deck shouldBe deck
      game.trumpCard shouldBe trumpCard
    }
    "switch between attack and defend states" in {
      val game = Game.startGame()

      game.state shouldBe a[AttackState]

      val attackCard = game.player1.getHand.head
      game.playCard(attackCard)

      game.state shouldBe a[DefendState]
      game.attackingCard shouldBe Some(attackCard)

      val defendCard = game.player2.getHand.head
      game.playCard(defendCard)

      game.state shouldBe a[AttackState]
      game.defendingCard shouldBe Some(defendCard)
    }
    "finish a round and start a new one" in {
      val game = Game.startGame()

      game.playCard(game.player1.getHand.head)
      game.state shouldBe a[DefendState]

      game.changeState(RoundFinished)
      game.state shouldBe a[RoundFinishedState]

      game.changeState(RoundStarted)
      game.state shouldBe a[AttackState]
    }
  }
}
