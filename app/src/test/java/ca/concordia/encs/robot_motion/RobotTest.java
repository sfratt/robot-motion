package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobotTest {
    private Location location;
    private Pen pen;
    private Floor floor;
    private Robot robot;

    @BeforeEach
    public final void beforeEach() {
        location = new Location(new Point(0, 0), Direction.NORTH);
        floor = new Floor(new Point(10, 10));
        pen = new Pen(false);
        robot = new Robot(location, pen, floor);
    }

    @Test
    public void robot_InstantiatedUsingConstructor_LocationIsSet() {
        assertEquals(location, robot.getLocation());
    }

    @Test
    public void turnLeft_RobotTurnsLeft_DirectionChangedToLeft() {
        var expected = location.copy();
        expected.turnLeft();
        robot.turnLeft();
        assertEquals(expected, robot.getLocation());
    }

    @Test
    public void turnRight_RobotTurnsRight_DirectionChangedToRight() {
        var expected = location.copy();
        expected.turnRight();
        robot.turnRight();
        assertEquals(expected, robot.getLocation());
    }

    @Test
    public void getPenPosition_InitialPenPosition_ReturnsFalse() {
        assertFalse(pen.getPenPosition());
    }

    @Test
    public void setPenPosition_SetPenPositionDown_ReturnsTrue() {
        robot.setPenPosition(true);
        assertTrue(robot.getPenPosition());
    }

    @Test
    public void getFloor_InstantiatedUsingConstructor_FloorGridIsInitialized() {
        assertEquals(floor, robot.getFloor());
    }

    @Test
    public void move_MoveWithPenPositionDown_MoveAppliedToFloorGrid() {
        var floorGrid = new boolean[floor.getMax().getX()][floor.getMax().getY()];
        floorGrid[0][0] = true;
        floorGrid[0][1] = true;
        floorGrid[0][2] = true;
        floorGrid[0][3] = true;

        robot.setPenPosition(true);
        robot.move(3);

        assertArrayEquals(floorGrid, robot.getFloor().getFloorGrid());
    }

    @Test
    public void move_MoveOutsideFloorAndPenPositionDown_ThrowsIllegalArgumentException() {
        var move = 11;
        robot.setPenPosition(true);
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> robot.move(move));
        assertEquals("M " + move + " is an invalid move", exception.getMessage());
    }

    @Test
    public void move_MoveWithPenPositionUp_NewPointSavedCorrectly() {
        var expected = new Location(new Point(4, 0), Direction.EAST);

        robot.setPenPosition(false);
        robot.turnRight();
        robot.move(4);

        assertEquals(expected, robot.getLocation());
    }

    @Test
    public void move_MoveOutsideFloorAndPenPositionUp_ThrowsIllegalArgumentException() {
        var move = 11;
        robot.setPenPosition(false);
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> robot.move(move));
        assertEquals("M " + move + " is an invalid move", exception.getMessage());
    }
}
