package ca.concordia.encs.robot_motion;

public class Floor {
    private final Point max;
    private boolean[][] floorGrid;
    private Point point;

    /**
     * Initializes the floor grid to dimensions specified by floor length parameter.
     * 
     * @param floorLength integer length N of the two-dimensional (N x N) floor grid
     */
    public Floor(Point max, Point point) {
        this.max = max;
        this.point = point;
        floorGrid = new boolean[max.getX()][max.getY()];
    }

    /**
     * Returns the current position on the x-axis.
     * 
     * @return current cell occupied on x-axis of floor grid
     */
    public int getHorizonatalAxisPosition() {
        return point.getX();
    }

    /**
     * Sets the new position on the x-axis relative to the current position.
     * 
     * @param positionChange integer value of positions to move on the x-axis
     * @throws IllegalArgumentException position being set outside x-axis
     */
    public void setHorizontalAxisPosition(int positionChange) {
        int newPosition = point.getX() + positionChange;
        if (newPosition < 0 || newPosition >= max.getX()) {
            throw new IllegalArgumentException("New horizontal position is outside floor grid");
        }
        point.setX(newPosition);
    }

    /**
     * Returns the current position on the y-axis.
     * 
     * @return current cell occupied on y-axis of floor grid
     */
    public int getVerticalAxisPosition() {
        return point.getY();
    }

    /**
     * Sets the new position on the y-axis relative to the current position.
     * 
     * @param positionChange integer value of positions to move on the y-axis
     * @throws IllegalArgumentException position being set outside y-axis
     */
    public void setVerticalAxisPosition(int positionChange) {
        int newPosition = point.getY() + positionChange;
        if (newPosition < 0 || newPosition >= max.getY()) {
            throw new IllegalArgumentException("New vertical position is outside floor grid");
        }
        point.setY(newPosition);
    }

    /**
     * Returns the current state of the floor grid.
     * 
     * @return two-dimensional array of boolean values
     */
    public boolean[][] getFloorGrid() {
        return floorGrid;
    }

    /**
     * Updates the grid values based on the difference in position change and axis
     * to apply the change to.
     * 
     * @param axis           direction on the grid the position change is affecting
     * @param positionChange integer value for number of positions to move
     * @throws IllegalArgumentException axis parameter is outside permitted values
     */
    public void setFloorGrid(char axis, int positionChange) throws IllegalArgumentException {
        int previousPosition;
        switch (axis) {
            case 'X':
                previousPosition = getHorizonatalAxisPosition();
                setHorizontalAxisPosition(positionChange);
                if (positionChange >= 0) {
                    for (int x = previousPosition; x <= point.getX(); x++) {
                        floorGrid[x][point.getY()] = true;
                    }
                } else {
                    for (int x = previousPosition; x >= point.getX(); x--) {
                        floorGrid[x][point.getY()] = true;
                    }
                }
                break;
            case 'Y':
                previousPosition = getVerticalAxisPosition();
                setVerticalAxisPosition(positionChange);
                if (positionChange >= 0) {
                    for (int y = previousPosition; y <= point.getY(); y++) {
                        floorGrid[point.getX()][y] = true;
                    }
                } else {
                    for (int y = previousPosition; y >= point.getY(); y--) {
                        floorGrid[point.getX()][y] = true;
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid axis value provided");
        }
    }

    public void setFloorGrid(Location location, int moveSize) {
        int previousPosition;
        int newPosition;

        switch (location.getDirection()) {
            case NORTH:
                previousPosition = location.getY();
                newPosition = previousPosition + moveSize;
                for (int y = previousPosition; y <= newPosition; y++) {
                    floorGrid[location.getX()][y] = true;
                }
                break;
            case EAST:
                previousPosition = location.getX();
                newPosition = previousPosition + moveSize;
                for (int x = previousPosition; x <= newPosition; x++) {
                    floorGrid[x][location.getY()] = true;
                }
            default:
                break;
        }
    }
}
