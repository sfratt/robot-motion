package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobotTest {
    private Robot robot;

    @BeforeEach
    public final void before() {
        robot = new Robot();
    }

    @Test
    public void getPenPosition_CorrectStartingPosition_ReturnsFalse() {
        assertFalse(robot.getPenPosition());
    }

    @Test
    public void setPenPosition_SetPenDownPosition_ReturnsTrue() {
        robot.setPenPosition(true);
        assertTrue(robot.getPenPosition());
    }
}
