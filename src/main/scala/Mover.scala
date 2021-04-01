package jc.ihoover

import scala.math.{max, min}

object Mover {

  val allPossiblePositions: List[Char] = List('N', 'E', 'S', 'W')

  //Move to the next element of the positions list. If it's the last, go back to the begining
  def orientateToTheRight(position: Position): Position = {
    val actualOrientation = position.orientation
    val currentIndex = allPossiblePositions.indexOf(actualOrientation)
    val nextOrientation = if (currentIndex != allPossiblePositions.length - 1) {
      allPossiblePositions(currentIndex + 1)
    } else {
      allPossiblePositions(0)
    }
    val output = new Position(position.x, position.y, nextOrientation)
    printPositionAfter(output)
    output
  }

  //Move to the previous element of the positions list. If it's the first, go back to the last
  def orientateToTheLeft(position: Position): Position = {
    val actualOrientation = position.orientation
    val currentIndex = allPossiblePositions.indexOf(actualOrientation)
    val nextOrientation = if (currentIndex != 0) {
      allPossiblePositions(currentIndex - 1)
    } else {
      allPossiblePositions.last
    }
    val output = new Position(position.x, position.y, nextOrientation)
    printPositionAfter(output)
    output
  }

  //When the command is A you add 1 to the correct coordonnate if there is no wall
  def tryMoveForward(position: Position, grid: Grid): Position = {
    val outputPosition = position.orientation match {
      case 'N' => new Position(position.x, min(position.y + 1, grid.maxY), position.orientation)
      case 'E' => new Position(min(position.x + 1, grid.maxX), position.y, position.orientation)
      case 'S' => new Position(position.x, max(position.y - 1, 0), position.orientation)
      case 'W' => new Position(max(position.x - 1, 0), position.y, position.orientation)
    }
    printPositionAfter(outputPosition)
    outputPosition
  }

  //As long as there is a command to do you update the position recursively
  def moveProcessing(grid: Grid, position: Position, commands: String): Position = {

    val commandsLength = commands.length
    val output =
      if (commandsLength > 0) {
      val currentCommand = commands.head
      printPositionBefore(position, commands)
      currentCommand match {
        case 'D' => moveProcessing(grid, orientateToTheRight(position), commands.substring(1))
        case 'G' => moveProcessing(grid, orientateToTheLeft(position), commands.substring(1))
        case 'A' => moveProcessing(grid, tryMoveForward(position, grid), commands.substring(1))
        case _ => new Position(position.x, position.y, position.orientation)
      }
    } else {
      position
    }
    output
  }

  def printPositionBefore(position: Position, commands: String): Unit = {
    println(s"before doing [${commands.head}] and remaining [${commands.substring(1)}]:")
    println(s"x=${position.x}, y=${position.y}, orientation=${position.orientation} ")
  }

  def printPositionAfter(position: Position): Unit = {
    println(s"=> result x=${position.x}, y=${position.y}, orientation=${position.orientation} ")
    println("")
  }
}
