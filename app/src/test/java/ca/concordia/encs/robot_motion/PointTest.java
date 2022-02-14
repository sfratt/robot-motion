package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PointTest {
    private Point point;
    private final int x = 12;
    private final int y = 21;

    @BeforeEach
    public void beforeEach() {
        point = new Point(x, y);
    }

    @Test
    public void getX_ConstructorInstantiatedX_ReturnsX() {
        point.getX();
    }

    @Test
    public void setX_UpdateXUsingSetter_XIsSetSuccessfully() {
        int newX = 10;
        point.setX(newX);
        assertEquals(newX, point.getX());
    }

    @Test
    public void getY_ConstructorInstantiatedY_ReturnsY() {
        point.getY();
    }

    @Test
    public void setX_UpdateYUsingSetter_YIsSetSuccessfully() {
        int newY = 10;
        point.setY(newY);
        assertEquals(newY, point.getY());
    }
}
