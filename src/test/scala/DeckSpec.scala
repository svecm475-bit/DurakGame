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
    }
    "throw an exception when drawing from an empty deck" in {
      val lst = List.empty[Card]

      val deck = new Deck(lst)
      intercept[NoSuchElementException] {
        deck.drawCard
      }
    }
  }
}
