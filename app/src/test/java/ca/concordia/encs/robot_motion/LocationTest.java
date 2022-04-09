package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LocationTest {
    private final int x = 12;
    private final int y = 23;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;

    @Mock
    private Point point;

    @BeforeEach
    public final void beforeEach() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
    }

    @Test
    public void getX_LocationIsInstatiated_XIsStoredCorrectly() {
        assertNotNull(point);
        when(point.getX()).thenReturn(x);
        var location = new Location(point, direction);
        assertEquals(x, location.getX());
    }

    @Test
    public void getY_LocationIsInstantiated_YIsStoredCorrectly() {
        assertNotNull(point);
        when(point.getY()).thenReturn(y);
        var location = new Location(point, direction);
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

    @Test
    public void equals_SameObjects_ReturnsTrue() {
        assertTrue(location.equals(location));
    }

    @Test
    public void equals_DifferentObjectClass_ReturnsFalse() {
        assertFalse(location.equals("bla"));
    }

    @Test
    public void equals_DifferentObjectNull_ReturnsFalse() {
        assertFalse(location.equals(null));
    }

    @Test
    public void equals_DifferentX_ReturnsFalse() {
        Location locationCopy = new Location(new Point(999, location.getY()), location.getDirection());
        assertFalse(location.equals(locationCopy));
    }

    @Test
    public void equals_DifferentY_ReturnsFalse() {
        Location locationCopy = new Location(new Point(location.getX(), 999), location.getDirection());
        assertFalse(location.equals(locationCopy));
    }

    @Test
    public void equals_DifferentDirection_ReturnsFalse() {
        Location locationCopy = new Location(location.getPoint(), Direction.SOUTH);
        assertFalse(location.equals(locationCopy));
    }

    @Test
    public void equals_SameXYDirection_ReturnsTrue() {
        Location locationCopy = new Location(location.getPoint(), location.getDirection());
        assertTrue(location.equals(locationCopy));
    }
}
