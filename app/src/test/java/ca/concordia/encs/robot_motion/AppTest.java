package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.*;

class AppTest {
    private final int floorLength = 10;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public final void before() {
        App.initGame(floorLength);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public final void after() {
        System.setOut(originalOut);
    }

    @Test
    public void initGame_InitializedGameBoard_AllParametersInitializedCorrectly() {
        var floorGrid = new Boolean[floorLength][floorLength];
        for (Boolean[] rows : floorGrid) {
            Arrays.fill(rows, Boolean.FALSE);
        }
        assertArrayEquals(floorGrid, App.gameBoard);
        assertEquals(10, App.boardSize);
        assertEquals(0, App.robotPositionX);
        assertEquals(0, App.robotPositionY);
        assertEquals("North", App.robotDirection);
    }

    @Test
    public void setPenPosition_PenPositionDown_PenPositionSetToTrue() {
        App.setPenPosition(true);
        assertTrue(App.penPosition);
    }

    @Test
    public void isInt_ValidStringToIntConversion_ReturnsTrue() {
        assertTrue(App.isInt("10"));
    }

    @Test
    public void isInt_InvalidStringToIntConversion_ReturnsFalse() {
        assertFalse(App.isInt("10x"));
    }

    @Test
    public void setPositionX_ValueInPositiveDirection_BoardUpdatedAndPositionIncreased() {
        var floorGrid = new Boolean[floorLength][floorLength];
        for (Boolean[] rows : floorGrid) {
            Arrays.fill(rows, Boolean.FALSE);
        }
        floorGrid[0][0] = true;
        floorGrid[0][1] = true;
        floorGrid[0][2] = true;
        floorGrid[0][3] = true;

        App.setPenPosition(true);
        App.setPositionX(3);

        assertArrayEquals(floorGrid, App.gameBoard);
        assertEquals(3, App.robotPositionX);
    }

    @Test
    public void setPositionY_ValueInPositiveDirection_BoardUpdatedAndPositionIncreased() {
        var floorGrid = new Boolean[floorLength][floorLength];
        for (Boolean[] rows : floorGrid) {
            Arrays.fill(rows, Boolean.FALSE);
        }
        floorGrid[0][0] = true;
        floorGrid[1][0] = true;
        floorGrid[2][0] = true;
        floorGrid[3][0] = true;
        floorGrid[4][0] = true;
        floorGrid[5][0] = true;

        App.setPenPosition(true);
        App.setPositionY(5);

        assertArrayEquals(floorGrid, App.gameBoard);
        assertEquals(5, App.robotPositionY);
    }

    @Test
    public void setPositionX_ValueInNegativeDirection_BoardUpdatedAndPositionDecreased() {
        var floorGrid = new Boolean[floorLength][floorLength];
        for (Boolean[] rows : floorGrid) {
            Arrays.fill(rows, Boolean.FALSE);
        }
        floorGrid[0][0] = true;
        floorGrid[0][1] = true;
        floorGrid[0][2] = true;
        floorGrid[0][3] = true;
        floorGrid[1][3] = true;
        floorGrid[2][3] = true;
        floorGrid[2][2] = true;
        floorGrid[2][1] = true;

        App.setPenPosition(true);
        App.setPositionX(3);
        App.setPositionY(2);
        App.setPositionX(-2);

        assertArrayEquals(floorGrid, App.gameBoard);
        assertEquals(1, App.robotPositionX);
    }

    @Test
    public void setPositionY_ValueInNegativeDirection_BoardUpdatedAndPositionDecreased() {
        var floorGrid = new Boolean[floorLength][floorLength];
        for (Boolean[] rows : floorGrid) {
            Arrays.fill(rows, Boolean.FALSE);
        }
        floorGrid[0][0] = true;
        floorGrid[1][0] = true;
        floorGrid[2][0] = true;
        floorGrid[3][0] = true;
        floorGrid[4][0] = true;
        floorGrid[5][0] = true;
        floorGrid[5][1] = true;
        floorGrid[5][2] = true;
        floorGrid[4][2] = true;
        floorGrid[3][2] = true;

        App.setPenPosition(true);
        App.setPositionY(5);
        App.setPositionX(2);
        App.setPositionY(-2);

        assertArrayEquals(floorGrid, App.gameBoard);
        assertEquals(3, App.robotPositionY);
    }

    @Test
    public void validInstruction_ConfirmUIsValidInput_ReturnsTrue() {
        assertTrue(App.validInstruction("U"));
    }

    @Test
    public void validInstruction_ConfirmDIsValidInput_ReturnsTrue() {
        assertTrue(App.validInstruction("D"));
    }

    @Test
    public void validInstruction_ConfirmRIsValidInput_ReturnsTrue() {
        assertTrue(App.validInstruction("R"));
    }

    @Test
    public void validInstruction_ConfirmLIsValidInput_ReturnsTrue() {
        assertTrue(App.validInstruction("L"));
    }

    @Test
    public void validInstruction_ConfirmMIsValidInput_ReturnsTrue() {
        assertTrue(App.validInstruction("M"));
    }

    @Test
    public void validInstruction_ConfirmPIsValidInput_ReturnsTrue() {
        assertTrue(App.validInstruction("P"));
    }

    @Test
    public void validInstruction_ConfirmCIsValidInput_ReturnsTrue() {
        assertTrue(App.validInstruction("C"));
    }

    @Test
    public void validInstruction_ConfirmQIsValidInput_ReturnsTrue() {
        assertTrue(App.validInstruction("Q"));
    }

    @Test
    public void validInstruction_ConfirmIIsValidInput_ReturnsTrue() {
        assertTrue(App.validInstruction("I"));
    }

    @Test
    public void validInstruction_ConfirmSIsValidInput_ReturnsTrue() {
        assertFalse(App.validInstruction("S"));
    }

    @Test
    public void setRobotTurn_TurnRightFromPointingNorth_SetsDirectionToEast() {
        App.robotDirection = "North";
        App.setRobotTurn("Right");
        assertEquals("East", App.robotDirection);
    }

    @Test
    public void setRobotTurn_TurnRightFromPointingEast_SetsDirectionToSouth() {
        App.robotDirection = "East";
        App.setRobotTurn("Right");
        assertEquals("South", App.robotDirection);
    }

    @Test
    public void setRobotTurn_TurnRightFromPointingSouth_SetsDirectionToWest() {
        App.robotDirection = "South";
        App.setRobotTurn("Right");
        assertEquals("West", App.robotDirection);
    }

    @Test
    public void setRobotTurn_TurnRightFromPointingWest_SetsDirectionToNorth() {
        App.robotDirection = "West";
        App.setRobotTurn("Right");
        assertEquals("North", App.robotDirection);
    }

    @Test
    public void setRobotTurn_TurnLeftFromPointingNorth_SetsDirectionToWest() {
        App.robotDirection = "North";
        App.setRobotTurn("Left");
        assertEquals("West", App.robotDirection);
    }

    @Test
    public void setRobotTurn_TurnLeftFromPointingWest_SetsDirectionToSouth() {
        App.robotDirection = "West";
        App.setRobotTurn("Left");
        assertEquals("South", App.robotDirection);
    }

    @Test
    public void setRobotTurn_TurnLeftFromPointingSouth_SetsDirectionToEast() {
        App.robotDirection = "South";
        App.setRobotTurn("Left");
        assertEquals("East", App.robotDirection);
    }

    @Test
    public void setRobotTurn_TurnLeftFromPointingEast_SetsDirectionToNorth() {
        App.robotDirection = "East";
        App.setRobotTurn("Left");
        assertEquals("North", App.robotDirection);
    }
}
