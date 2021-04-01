package jc.ihoover

import Mover._

import org.scalatest.FunSuite

class ApplicationTest extends FunSuite {

  test("Test the application") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 5
    val yStartingPosition = 5
    val orientation = 'N'

    val commands = "DADADADAA"


    //Then
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val allPossiblePositions: List[Char] = List('N', 'E', 'S', 'W')
    println("Starting program")
    println("")

    if (maxX < 1 || maxY < 1) {
      println("Please enter a grid with maxX and maxY > 0 and a correct orientation")
    } else if (!allPossiblePositions.contains(orientation)) {
      println("Please enter a correct orientation")
    }
    else {
      moveProcessing(grid, position, commands)
    }
    println("Program end")
  }

}
