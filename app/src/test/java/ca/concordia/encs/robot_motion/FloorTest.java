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
    private Point point;
    private Location location;
    private Floor floor;

    @BeforeEach
    public final void beforeEach() {
        point = new Point(x, y);
        location = new Location(new Point(x, y), direction);
        floor = new Floor(max, point);
    }

    @Test
    @DisplayName("Horizontal axis position is initialized to zero")
    public void getHorizontalAxisPosition_CorrectStartingPosition_ReturnsZero() {
        assertEquals(0, floor.getHorizonatalAxisPosition());
    }

    @Test
    @DisplayName("New horizontal axis position is greater than maximum horizontal position")
    public void setHorizontalAxisPosition_GreaterThanMaxPosition_ThrowsIllegalArgumentException() {
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> floor.setHorizontalAxisPosition(11));
        assertEquals("New horizontal position is outside floor grid", exception.getMessage());
    }

    @Test
    @DisplayName("New horizontal axis position is less than minimum horizontal position")
    public void setHorizontalAxisPosition_LessThanMinPosition_ThrowsIllegalArgumentException() {
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> floor.setHorizontalAxisPosition(-1));
        assertEquals("New horizontal position is outside floor grid", exception.getMessage());
    }

    @Test
    @DisplayName("Set horizontal axis position to a valid position within floor")
    public void setHorizontalAxisPosition_ValidInput_PositionModifiedSuccessfully() {
        floor.setHorizontalAxisPosition(8);
        floor.setHorizontalAxisPosition(1);
        assertEquals(9, floor.getHorizonatalAxisPosition());
    }

    @Test
    @DisplayName("Vertical axis position is initialized to zero")
    public void getVerticalAxisPosition_CorrectStartingPosition_ReturnsZero() {
        assertEquals(0, floor.getVerticalAxisPosition());
    }

    @Test
    @DisplayName("New vertical axis position is greater than maximum vertical position")
    public void setVerticalAxisPosition_GreaterThanMaxPosition_ThrowsIllegalArgumentException() {
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> floor.setVerticalAxisPosition(11));
        assertEquals("New vertical position is outside floor grid", exception.getMessage());
    }

    @Test
    @DisplayName("New vertical axis position is less than maximum vertical position")
    public void setVerticalAxisPosition_LessThanMinPosition_ThrowsIllegalArgumentException() {
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> floor.setVerticalAxisPosition(-1));
        assertEquals("New vertical position is outside floor grid", exception.getMessage());
    }

    @Test
    @DisplayName("Set vertical axis position to a valid position within floor")
    public void setVerticalAxisPosition_ValidInput_PositionModifiedSuccessfully() {
        floor.setVerticalAxisPosition(5);
        floor.setVerticalAxisPosition(-2);
        assertEquals(3, floor.getVerticalAxisPosition());
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
    @DisplayName("Move position in the positive direction on horizontal axis")
    public void setFloorGrid_MovePositiveDirectionOnHorizontalAxis_CellsTraversedSetToTrue() {
        var floorGrid = new boolean[max.getX()][max.getY()];
        floorGrid[0][0] = true;
        floorGrid[1][0] = true;
        floorGrid[2][0] = true;
        floorGrid[3][0] = true;
        floorGrid[4][0] = true;
        floorGrid[5][0] = true;
        floorGrid[6][0] = true;
        floorGrid[7][0] = true;

        floor.setFloorGrid('X', 7);

        assertArrayEquals(floorGrid, floor.getFloorGrid());
    }

    @Test
    @DisplayName("Move position in the positive direction on the vertical axis")
    public void setFloorGrid_MovePositiveDirectionOnVerticalAxis_CellsTraversedSetToTrue() {
        var floorGrid = new boolean[max.getX()][max.getY()];
        floorGrid[0][0] = true;
        floorGrid[0][1] = true;
        floorGrid[0][2] = true;
        floorGrid[0][3] = true;
        floorGrid[0][4] = true;
        floorGrid[0][5] = true;

        floor.setFloorGrid('Y', 5);

        assertArrayEquals(floorGrid, floor.getFloorGrid());
    }

    @Test
    @DisplayName("Move position in the negative direction on horizontal axis")
    public void setFloorGrid_MoveNegativeDirectionOnHorizontalAxis_CellsTraversedSetToTrue() {
        var floorGrid = new boolean[max.getX()][max.getY()];
        floorGrid[0][0] = true;
        floorGrid[1][0] = true;
        floorGrid[2][0] = true;
        floorGrid[2][1] = true;
        floorGrid[2][2] = true;
        floorGrid[1][2] = true;
        floorGrid[0][2] = true;

        floor.setFloorGrid('X', 2);
        floor.setFloorGrid('Y', 2);
        floor.setFloorGrid('X', -2);

        assertArrayEquals(floorGrid, floor.getFloorGrid());
    }

    @Test
    @DisplayName("Move position in the negative direction on the vertical axis")
    public void setFloorGrid_MoveNegativeDirectionOnVerticalAxis_CellsTraversedSetToTrue() {
        var floorGrid = new boolean[max.getX()][max.getY()];
        floorGrid[0][0] = true;
        floorGrid[0][1] = true;
        floorGrid[0][2] = true;
        floorGrid[0][3] = true;
        floorGrid[1][3] = true;
        floorGrid[1][2] = true;
        floorGrid[1][1] = true;

        floor.setFloorGrid('Y', 3);
        floor.setFloorGrid('X', 1);
        floor.setFloorGrid('Y', -2);

        assertArrayEquals(floorGrid, floor.getFloorGrid());
    }

    @Test
    @DisplayName("Axis parameter contains an argument that is not the X or Y axis")
    public void setFloorGrid_InvalidAxisInput_ThrowsIllegalArgumentException() {
        var exception = assertThrows(IllegalArgumentException.class, () -> floor.setFloorGrid('Z', 2));
        assertEquals("Invalid axis value provided", exception.getMessage());
    }

    @Test
    public void testNewGridMethod() {
        var floorGrid = new boolean[max.getX()][max.getY()];
        floorGrid[0][0] = true;
        floorGrid[0][1] = true;
        floorGrid[0][2] = true;

        var previousLocation = location.copy();
        location.move(2, max);
        floor.setFloorGrid(previousLocation, 2);

        assertArrayEquals(floorGrid, floor.getFloorGrid());
    }
}
