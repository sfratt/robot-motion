package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobotMotionTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private RobotMotion robotMotion;

    @BeforeEach
    public void beforeEach() {
        robotMotion = new RobotMotion();
        robotMotion.setFloorSize(10);
        robotMotion.initialize();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void printCurrentPosition_InitialCurrentPosition_CaptureOutputSuccessfully() {
        robotMotion.printCurrentPosition();
        assertEquals("Position: 0, 0 - Pen: up - Facing: NORTH", outputStreamCaptor.toString().trim());
    }

    @Test
    public void printCurrentPosition_InvokePrintWithPenUp_CaptureOutputSuccessfully() {
        robotMotion.receiveInstruction('D');
        robotMotion.receiveInstruction('R');
        robotMotion.setMove(2);
        robotMotion.receiveInstruction('M');
        robotMotion.receiveInstruction('U');
        robotMotion.receiveInstruction('L');
        robotMotion.setMove(2);
        robotMotion.receiveInstruction('M');
        robotMotion.receiveInstruction('L');

        robotMotion.printCurrentPosition();

        assertEquals("Position: 2, 2 - Pen: up - Facing: WEST", outputStreamCaptor.toString().trim());
    }

    @Test
    public void printCurrentPosition_InvokePrintWithPenDown_CaptureOutputSuccessfully() {
        robotMotion.receiveInstruction('D');
        robotMotion.receiveInstruction('R');
        robotMotion.setMove(4);
        robotMotion.receiveInstruction('M');

        robotMotion.printCurrentPosition();

        assertEquals("Position: 4, 0 - Pen: down - Facing: EAST", outputStreamCaptor.toString().trim());
    }

    @Test
    public void isInt_ValidIntegerInput_ReturnsTrue() {
        assertTrue(RobotMotion.isInt("10"));
    }

    @Test
    public void isInt_InvalidIntegerInput_ReturnsTrue() {
        assertFalse(RobotMotion.isInt("bla"));
    }

    @AfterEach
    public void afterEach() {
        System.setOut(standardOut);
    }
}
