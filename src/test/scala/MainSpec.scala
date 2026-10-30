import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class MainSpec extends AnyWordSpec with Matchers {

  "Main" should {

    "start the game and exit" in {
      val input = new ByteArrayInputStream("exit\n".getBytes("UTF-8"))
      val output = new ByteArrayOutputStream()

      Console.withIn(input) {
        Console.withOut(new PrintStream(output)) {
          main()
        }
      }

      val result = output.toString("UTF-8")

      result should include("CARD GAME: DURAK")
      result should include("Print command:")
      result should include("Exiting...")
    }

    "process multiple commands and quit" in {
      val input = new ByteArrayInputStream("show\nq\n".getBytes("UTF-8"))
      val output = new ByteArrayOutputStream()

      Console.withIn(input) {
        Console.withOut(new PrintStream(output)) {
          main()
        }
      }

      val result = output.toString("UTF-8")

      result should include("CARD GAME: DURAK")
      result should include("Print command:")
      result should include("Exiting...")
    }

    "process an unknown command and exit" in {
      val input = new ByteArrayInputStream("invalid\nexit\n".getBytes("UTF-8"))
      val output = new ByteArrayOutputStream()

      Console.withIn(input) {
        Console.withOut(new PrintStream(output)) {
          main()
        }
      }

      val result = output.toString("UTF-8")

      result should include("Unknown command")
      result should include("Exiting...")
    }
  }
}

