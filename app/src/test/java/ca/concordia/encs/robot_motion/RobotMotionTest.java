package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void print_InvokePrintWithOutput_OutputCaptorSuccess() {
        robotMotion.print("Hello Baeldung Readers!!");

        assertEquals("Hello Baeldung Readers!!", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void afterEach() {
        System.setOut(standardOut);
    }
}
