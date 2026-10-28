import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class DurakFieldSpec extends  AnyWordSpec with Matchers {
  "Deck field" should {
    "return the playing field" in {
      val field = DurakField.displayField()

      field should include("CARD GAME: DURAK")
    }
    "run the main method" in {
      DurakField.main(Array.empty[String])
    }
  }
}
