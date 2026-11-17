import model.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class AttackStateSpec extends AnyFunSuite {

  test("should switch to DefendState when CardAttacked event occurs") {
    val state = new AttackState

    state.handle(CardAttacked).isInstanceOf[DefendState] shouldBe true
  }

  test("should remain in AttackState for an unsupported event") {
    val state = new AttackState

    state.handle(CardDefended) shouldBe state
  }
}