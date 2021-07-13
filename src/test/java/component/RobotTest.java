package component;

import com.hugo.component.Direction;
import com.hugo.component.Robot;
import com.hugo.component.Table;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RobotTest {
    private Robot robot;

    @Before
    public void init() {
        robot = new Robot();
    }

    @Test
    public void testPlaceInNegativePosition() throws Exception {
        robot.place(-1, -1, Direction.NORTH);
        assertEquals(robot.report(), "");
    }

    @Test
    public void testPlaceOffTable() throws Exception {
        robot.place(Table.LENGTH + 1, Table.LENGTH + 1, Direction.NORTH);
        assertEquals(robot.report(), "");
    }

    @Test
    public void testIgnoreLeftWithoutPlaced() throws Exception {
        robot.left();
        assertEquals(robot.report(), "");
    }

    @Test
    public void testIgnoreRightWithoutPlaced() throws Exception {
        robot.right();
        assertEquals(robot.report(), "");
    }

    @Test
    public void testIgnoreWithoutPlaced() throws Exception {
        robot.report();
        assertEquals(robot.report(), "");
    }

    @Test
    public void testPlaceOnTable() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        assertEquals(robot.report(), "0,0,NORTH");
    }

    @Test
    public void testReplaceOnTable() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        robot.place(3, 3, Direction.SOUTH);
        assertEquals(robot.report(), "3,3,SOUTH");
    }

    @Test
    public void testRotateLeft() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        robot.left();
        assertEquals(robot.report(), "0,0,WEST");
    }

    @Test
    public void testRotateLeftTwice() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        robot.left();
        robot.left();
        assertEquals(robot.report(), "0,0,SOUTH");
    }

    @Test
    public void testRotateLeftThrice() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        for (int i = 0; i < 3; i++) {
            robot.left();
        }
        assertEquals(robot.report(), "0,0,EAST");
    }

    @Test
    public void testRotateLeftFullRotation() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        for (int i = 0; i < 4; i++) {
            robot.left();
        }
        assertEquals(robot.report(), "0,0,NORTH");
    }

    @Test
    public void testRotateRight() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        robot.right();
        assertEquals(robot.report(), "0,0,EAST");
    }

    @Test
    public void testRotateRightTwice() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        robot.right();
        robot.right();
        assertEquals(robot.report(), "0,0,SOUTH");
    }

    @Test
    public void testRotateRightThrice() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        for (int i = 0; i < 3; i++) {
            robot.right();
        }
        assertEquals(robot.report(), "0,0,WEST");
    }

    @Test
    public void testRotateRightFullRotation() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        for (int i = 0; i < 4; i++) {
            robot.right();
        }
        assertEquals(robot.report(), "0,0,NORTH");
    }

    @Test
    public void testMoveOnce() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        robot.move();
        assertEquals(robot.report(), "0,1,NORTH");
    }

    @Test
    public void testMoveTwice() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        robot.move();
        robot.move();
        assertEquals(robot.report(), "0,2,NORTH");
    }

    @Test
    public void testIgnoreMovingOffTable() throws Exception {
        robot.place(0, 0, Direction.NORTH);
        for (int i = 0; i < Table.LENGTH + 1; i++) {
            robot.move();
        }
        assertEquals(robot.report(), "0,4,NORTH");
    }

    @Test
    public void testCombineMovingAndRotating() throws Exception {
        robot.place(1, 2, Direction.EAST);
        robot.move();
        robot.move();
        robot.left();
        robot.move();
        assertEquals(robot.report(), "3,3,NORTH");
    }

    @Test
    public void testIgnoreMovingOffTableAllSides() throws Exception {
        robot.place(0, 0, Direction.NORTH);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < Table.LENGTH + 1; j++) {
                robot.move();
            }

            robot.right();
        }

        assertEquals(robot.report(), "0,0,NORTH");
    }
}
