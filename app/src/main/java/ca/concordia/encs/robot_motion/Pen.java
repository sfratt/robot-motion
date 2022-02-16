package ca.concordia.encs.robot_motion;

public class Pen {
    private boolean penPosition;

    public Pen(boolean penPosition) {
        this.penPosition = penPosition;
    }

    public boolean getPenPosition() {
        return penPosition;
    }

    /**
     * Sets pen in the up or down position.
     * 
     * @param penPosition pen position down when true, up when false
     */
    public void setPenPosition(boolean penPosition) {
        this.penPosition = penPosition;
    }
}
