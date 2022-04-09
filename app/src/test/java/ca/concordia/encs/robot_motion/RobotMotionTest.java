package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void getCurrentPosition_InvokeOnInitializedFloor_ReturnsCurrentPositionString() {
        robotMotion.receiveInstruction('D');
        robotMotion.receiveInstruction('R');
        robotMotion.setMove(4);
        robotMotion.receiveInstruction('M');

        assertEquals("Position: 4, 0 - Pen: down - Facing: EAST\n", robotMotion.getCurrentPosition());
    }

    @Test
    public void printCurrentPosition_InvokePrintWithPenDown_CaptureOutputSuccessfully() {
        robotMotion.receiveInstruction('D');
        robotMotion.receiveInstruction('R');
        robotMotion.setMove(4);
        robotMotion.receiveInstruction('M');

        robotMotion.receiveInstruction('C');

        assertEquals("Position: 4, 0 - Pen: down - Facing: EAST", outputStreamCaptor.toString().trim());
    }

    @Test
    public void addHistory_EnterValidInstruction_VerifyHistoryCount() {
        robotMotion.receiveInstruction('D');

        robotMotion.addHistory("D");

        assertEquals(1, robotMotion.getHistory().size());
    }

    @Test
    public void addHistory_EnterInvalidInstruction_ThrowsIllegalArgumentException() {
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> robotMotion.addHistory("S"));
        assertEquals("S is not a valid instruction.", exception.getMessage());
    }

    @Test
    public void printHistory_InvokeOnInitialize_PrintHistoryContents() {
        robotMotion.addHistory("I");

        robotMotion.receiveInstruction('H');

        assertEquals(
                "Instruction: I - Position: 0, 0 - Pen: up - Facing: NORTH",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void printFloor_InitializedFloorWithoutMoves_PrintsFloorGrid() {
        robotMotion.receiveInstruction('P');

        assertFalse(outputStreamCaptor.toString().contains("*"));
    }

    @Test
    public void printFloor_InitializedFloorWithMoves_PrintsFloorGrid() {
        robotMotion.receiveInstruction('D');
        robotMotion.receiveInstruction('R');
        robotMotion.setMove(4);
        robotMotion.receiveInstruction('M');

        robotMotion.receiveInstruction('P');

        assertTrue(outputStreamCaptor.toString().contains("*"));
    }

    @Test
    public void quit_CloseScanner_ScannerClosedButNotNull() {
        robotMotion.receiveInstruction('Q');

        assertNotNull(robotMotion.getScanner());
    }

    @Test
    public void initialize_StartOrRestart_InstantiatesNewObjects() {
        robotMotion.receiveInstruction('D');
        robotMotion.receiveInstruction('R');
        robotMotion.setMove(4);
        robotMotion.receiveInstruction('M');

        robotMotion.receiveInstruction('I');

        assertEquals("Position: 0, 0 - Pen: up - Facing: NORTH\n", robotMotion.getCurrentPosition());
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
