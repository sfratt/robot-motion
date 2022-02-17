package ca.concordia.encs.robot_motion;

public class Floor {
    private final Point max;
    private boolean[][] floorGrid;

    /**
     * Initializes the floor grid to dimensions specified by maximum point
     * parameter.
     * 
     * @param max point object containing maximum axis values for the
     *            two-dimensional floor grid
     */
    public Floor(Point max) {
        this.max = max;
        floorGrid = new boolean[max.getX()][max.getY()];
    }

    public Point getMax() {
        return max;
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
     * Updates the floor grid values based on the difference in position change and
     * axis to apply the change to.
     * 
     * @param location
     * @param moveSize
     */
    public void setFloorGrid(Location location, int moveSize) throws ArrayIndexOutOfBoundsException {
        if (moveSize < 0 || moveSize >= getFloorGrid().length) {
            throw new IllegalArgumentException(moveSize + " is not a valid move");
        }

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
                break;
            case SOUTH:
                previousPosition = location.getY();
                newPosition = previousPosition - moveSize;
                for (int y = previousPosition; y >= newPosition; y--) {
                    floorGrid[location.getX()][y] = true;
                }
                break;
            case WEST:
                previousPosition = location.getX();
                newPosition = previousPosition - moveSize;
                for (int x = previousPosition; x >= newPosition; x--) {
                    floorGrid[x][location.getY()] = true;
                }
                break;
            default:
                break;
        }
    }
}
