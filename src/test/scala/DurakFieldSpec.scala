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
    "display attacking and defending cards" in {
      val trump = Card(14, "♠")
      val attack = Card(7, "♥")
      val defend = Card(10, "♥")
      val hand = List(Card(6, "♣"), Card(13, "♦"))

      val field = DurakField.displayField(
        trump, 24, 5, Some(attack), Some(defend), hand
      )

      field should include("Attack:  7♥")
      field should include("Defend:  10♥")
      field should include("6♣")
      field should include("K♦")
      field should include("Opponent cards: 5")
      field should include("Cards left in deck: 24")
    }

    "display empty hand and no defending card" in {
      val trump = Card(14, "♠")
      val attack = Card(9, "♣")

      val field = DurakField.displayField(
        trump, 0, 0, Some(attack), None, List.empty[Card]
      )

      field should include("Attack:  9♣")
      field should include("Defend:  -")
      field should include("Opponent cards: 0")
      field should include("Cards left in deck: 0")
    }

  }
}
