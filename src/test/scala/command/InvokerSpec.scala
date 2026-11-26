package command

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Failure, Success, Try}

class InvokerSpec extends AnyWordSpec with Matchers {

  "Invoker" should {
    "execute a command and undo it" in {
      val invoker = new Invoker()
      var value = 0

      val command = new Command {
        override def execute() = {
          value += 1
          Success(())
        }

        override def undo(): Unit = {
          value -= 1
        }
      }

      invoker.doStep(command) shouldBe Success(())
      value shouldBe 1

      invoker.undoStep()
      value shouldBe 0
    }
    "not save a failed command in undo history" in {
      val invoker = new Invoker()
      var undoCalled = false

      val command = new Command {
        override def execute(): Try[Unit] =
          Failure(new IllegalArgumentException("Invalid move"))

        override def undo(): Unit = {
          undoCalled = true
        }
      }

      invoker.doStep(command).isFailure shouldBe true

      invoker.undoStep()

      undoCalled shouldBe false
    }
    "handle undo when history is empty" in {
      val invoker = new Invoker()
      val output = new java.io.ByteArrayOutputStream()

      Console.withOut(output) {
        invoker.undoStep()
      }

      output.toString("UTF-8") should include("Nothing to undo")
    }
  }
}