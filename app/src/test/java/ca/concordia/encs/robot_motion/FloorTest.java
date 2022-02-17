package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FloorTest {
    private final Point max = new Point(10, 10);
    private final int x = 0;
    private final int y = 0;
    private Direction direction = Direction.NORTH;
    private Location location;
    private Floor floor;

    @BeforeEach
    public final void beforeEach() {
        location = new Location(new Point(x, y), direction);
        floor = new Floor(max);
    }

    @Test
    @DisplayName("Floor grid initialized with correct dimensions and values")
    public void getFloorGrid_InitializeTwoDimensionalArray_GridInitializedCorrectly() {
        var floorGrid = new boolean[max.getX()][max.getY()];
        for (boolean[] rows : floorGrid) {
            Arrays.fill(rows, Boolean.FALSE);
        }
        assertEquals(max.getX(), floor.getFloorGrid().length);
        assertEquals(max.getY(), floor.getFloorGrid()[0].length);
        assertArrayEquals(floorGrid, floor.getFloorGrid());
    }

    @Test
    @DisplayName("Floor grid cells changed to true when moving in the NORTH direction")
    public void setFloorGrid_MoveDirectionNorth_CellsTraversedSetToTrue() {
        var floorGrid = new boolean[max.getX()][max.getY()];
        floorGrid[0][0] = true;
        floorGrid[0][1] = true;
        floorGrid[0][2] = true;
        floorGrid[0][3] = true;
        floorGrid[0][4] = true;
        floorGrid[0][5] = true;
        floorGrid[0][6] = true;

        var previousLocation = location.copy();
        location.move(6, max);
        floor.setFloorGrid(previousLocation, 6);

        assertArrayEquals(floorGrid, floor.getFloorGrid());
    }

    @Test
    @DisplayName("Floor grid cells changed to true when moving in the EAST direction")
    public void setFloorGrid_MoveDirectionEast_CellsTraversedSetToTrue() {
        var floorGrid = new boolean[max.getX()][max.getY()];
        floorGrid[0][0] = true;
        floorGrid[1][0] = true;
        floorGrid[2][0] = true;
        floorGrid[3][0] = true;
        floorGrid[4][0] = true;
        floorGrid[5][0] = true;

        location.turnRight();
        var previousLocation = location.copy();
        location.move(5, max);
        floor.setFloorGrid(previousLocation, 5);

        assertArrayEquals(floorGrid, floor.getFloorGrid());
    }

    @Test
    @DisplayName("Floor grid cells changed to true when moving in the SOUTH direction")
    public void setFloorGrid_MoveDirectionSouth_CellsTraversedSetToTrue() {
        var floorGrid = new boolean[max.getX()][max.getY()];
        floorGrid[0][0] = true;
        floorGrid[0][1] = true;
        floorGrid[0][2] = true;
        floorGrid[0][3] = true;
        floorGrid[1][3] = true;
        floorGrid[1][2] = true;
        floorGrid[1][1] = true;

        var previousLocation = location.copy();
        location.move(3, max);
        floor.setFloorGrid(previousLocation, 3);

        location.turnRight();
        previousLocation = location.copy();
        location.move(1, max);
        floor.setFloorGrid(previousLocation, 1);

        location.turnRight();
        previousLocation = location.copy();
        location.move(2, max);
        floor.setFloorGrid(previousLocation, 2);

        assertArrayEquals(floorGrid, floor.getFloorGrid());
    }

    @Test
    @DisplayName("Floor grid cells changed to true when moving in the WEST direction")
    public void setFloorGrid_MoveDirectionWest_CellsTraversedSetToTrue() {
        var floorGrid = new boolean[max.getX()][max.getY()];
        floorGrid[0][0] = true;
        floorGrid[1][0] = true;
        floorGrid[2][0] = true;
        floorGrid[2][1] = true;
        floorGrid[2][2] = true;
        floorGrid[1][2] = true;
        floorGrid[0][2] = true;

        location.turnRight();
        var previousLocation = location.copy();
        location.move(2, max);
        floor.setFloorGrid(previousLocation, 2);

        location.turnLeft();
        previousLocation = location.copy();
        location.move(2, max);
        floor.setFloorGrid(previousLocation, 1);

        location.turnLeft();
        previousLocation = location.copy();
        location.move(2, max);
        floor.setFloorGrid(previousLocation, 2);

        assertArrayEquals(floorGrid, floor.getFloorGrid());
    }

    @Test
    public void setFloorGrid_MoveDirectionNone_DoesNothing() {
        var invalidLocation = new Location(new Point(x, y), Direction.NONE);
        floor.setFloorGrid(invalidLocation, 2);
        // assert
    }

    @Test
    @DisplayName("Move value is less than the floor grid minimum allowable cell index")
    public void setFloorGrid_LessThanMinimumValidMove_ThrowsIllegalArgumentException() {
        var invalidMove = -2;
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> floor.setFloorGrid(location, invalidMove));
        assertEquals(invalidMove + " is not a valid move", exception.getMessage());
    }

    @Test
    @DisplayName("Move value is greater than the floor grid maximum allowable cell index")
    public void setFloorGrid_GreaterThanMaximumValidMove_ThrowsIllegalArgumentException() {
        var invalidMove = 11;
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> floor.setFloorGrid(location, invalidMove));
        assertEquals(invalidMove + " is not a valid move", exception.getMessage());
    }

    @Test
    public void setFloorGrid_InvalidMoveWestFromStartingPosition_ThrowsArrayIndexOutOfBoundsException() {
        var invalidMove = 1;
        location.turnLeft();
        var exception = assertThrows(
                ArrayIndexOutOfBoundsException.class,
                () -> floor.setFloorGrid(location, invalidMove));
        assertEquals("Index -1 out of bounds for length 10", exception.getMessage());
    }

    @Test
    public void setFloorGrid_InvalidMoveNorthFromStartingPosition_ThrowsArrayIndexOutOfBoundsException() {
        var invalidMove = 5;

        var previousLocation = location.copy();
        location.move(7, max);
        floor.setFloorGrid(previousLocation, 7);
        var exception = assertThrows(
                ArrayIndexOutOfBoundsException.class,
                () -> floor.setFloorGrid(location, invalidMove));
        assertEquals("Index 10 out of bounds for length 10", exception.getMessage());
    }
}
