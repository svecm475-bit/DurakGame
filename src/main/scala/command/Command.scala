package command

trait Command {
  def execute(): Unit
  def undo(): Unit
}
