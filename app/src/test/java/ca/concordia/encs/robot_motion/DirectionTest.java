package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DirectionTest {
    @Test
    public void getFromShortName_ShortNameN_ReturnsDirectionNorth() {
        Direction direction = Direction.getFromShortName('N');
        assertEquals(Direction.NORTH, direction);
    }

    @Test
    public void getFromShortName_ShortNameB_ReturnsNone() {
        Direction direction = Direction.getFromShortName('D');
        assertEquals(Direction.NONE, direction);
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
