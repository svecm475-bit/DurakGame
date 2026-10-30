import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DurakFieldSpec extends  AnyWordSpec with Matchers {
  "Deck field" should {
    "return the playing field" in {
      val trumpCard = Card(13, "Spades")
      val card1 = Card(14, "Spades")
      val card2 = Card(13, "Spades")
      val card3 = Card(12, "Spades")

      val lst = List(card1, card2, card3)
      val field = DurakField.displayField(trumpCard, 24, 3, None, None, lst)

      field should include("CARD GAME: DURAK")
    }
  }
}
