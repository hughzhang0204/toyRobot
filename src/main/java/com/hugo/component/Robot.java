package com.hugo.component;

public class Robot {
    private boolean placed;
    private int x;
    private int y;
    private Direction facing;

    public void place(int x, int y, Direction facing) {
        if (isValidPlacement(x) && isValidPlacement(y)) {
            this.x = x;
            this.y = y;
            this.facing = facing;
            placed = true;
        }
    }

    public String report() {
        if (placed) {
            return (x + "," + y + "," + facing).toUpperCase();
        }

        return "";
    }

    public void left() {
        if (placed) {
            facing = facing.getPrev();
        }
    }

    public void right() {
        if (placed) {
            facing = facing.getNext();
        }
    }

    public void move() {
        if (placed) {
            moveForward();
        }
    }

    /**
     * Depending on the robot's direction, move it forward/back across the table X or Y plane.
     */
    private void moveForward() {
        switch (facing) {
            case NORTH:
                if (isValidPlacement(y + 1)) {
                    y++;
                }
                break;
            case SOUTH:
                if (isValidPlacement(y - 1)) {
                    y--;
                }
                break;
            case EAST:
                if (isValidPlacement(x + 1)) {
                    x++;
                }
                break;
            case WEST:
                if (isValidPlacement(x - 1)) {
                    x--;
                }
                break;
        }
    }

    /**
     * Checks if position is on the table.
     *
     * @param pos   Proposed position
     * @return      TRUE if position is on the table
     */
    private static boolean isValidPlacement(int pos) {
        return pos >= 0 && pos < Table.LENGTH;
    }
}
