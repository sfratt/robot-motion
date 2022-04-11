package ca.concordia.encs.robot_motion;

import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;

public class RobotMotion {
    private Robot robot;
    private Location location;
    private Pen pen;
    private Floor floor;
    private int moveSize;
    private int floorSize;
    private Scanner scanner;
    private boolean gameActive;
    private TreeMap<Long, String> history;

    public RobotMotion() {
        location = new Location(new Point(0, 0), Direction.NORTH);
        pen = new Pen(false);
        floor = new Floor(new Point(0, 0));
        robot = new Robot(location, pen, floor);
        history = new TreeMap<Long, String>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        gameActive = true;
        while (gameActive) {
            print("Enter command: ");
            receiveInput();
        }
    }

    public void print(String output) {
        System.out.print(output);
    }

    public void printFloor() {
        var floorGrid = robot.getFloor().getFloorGrid();

        for (int y = floorGrid.length - 1; y >= 0; y--) {
            System.out.format("%2d", y);
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

    public String getCurrentPosition() {
        return "Position: " + location.getX() + ", " + location.getY() + " - Pen: "
                + (robot.getPenPosition() ? "down" : "up") + " - Facing: " + location.getDirection().name() + "\n";
    }

    public void printCurrentPosition() {
        print(getCurrentPosition());
    }

    public void addHistory(String instruction) {
        try {
            Instruction.getFromShortName(instruction.charAt(0));
            history.put(new Date().getTime(), "Instruction: " + instruction.charAt(0) + " - " + getCurrentPosition());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public TreeMap<Long, String> getHistory() {
        return history;
    }

    public void printHistory() {
        for (var value : history.values()) {
            print(value);
        }
    }

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
        history = new TreeMap<Long, String>();
    }

    public Scanner getScanner() {
        return scanner;
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
            case HISTORY:
                printHistory();
                break;
            default:
                break;
        }
    }

    public void receiveInput() {
        String firstInput = "";
        String secondInput = "";
        String instruction = "";
        int instructionNumber = 0;

        try {
            firstInput = scanner.next();

            // TODO: Handle empty string and invalid character input
            instruction = firstInput.toUpperCase();
            if (robot.getFloor().getFloorGrid().length == 0 && !instruction.equals("I")) {
                print("Please start by initializing the floor.\n");
                return;
            }
            if (instruction.equals("I") || instruction.equals("M")) {
                secondInput = scanner.next();
                if (isInt(secondInput)) {
                    if (instruction.equals("I")) {
                        if (Integer.parseInt(secondInput) > 0) {
                            instructionNumber = Integer.parseInt(secondInput);
                        } else {
                            print("Please enter a positive integer value for floor size.\n");
                        }
                        setFloorSize(instructionNumber);
                    } else {
                        instructionNumber = Integer.parseInt(secondInput);
                        setMove(instructionNumber);
                    }
                } else {
                    print("Please enter an integer value.\n");
                }
            }

            receiveInstruction(instruction.charAt(0));
            addHistory(instruction);
        } catch (Exception e) {
            print(e.getMessage() + "\n");
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
