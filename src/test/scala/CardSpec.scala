import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class CardSpec extends AnyWordSpec with Matchers {
  "Card" should {
    "beat a lower card of the same suit" in {
      val jack = new Card(11, "Hearts")
      val seven = new Card(7, "Hearts")

      jack.canBeat(seven, "Spades") shouldBe true
    }
    "not beat higher card of the same suit" in {
      val jack = new Card(11, "Hearts")
      val seven = new Card(7, "Hearts")

      seven.canBeat(jack, "Spades") shouldBe false
    }
    "not beat a card of a different suit" in {
      val ace = new Card(14, "Spades")
      val seven = new Card(7, "Hearts")

      ace.canBeat(seven, "Diamonds") shouldBe false
    }
    "not beat a card of the same rank and suit" in {
      val nine1 = new Card(9, "Hearts")
      val nine2 = new Card(9, "Hearts")

      nine1.canBeat(nine2, "Spades") shouldBe false
    }
    "beat a non-trump card with a trump card" in {
      val six = new Card(6, "Spades")
      val ace = new Card(14, "Hearts")

      six.canBeat(ace, "Spades") shouldBe true
    }
    "not beat a higher trump card" in {
      val six = new Card(6, "Spades")
      val ten = new Card(10, "Spades")

      six.canBeat(ten, "Spades") shouldBe false
    }
    "not beat a trump card with a non-trump card" in {
      val six = new Card(6, "Spades")
      val ace = new Card(14, "Hearts")

      ace.canBeat(six, "Spades") shouldBe false
    }
    "beat a lower trump card" in {
      val six = new Card(6, "Spades")
      val ace = new Card(14, "Spades")

      ace.canBeat(six, "Spades") shouldBe true
    }
  }
}
