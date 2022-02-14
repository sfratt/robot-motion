package ca.concordia.encs.robot_motion;

public class Pen {
    private boolean penPositionDown;

    public Pen() {
        penPositionDown = false;
    }

    // public Pen(boolean penPosition) {
    // isPenDown = penPosition;
    // }

    public boolean isPenPositionDown() {
        return penPositionDown;
    }

    public void setPenPositionDown(boolean penPositionDown) {
        this.penPositionDown = penPositionDown;
    }
}
