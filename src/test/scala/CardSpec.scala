import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class CardSpec extends AnyWordSpec with Matchers {
  "Card" should {
    "beat a lower card of the same suit" in {
      val jack = new Card(11, "♥")
      val seven = new Card(7, "♥")

      jack.canBeat(seven, "♠") shouldBe true
    }
    "not beat higher card of the same suit" in {
      val jack = new Card(11, "♥")
      val seven = new Card(7, "♥")

      seven.canBeat(jack, "♠") shouldBe false
    }
    "not beat a card of a different suit" in {
      val ace = new Card(14, "♠")
      val seven = new Card(7, "♥")

      ace.canBeat(seven, "♦") shouldBe false
    }
    "not beat a card of the same rank and suit" in {
      val nine1 = new Card(9, "♥")
      val nine2 = new Card(9, "♥")

      nine1.canBeat(nine2, "♠") shouldBe false
    }
    "beat a non-trump card with a trump card" in {
      val six = new Card(6, "♠")
      val ace = new Card(14, "♥")

      six.canBeat(ace, "♠") shouldBe true
    }
    "not beat a higher trump card" in {
      val six = new Card(6, "♠")
      val ten = new Card(10, "♠")

      six.canBeat(ten, "♠") shouldBe false
    }
    "not beat a trump card with a non-trump card" in {
      val six = new Card(6, "♠")
      val ace = new Card(14, "♥")

      ace.canBeat(six, "♠") shouldBe false
    }
    "beat a lower trump card" in {
      val six = new Card(6, "♠")
      val ace = new Card(14, "♠")

      ace.canBeat(six, "♠") shouldBe true
    }
    "display right suit" in {
      val ace = new Card(14, "♥")
      val king = new Card(13, "♥")
      val queen = new Card(12, "♥")
      val jack = new Card(11, "♥")
      val ten = new Card(10, "♥")
      ace.toString shouldBe "A♥"
      king.toString shouldBe "K♥"
      queen.toString shouldBe "Q♥"
      jack.toString shouldBe "J♥"
      ten.toString shouldBe "10♥"
    }
    "not beat a different non-trump suit" in {
      val ten = Card(10, "♠")
      val seven = Card(7, "♥")

      ten.canBeat(seven, "♦") shouldBe false
    }

    "not beat a higher card of the same non-trump suit" in {
      val six = Card(6, "♥")
      val ace = Card(14, "♥")

      six.canBeat(ace, "♠") shouldBe false
    }

    "not beat a card of equal rank in the same trump suit" in {
      val card1 = Card(9, "♠")
      val card2 = Card(9, "♠")

      card1.canBeat(card2, "♠") shouldBe false
    }

    "beat a lower non-trump card of the same suit" in {
      val ten = Card(10, "♥")
      val seven = Card(7, "♥")

      ten.canBeat(seven, "♠") shouldBe true
    }

    "not beat a higher card of another suit even when the other card is trump" in {
      val ace = Card(14, "♥")
      val six = Card(6, "♠")

      ace.canBeat(six, "♠") shouldBe false
    }
  }
}
