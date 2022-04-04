package ca.concordia.encs.robot_motion;

public enum Instruction {
    UP('U'),
    DOWN('D'),
    RIGHT('R'),
    LEFT('L'),
    MOVE('M'),
    PRINT('P'),
    CURRENT('C'),
    QUIT('Q'),
    INITIALIZE('I'),
    HISTORY('H');

    private final char value;

    private Instruction(final char value) {
        this.value = value;
    }

    public static Instruction getFromShortName(char value) {
        for (Instruction instruction : Instruction.values()) {
            if (instruction.value == value) {
                return instruction;
            }
        }
        throw new IllegalArgumentException(value + " is not a valid instruction");
    }

    // public static Instruction getFromShortName(String value) {
    // for (Instruction instruction : Instruction.values()) {
    // if (String.valueOf(instruction) == value) {
    // return instruction;
    // }
    // }
    // throw new IllegalArgumentException(value + " is not a valid instruction");
    // }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
