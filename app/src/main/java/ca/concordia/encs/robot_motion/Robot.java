package ca.concordia.encs.robot_motion;

public class Robot {
    private boolean penPosition;
    // private String direction;

    public Robot() {
        // penPosition = false;
        // direction = "North";
    }

    public boolean getPenPosition() {
        return penPosition;
    }

    public void setPenPosition(boolean position) {
        penPosition = position;
        // penPosition = !penPosition;
    }

    // TODO: if pen down, setFloorGrid, else if pen up, set x/y-axis with setters
}
