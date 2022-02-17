package ca.concordia.encs.robot_motion;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class RobotMotion {
    private Robot robot;
    private Instruction instruction;
    private Location location;
    private Pen pen;
    private Floor floor;
    private int move;

    public RobotMotion() {
        location = new Location(new Point(0, 0), Direction.NORTH);
        pen = new Pen(false);
        floor = new Floor(new Point(10, 10));
        robot = new Robot(location, pen, floor);
    }

    public void run() throws NoSuchElementException {
        while (true) {
            System.out.println("Calling from robot motion");
            String firstInput;
            System.out.print("Enter command: ");
            var scanner = new Scanner(System.in);
            firstInput = scanner.next(); // reads input
            System.out.println("First input test: " + firstInput);
            scanner.close();
        }
    }

    public void print(String output) {
        System.out.println(output);
    }

    public void printFloor() {
        var floorGrid = robot.getFloor().getFloorGrid();

        for (int y = floorGrid.length - 1; y >= 0; y--) {
            System.out.print(y);
            for (int x = 0; x < floorGrid.length; x++) {
                if (floorGrid[x][y]) {
                    System.out.print(" *");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print("\n");
        }
        System.out.print(" ");
        for (int i = 0; i < floorGrid.length; i++) {
            System.out.print(" " + i);
        }
        System.out.print("\n");
    }

    public void printCurrentPosition() {
        var currentPosition = "Position: " + location.getX() + ", " + location.getY() + " - Pen: "
                + (robot.getPenPosition() ? "down" : "up") + " - Facing: " + location.getDirection().toString();
        print(currentPosition);
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(char shortName) {
        this.instruction = Instruction.getFromShortName(shortName);
    }

    public void setMove(int move) {
        this.move = move;
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
                robot.move(move);
                break;
            case PRINT:
                printFloor();
                // print(instruction);
                break;
            case CURRENT:
                printCurrentPosition();
                break;
            default:
                break;
        }
    }
}
