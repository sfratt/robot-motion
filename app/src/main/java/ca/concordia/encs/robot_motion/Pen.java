package ca.concordia.encs.robot_motion;

public class Pen {
    private boolean penPositionDown;

    public Pen(boolean penPositionDown) {
        this.penPositionDown = penPositionDown;
    }

    public boolean isPenPositionDown() {
        return penPositionDown;
    }

    public void setPenPositionDown(boolean penPositionDown) {
        this.penPositionDown = penPositionDown;
    }
}
