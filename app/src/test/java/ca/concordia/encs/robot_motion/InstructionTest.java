package ca.concordia.encs.robot_motion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class InstructionTest {
    @Test
    public void getFromShortName_ShortNameI_ReturnsInstructionInitialize() {
        Instruction instruction = Instruction.getFromShortName('I');
        assertEquals(Instruction.INITIALIZE, instruction);
    }

    @Test
    public void getFromShortName_ShortNameS_ThrowsIllegalArgumentException() {
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> Instruction.getFromShortName('S'));
        assertEquals("S is not a valid instruction", exception.getMessage());
    }

    @Test
    public void toString_InstructionConstant_ReturnsShortNameAsString() {
        assertEquals("R", Instruction.RIGHT.toString());
    }
}
