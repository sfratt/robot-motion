package ca.concordia.encs.robot_motion;

public class Robot {
    private final Location location;
    private Pen pen;
    private Floor floor;

    public Robot(Location location, Pen pen, Floor floor) {
        this.location = location;
        this.pen = pen;
        this.floor = floor;
    }

    public Location getLocation() {
        return location;
    }

    public void turnLeft() {
        location.turnLeft();
    }

    public void turnRight() {
        location.turnRight();
    }

    public boolean getPenPosition() {
        return pen.getPenPosition();
    }

    public void setPenPosition(boolean penPosition) {
        pen.setPenPosition(penPosition);
    }

    public Floor getFloor() {
        return floor;
    }

    public void move(int moveSize) {
        if (getPenPosition()) {
            var previousLocation = location.copy();
            if (location.move(moveSize, floor.getMax())) {
                floor.setFloorGrid(previousLocation, moveSize);
            } else {
                throw new IllegalArgumentException(moveSize + " is an invalid move");
            }
        } else {
            if (!location.move(moveSize, floor.getMax())) {
                throw new IllegalArgumentException(moveSize + " is an invalid move");
            }
        }
    }
}
