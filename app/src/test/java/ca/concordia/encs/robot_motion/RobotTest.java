package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobotTest {
    private Robot robot;
    private Pen pen;

    @BeforeEach
    public final void beforeEach() {
        pen = new Pen();
        robot = new Robot(pen);
    }
}
