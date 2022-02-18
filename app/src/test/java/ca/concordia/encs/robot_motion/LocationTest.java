package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LocationTest {
    private final int x = 12;
    private final int y = 23;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;

    @BeforeEach
    public final void beforeEach() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
    }

    @Test
    public void getX_LocationIsInstatiated_XIsStoredCorrectly() {
        assertEquals(x, location.getX());
    }

    @Test
    public void getY_LocationIsInstantiated_YIsStoredCorrectly() {
        assertEquals(y, location.getY());
    }

    @Test
    public void getDirection_LocationIsInstantiated_DirectionIsStoredCorrectly() {
        assertEquals(direction, location.getDirection());
    }

    @Test
    public void turnLeft_DirectionIsSet_ReturnsDirectionWest() {
        location.turnLeft();
        assertEquals(Direction.WEST, location.getDirection());
    }

    @Test
    public void turnRight_DirectionIsSet_ReturnsDirectionEast() {
        location.turnRight();
        assertEquals(Direction.EAST, location.getDirection());
    }

    @Test
    public void move_DirectionNorth_YIncreasesByMoveSize() {
        location.setDirection(Direction.NORTH);
        location.move(4, max);
        assertEquals(y + 4, location.getY());
    }

    @Test
    public void move_DirectionEast_XIncreasesByMoveSize() {
        location.setDirection(Direction.EAST);
        location.move(12, max);
        assertEquals(x + 12, location.getX());
    }

    @Test
    public void move_DirectionSouth_YDecreasesByMoveSize() {
        location.setDirection(Direction.SOUTH);
        location.move(8, max);
        assertEquals(y - 8, location.getY());
    }

    @Test
    public void move_DirectionWest_XDecreasesByMoveSize() {
        location.setDirection(Direction.WEST);
        location.move(3, max);
        assertEquals(x - 3, location.getX());
    }

    @Test
    public void move_DirectionNone_ReturnsFalse() {
        location.setDirection(Direction.NONE);
        var isPointChanged = location.move(17, max);
        assertFalse(isPointChanged);
    }

    @Test
    public void move_YGreaterThanMaxPoint_ReturnsFalseAndMoveNotChanged() {
        location.setDirection(Direction.NORTH);
        var isPointChanged = location.move(50, max);
        assertFalse(isPointChanged);
        assertEquals(y, location.getY());
    }

    @Test
    public void move_XGreaterThanMaxPoint_ReturnsFalseAndMoveNotChanged() {
        location.setDirection(Direction.EAST);
        var isPointChanged = location.move(50, max);
        assertFalse(isPointChanged);
        assertEquals(x, location.getX());
    }

    @Test
    public void move_YLessThanMinPoint_ReturnsFalseAndMoveNotChanged() {
        location.setDirection(Direction.SOUTH);
        var isPointChanged = location.move(50, max);
        assertFalse(isPointChanged);
        assertEquals(y, location.getY());
    }

    @Test
    public void move_XLessThanMaxPoint_ReturnsFalseAndMoveNotChanged() {
        location.setDirection(Direction.WEST);
        var isPointChanged = location.move(50, max);
        assertFalse(isPointChanged);
        assertEquals(x, location.getX());
    }

    @Test
    public void copy_CopyObject_ObjectsAreDifferent() {
        var copy = location.copy();
        assertNotSame(copy, location);
    }

    @Test
    public void copy_CopyObject_ObjectsAreEquivalent() {
        var copy = location.copy();
        assertEquals(location, copy);
    }
}
