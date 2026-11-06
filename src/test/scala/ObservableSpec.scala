import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import util.{Observable, Observer}

class ObservableSpec extends AnyWordSpec with Matchers {

  "Observable" should {

    "remove an observer" in {
      val observable = new Observable()
      var count = 0

      val observer = new Observer {
        override def update(): Unit = {
          count += 1
        }
      }

      observable.add(observer)
      observable.notifyObservers()

      count shouldBe 1

      observable.remove(observer)
      observable.notifyObservers()

      count shouldBe 1
    }
  }
}
