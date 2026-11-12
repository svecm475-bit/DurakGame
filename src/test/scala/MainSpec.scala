import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}

class MainSpec extends AnyWordSpec with Matchers {

  private def runMain(commands: String): String = {
    val input = new ByteArrayInputStream(commands.getBytes("UTF-8"))
    val output = new ByteArrayOutputStream()
    val stream = new PrintStream(output, true, "UTF-8")

    try {
      Console.withIn(input) {
        Console.withOut(stream) {
          main()
        }
      }

      output.toString("UTF-8")
    } finally {
      stream.close()
      input.close()
    }
  }

  "Main" should {

    "start the game and exit" in {
      val result = runMain("exit\n")

      result should include("CARD GAME: DURAK")
      result should include("Exiting...")
    }

    "process multiple commands and quit" in {
      val result = runMain("show\nq\n")

      result should include("CARD GAME: DURAK")
      result should include("Exiting...")
    }

    "process an unknown command and exit" in {
      val result = runMain("invalid\nexit\n")

      result should include("Unknown command")
      result should include("Exiting...")
    }

    "play a card and exit" in {
      val result = runMain("play\n0\nexit\n")

      result should include("CARD GAME: DURAK")
      result should include("Choose index of the card you want to play:")
      result should include("Attack:")
      result should include("Exiting...")
    }

    "handle an invalid card index and exit" in {
      val result = runMain("play\n99\nexit\n")

      result should include("Choose index of the card you want to play:")
      result should include("Exiting...")
    }
  }
}
