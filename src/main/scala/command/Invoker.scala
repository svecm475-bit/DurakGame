package command

import scala.util.Try

class Invoker {
  // Stores commands that can be undone
  private var undoStack:List[Command] = List()
  // Stores commands that can be redone
  private var redoStack:List[Command] = List()

  // Executes a command and saves it in the undo history
  def doStep(command: Command): Unit = {
    command.execute()
    // Add the command to the beginning of the undo stack
    undoStack = command :: undoStack
    // Clear redo history because a new action was performed
    redoStack = List()
  }
  // Undoes the last executed command
  def undoStep() : Unit = {
    undoStack match {
      // There are no commands to undo
      case List() => println("Nothing to do")
      // Get the last executed command
      case head :: stack => head.undo()
        // Remove the command from the undo stack
        undoStack = stack
        // Move the command to the redo stack
        redoStack = head :: redoStack
    }
  }

}
