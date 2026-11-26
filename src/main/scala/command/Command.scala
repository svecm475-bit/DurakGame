package command
import scala.util.Try

trait Command {
  def execute(): Try[Unit]
  def undo(): Unit
}
