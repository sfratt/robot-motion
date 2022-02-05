package ca.concordia.encs.robot_motion;

public class Floor {
    private final int floorLength;
    private boolean[][] floorGrid;
    private int horizontalAxisPosition = 0;
    private int verticalAxisPosition = 0;

    /**
     * Initializes the floor grid to dimensions specified by floor length parameter.
     * 
     * @param floorLength integer length N of the two-dimensional (N x N) floor grid
     */
    public Floor(int floorLength) {
        this.floorLength = floorLength;
        floorGrid = new boolean[floorLength][floorLength];
    }

    /**
     * Returns the current position on the x-axis.
     * 
     * @return current cell occupied on x-axis of floor grid
     */
    public int getHorizonatalAxisPosition() {
        return horizontalAxisPosition;
    }

    /**
     * Sets the new position on the x-axis relative to the current position.
     * 
     * @param positionChange integer value of positions to move on the x-axis
     * @throws IllegalArgumentException position being set outside x-axis
     */
    public void setHorizontalAxisPosition(int positionChange) {
        int newPosition = horizontalAxisPosition + positionChange;
        if (newPosition < 0 || newPosition >= floorLength) {
            throw new IllegalArgumentException("New horizontal position is outside floor grid");
        }
        horizontalAxisPosition = newPosition;
    }

    /**
     * Returns the current position on the y-axis.
     * 
     * @return current cell occupied on y-axis of floor grid
     */
    public int getVerticalAxisPosition() {
        return verticalAxisPosition;
    }

    /**
     * Sets the new position on the y-axis relative to the current position.
     * 
     * @param positionChange integer value of positions to move on the y-axis
     * @throws IllegalArgumentException position being set outside y-axis
     */
    public void setVerticalAxisPosition(int positionChange) {
        int newPosition = verticalAxisPosition + positionChange;
        if (newPosition < 0 || newPosition >= floorLength) {
            throw new IllegalArgumentException("New vertical position is outside floor grid");
        }
        verticalAxisPosition = newPosition;
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
     * @param positionChange integer value for number of positions to move
     * @param axis           direction on the grid the position change is affecting
     * @throws IllegalArgumentException axis parameter is outside permitted values
     */
    public void setFloorGrid(int positionChange, char axis) throws IllegalArgumentException {
        if (axis == 'X') {
            var previousPosition = getHorizonatalAxisPosition();
            setHorizontalAxisPosition(positionChange);
            if (positionChange > 0) {
                for (int x = previousPosition; x <= horizontalAxisPosition; x++) {
                    floorGrid[x][verticalAxisPosition] = true;
                }
            } else {
                for (int x = previousPosition; x >= horizontalAxisPosition; x--) {
                    floorGrid[x][verticalAxisPosition] = true;
                }
            }
        } else if (axis == 'Y') {
            var previousPosition = getVerticalAxisPosition();
            setVerticalAxisPosition(positionChange);
            if (positionChange > 0) {
                for (int y = previousPosition; y <= verticalAxisPosition; y++) {
                    floorGrid[horizontalAxisPosition][y] = true;
                }
            } else {
                for (int y = previousPosition; y >= verticalAxisPosition; y--) {
                    floorGrid[horizontalAxisPosition][y] = true;
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid axis value provided");
        }
    }
}
