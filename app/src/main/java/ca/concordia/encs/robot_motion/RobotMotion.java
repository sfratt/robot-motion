package ca.concordia.encs.robot_motion;

// import java.io.IOException;
import java.util.Scanner;

public class RobotMotion {
    private Robot robot;
    // private Instruction instruction;
    private Location location;
    private Pen pen;
    private Floor floor;
    private int moveSize;
    private int floorSize;
    private Scanner scanner;
    private boolean gameActive;

    public RobotMotion() {
        location = new Location(new Point(0, 0), Direction.NORTH);
        pen = new Pen(false);
        floor = new Floor(new Point(0, 0));
        robot = new Robot(location, pen, floor);
    }

    public void run() {
        gameActive = true;
        while (gameActive) {
            String firstInput = "";
            String secondInput = "";
            String instruction = "";
            int instructionNumber = 0;

            // clearConsole();
            try {
                System.out.print("Enter command: ");
                scanner = new Scanner(System.in);
                firstInput = scanner.next(); // reads input

                if (firstInput.trim().toUpperCase() != "") { // validates input
                    instruction = firstInput.toUpperCase(); // input to uppercase and save
                    if (robot.getFloor().getFloorGrid().length == 0 && !instruction.equals("I")) {
                        print("Please start by initializing the floor\n");
                        continue;
                    }
                    if (instruction.equals("I") || instruction.equals("M")) {
                        secondInput = scanner.next(); // read number
                        if (isInt(secondInput)) { // validates number
                            if (instruction.equals("I")) {
                                if (Integer.parseInt(secondInput) > 0) {
                                    instructionNumber = Integer.parseInt(secondInput);
                                } else {
                                    System.out.println("Please enter positive integer.");
                                }
                                setFloorSize(instructionNumber);
                            } else {
                                instructionNumber = Integer.parseInt(secondInput);
                                setMove(instructionNumber);
                            }
                        } else {
                            System.out.println("Please enter valid integer.");
                        }
                    }
                } else {
                    System.out.println("Please enter valid instruction.");
                }

                receiveInstruction(instruction.charAt(0));
            } catch (Exception e) {
                print(e.getMessage() + "\n");
            }
        }
    }

    public void print(String output) {
        System.out.print(output);
    }

    // public void clearConsole() throws IOException {
    // final String os = System.getProperty("os.name");
    // if (os.contains("Windows")) {
    // Runtime.getRuntime().exec("cls");
    // } else {
    // Runtime.getRuntime().exec("clear");
    // }
    // }

    public void printFloor() {
        var floorGrid = robot.getFloor().getFloorGrid();

        for (int y = floorGrid.length - 1; y >= 0; y--) {
            System.out.format("%2d", y);
            // print(String.valueOf(y));
            for (int x = 0; x < floorGrid.length; x++) {
                if (floorGrid[x][y]) {
                    print(" *");
                } else {
                    print("  ");
                }
            }
            print("\n");
        }
        print("  ");
        for (int i = 0; i < floorGrid.length; i++) {
            print(" " + i);
        }
        print("\n");
    }

    public void printCurrentPosition() {
        var currentPosition = "Position: " + location.getX() + ", " + location.getY() + " - Pen: "
                + (robot.getPenPosition() ? "down" : "up") + " - Facing: " + location.getDirection().toString() + "\n";
        print(currentPosition);
    }

    // public Instruction getInstruction() {
    // return instruction;
    // }

    // public void setInstruction(char shortName) {
    // this.instruction = Instruction.getFromShortName(shortName);
    // }

    public void setMove(int move) {
        this.moveSize = move;
    }

    public void setFloorSize(int floorSize) {
        this.floorSize = floorSize;
    }

    public void initialize() {
        location = new Location(new Point(0, 0), Direction.NORTH);
        pen = new Pen(false);
        floor = new Floor(new Point(floorSize, floorSize));
        robot = new Robot(location, pen, floor);
    }

    public void quit() {
        gameActive = false;
        scanner.close();
    }

    public void receiveInstruction(char instruction) {
        switch (Instruction.getFromShortName(instruction)) {
            case DOWN:
                robot.setPenPosition(true);
                break;
            case UP:
                robot.setPenPosition(false);
                break;
            case LEFT:
                robot.turnLeft();
                break;
            case RIGHT:
                robot.turnRight();
                break;
            case MOVE:
                robot.move(moveSize);
                break;
            case PRINT:
                printFloor();
                break;
            case CURRENT:
                printCurrentPosition();
                break;
            case QUIT:
                quit();
                break;
            case INITIALIZE:
                initialize();
                break;
            default:
                break;
        }
    }

    public static boolean isInt(String inputString) {
        try {
            Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
