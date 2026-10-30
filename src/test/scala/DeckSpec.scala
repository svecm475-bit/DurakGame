import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class DeckSpec extends AnyWordSpec with Matchers {
  "Deck" should {
    "return the correct number of cards" in {
      val card1 = new Card(14, "Spades")
      val card2 = new Card(13, "Spades")
      val card3 = new Card(12, "Spades")

      val lst = List(card1, card2, card3)

      val deck = new Deck(lst)

      deck.getSize shouldBe 3
    }
    "return zero for an empty deck" in {
      val lst = List.empty[Card]

      val deck = new Deck(lst)

      deck.getSize shouldBe 0
    }
    "draw the first card from the deck" in {
      val ace = new Card(14, "Spades")
      val king = new Card(13, "Hearts")
      val lst = List(ace, king)

      val deck = new Deck(lst)

      deck.drawCard shouldBe ace
      deck.getSize shouldBe 1
      deck.list.head shouldBe king
      deck.drawTrumpCard shouldBe king
    }
    "throw an exception when drawing from an empty deck" in {
      val lst = List.empty[Card]

      val deck = new Deck(lst)
      intercept[NoSuchElementException] {
        deck.drawCard
      }
    }
    "shuffle the deck without losing cards" in {
      val ace = Card(14, "Spades")
      val king = Card(13, "Hearts")
      val queen = Card(12, "Diamonds")

      val originalCards = List(ace, king, queen)
      val deck = new Deck(originalCards)

      deck.shuffle()

      deck.getSize shouldBe 3
      deck.list.toSet shouldBe originalCards.toSet
    }

    "shuffle an empty deck" in {
      val deck = new Deck(List.empty[Card])

      deck.shuffle()

      deck.getSize shouldBe 0
      deck.list shouldBe empty
    }

    "create a full 36 card deck" in {
      val deck = Deck.createFull36Deck()

      deck.getSize shouldBe 36

      val suits = List("♥", "♦", "♣", "♠")
      val ranks = 6 to 14

      for (suit <- suits; rank <- ranks) {
        deck.list should contain(Card(rank, suit))
      }

      deck.list.distinct.size shouldBe 36
    }

    "keep the trump card as the last card" in {
      val ace = Card(14, "Spades")
      val king = Card(13, "Hearts")
      val queen = Card(12, "Diamonds")

      val deck = new Deck(List(ace, king, queen))

      val trump = deck.drawTrumpCard

      trump shouldBe ace
      deck.getSize shouldBe 3
      deck.list shouldBe List(king, queen, ace)
    }

    "throw an exception when choosing trump from an empty deck" in {
      val deck = new Deck(List.empty[Card])

      intercept[NoSuchElementException] {
        deck.drawTrumpCard
      }
    }

  }
}
