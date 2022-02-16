package ca.concordia.encs.robot_motion;

public class Location {
    private Point point;
    private Direction direction;

    public Location(Point point, Direction direction) {
        this.point = point;
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void turnLeft() {
        this.direction = direction.turnLeft();
    }

    public void turnRight() {
        this.direction = direction.turnRight();
    }

    public boolean move(int moveSize, Point max) {
        int x = getX();
        int y = getY();

        switch (getDirection()) {
            case NORTH:
                y += moveSize;
                break;
            case EAST:
                x += moveSize;
                break;
            case SOUTH:
                y -= moveSize;
                break;
            case WEST:
                x -= moveSize;
                break;
            default:
                return false;
        }
        if (isValidMove(new Point(x, y), max)) {
            point = new Point(x, y);
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidMove(Point point, Point max) {
        if ((point.getX() >= 0 && point.getX() < max.getX()) && (point.getY() >= 0 && point.getY() < max.getY())) {
            return true;
        }
        return false;
    }

    public Location copy() {
        return new Location(new Point(point.getX(), point.getY()), direction);
    }
}
