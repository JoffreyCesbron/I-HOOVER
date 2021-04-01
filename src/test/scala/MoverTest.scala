package jc.ihoover

import jc.ihoover.Mover._
import org.scalatest.FunSuite

class MoverTest extends FunSuite {

  test("Orientation W and move to the right") {
    //Given
    val actualOrientation = 'W'
    val position = new Position(0, 0, actualOrientation)

    //When
    val ouput = orientateToTheRight(position)

    //Then
    assert(ouput.orientation === 'N')
  }

  test("Orientation E and move to the right") {
    //Given
    val actualOrientation = 'E'
    val position = new Position(0, 0, actualOrientation)

    //When
    val ouput = orientateToTheRight(position)

    //Then
    assert(ouput.orientation === 'S')
  }

  test("Orientation E and move to the left") {
    //Given
    val actualOrientation = 'E'
    val position = new Position(0, 0, actualOrientation)

    //When
    val ouput = orientateToTheLeft(position)

    //Then
    assert(ouput.orientation === 'N')
  }

  test("Orientation N and move to the left") {
    //Given
    val actualOrientation = 'N'
    val position = new Position(0, 0, actualOrientation)

    //When
    val ouput = orientateToTheLeft(position)

    //Then
    assert(ouput.orientation === 'W')
  }


  test("Go to the South") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 5
    val yStartingPosition = 5
    val orientation = 'S'

    //When
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val output = tryMoveForward(position, grid)

    //Then
    assert(output.x == xStartingPosition)
    assert(output.y == 4)
    assert(output.orientation == orientation)
  }

  test("Blocked in the South") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 5
    val yStartingPosition = 0
    val orientation = 'S'

    //When
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val output = tryMoveForward(position, grid)

    //Then
    assert(output.x == xStartingPosition)
    assert(output.y == yStartingPosition)
    assert(output.orientation == orientation)
  }

  test("Go to the North") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 5
    val yStartingPosition = 5
    val orientation = 'N'

    //When
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val output = tryMoveForward(position, grid)

    //Then
    assert(output.x == xStartingPosition)
    assert(output.y == 6)
    assert(output.orientation == orientation)
  }

  test("Blocked in the North") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 5
    val yStartingPosition = 10
    val orientation = 'N'

    //When
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val output = tryMoveForward(position, grid)

    //Then
    assert(output.x == xStartingPosition)
    assert(output.y == yStartingPosition)
    assert(output.orientation == orientation)
  }

  test("Go to the East") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 5
    val yStartingPosition = 5
    val orientation = 'E'

    //When
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val output = tryMoveForward(position, grid)

    //Then
    assert(output.x == 6)
    assert(output.y == yStartingPosition)
    assert(output.orientation == orientation)
  }

  test("Blocked in the East") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 10
    val yStartingPosition = 5
    val orientation = 'E'

    //When
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val output = tryMoveForward(position, grid)

    //Then
    assert(output.x == xStartingPosition)
    assert(output.y == yStartingPosition)
    assert(output.orientation == orientation)
  }


  test("Go to the West") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 5
    val yStartingPosition = 5
    val orientation = 'W'

    //When
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val output = tryMoveForward(position, grid)

    //Then
    assert(output.x == 4)
    assert(output.y == yStartingPosition)
    assert(output.orientation == orientation)
  }

  test("Blocked in the West") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 0
    val yStartingPosition = 5
    val orientation = 'W'

    //When
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val output = tryMoveForward(position, grid)

    //Then
    assert(output.x == xStartingPosition)
    assert(output.y == yStartingPosition)
    assert(output.orientation == orientation)
  }

  test("Go to the East wall") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 5
    val yStartingPosition = 5
    val orientation = 'N'

    val commands = "DAAAAAA"

    //When
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val output = moveProcessing(grid, position, commands)

    //Then
    assert(output.x == 10)
    assert(output.y == 5)
    assert(output.orientation == 'E')
  }

  test("Don't move when commands empty") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 5
    val yStartingPosition = 5
    val orientation = 'N'

    val commands = ""

    //When
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val output = moveProcessing(grid, position, commands)

    //Then
    assert(output.x == 5)
    assert(output.y == 5)
    assert(output.orientation == 'N')
  }

  test("Stop moving when command is unrecognized") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 5
    val yStartingPosition = 5
    val orientation = 'N'

    val commands = "AZAAAA"

    //When
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val output = moveProcessing(grid, position, commands)

    //Then
    assert(output.x == 5)
    assert(output.y == 6)
    assert(output.orientation == 'N')
  }

  test("Test the application with a basic case") {
    //Given
    val maxX = 10
    val maxY = 10

    val xStartingPosition = 5
    val yStartingPosition = 5
    val orientation = 'N'

    val commands = "DADADADAA"

    //When
    val grid = new Grid(maxX, maxY)
    val position = new Position(xStartingPosition, yStartingPosition, orientation)
    val output = moveProcessing(grid, position, commands)

    //Then
    assert(output.x == 5)
    assert(output.y == 6)
    assert(output.orientation == 'N')
  }
}
