package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DirectionTest {
    @Test
    public void getFromShortName_ShortNameN_ReturnsDirectionNorth() {
        Direction direction = Direction.getFromShortName('N');
        assertEquals(Direction.NORTH, direction);
    }

    @Test
    public void getFromShortName_ShortNameW_ReturnsDirectionWest() {
        Direction direction = Direction.getFromShortName('W');
        assertEquals(Direction.WEST, direction);
    }

    @Test
    public void getFromShortName_ShortNameD_ThrowsIllegalArgumentException() {
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> Direction.getFromShortName('D'));
        assertEquals("D is not a valid direction short name", exception.getMessage());
    }

    @Test
    public void turnLeft_DirectionNorth_ReturnsDirectionWest() {
        assertEquals(Direction.WEST, Direction.NORTH.turnLeft());
    }

    @Test
    public void turnRight_DirectionEast_ReturnsDirectionSouth() {
        assertEquals(Direction.SOUTH, Direction.EAST.turnRight());
    }
}
