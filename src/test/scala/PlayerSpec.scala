
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PlayerSpec extends AnyWordSpec with Matchers {
  "Player" should {

    "return size of hand" in {
      val player = new Player(List.empty[Card])
      player.getHandSize shouldBe 0
    }

    "return hand" in {
      val ace = Card(14, "Spades")
      val king = Card(13, "Hearts")
      val lst = List(ace, king)

      val player = new Player(lst)
      player.getHand shouldBe lst
    }

    "add cards to hand" in {
      val ace = Card(14, "Spades")
      val king = Card(13, "Hearts")
      val player = new Player(List(ace))

      player.addCard(king)

      player.getHand shouldBe List(ace, king)
      player.getHandSize shouldBe 2
    }

    "play a card from hand" in {
      val ace = Card(14, "Spades")
      val king = Card(13, "Hearts")
      val queen = Card(12, "Diamonds")
      val player = new Player(List(ace, king, queen))

      player.playCard(1) shouldBe Some(king)
      player.getHand shouldBe List(ace, queen)
      player.getHandSize shouldBe 2
    }

    "return None for an invalid card index" in {
      val ace = Card(14, "Spades")
      val player = new Player(List(ace))

      player.playCard(5) shouldBe None
      player.playCard(-1) shouldBe None
      player.getHand shouldBe List(ace)
    }

    "draw cards until hand contains six cards" in {
      val player = new Player(List.empty[Card])
      val deck = Deck.createFull36Deck()

      player.drawUpToSix(deck)

      player.getHandSize shouldBe 6
      deck.getSize shouldBe 30
    }

    "draw only the missing cards" in {
      val initialCards = List(
        Card(6, "Hearts"),
        Card(7, "Hearts"),
        Card(8, "Hearts"),
        Card(9, "Hearts")
      )

      val player = new Player(initialCards)
      val deck = new Deck(List(
        Card(10, "Spades"),
        Card(11, "Spades"),
        Card(12, "Spades")
      ))

      player.drawUpToSix(deck)

      player.getHandSize shouldBe 6
      player.getHand.take(4) shouldBe initialCards
      deck.getSize shouldBe 1
    }

    "stop drawing when the deck is empty" in {
      val player = new Player(List.empty[Card])
      val deck = new Deck(List(
        Card(6, "Spades"),
        Card(7, "Spades")
      ))

      player.drawUpToSix(deck)

      player.getHandSize shouldBe 2
      deck.getSize shouldBe 0
    }

    "not draw cards when the hand already contains six cards" in {
      val cards = (6 to 11).map(rank => Card(rank, "Hearts")).toList
      val player = new Player(cards)
      val deck = Deck.createFull36Deck()

      player.drawUpToSix(deck)

      player.getHand shouldBe cards
      deck.getSize shouldBe 36
    }
  }
}

