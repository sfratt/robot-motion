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
    INITIALIZE('I');

    private final char shortName;

    private Instruction(final char shortName) {
        this.shortName = shortName;
    }

    public static Instruction getFromShortName(char shortName) {
        for (Instruction instruction : Instruction.values()) {
            if (instruction.shortName == shortName) {
                return instruction;
            }
        }
        throw new IllegalArgumentException(shortName + " is not a valid instruction short name");
    }
}
