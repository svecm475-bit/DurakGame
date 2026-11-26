package command

import scala.util.{Try, Success, Failure}

class Invoker {
  // Stores commands that can be undone
  private var undoStack:List[Command] = List()
  // Executes a command and saves it in the undo history
  def doStep(command: Command): Try[Unit] = {
    val result = command.execute()

    result match {
      case Success(_) =>
        undoStack = command :: undoStack

      case Failure(_) =>
        ()
    }

    result
  }
  // Undoes the last executed command
  def undoStep() : Unit = {
    undoStack match {
      case head :: tail =>
        head.undo()
        undoStack = tail

      case Nil =>
        println("Nothing to undo")
    }
  }

}
