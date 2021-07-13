import com.hugo.service.RobotService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class IntegrationTest{

    private RobotService service;

    @Before
    public void init() {
        service = new RobotService();
    }

    @Test
    public void testPlace() throws Exception {
        service.command("PLACE 0,0,NORTH");
        assertEquals("0,0,NORTH", service.command("REPORT"));
    }

    @Test
    public void testPlaceAndMove() throws Exception {
        service.command("PLACE 0,0,NORTH");
        service.command("MOVE");
        assertEquals("0,1,NORTH", service.command("REPORT"));
    }

    @Test
    public void testPlaceAndTurnLeft() throws Exception {
        service.command("PLACE 0,0,NORTH");
        service.command("LEFT");
        assertEquals("0,0,WEST", service.command("REPORT"));
    }

    @Test
    public void testPlaceAndTurnRight() throws Exception {
        service.command("PLACE 0,0,NORTH");
        service.command("RIGHT");
        assertEquals("0,0,EAST", service.command("REPORT"));
    }

    @Test
    public void testMultiPlace() throws Exception {
        service.command("PLACE 0,0,NORTH");
        service.command("MOVE");
        service.command("PLACE 3,4,SOUTH");
        assertEquals("3,4,SOUTH", service.command("REPORT"));
    }

    @Test
    public void testMoveAndLeft() throws Exception {
        service.command("PLACE 1,2,EAST");
        service.command("MOVE");
        service.command("MOVE");
        service.command("LEFT");
        service.command("MOVE");
        assertEquals("3,3,NORTH", service.command("REPORT"));
    }

    @Test
    public void testInvalidPlace() throws Exception {
        assertEquals("Invalid command, Please re-input!", service.command("PLACE -1,0,NORTH"));
    }

    @Test
    public void shouldNotFallOffTable() throws Exception {
        service.command("PLACE 10,2,EAST");
        service.command("MOVE");
        service.command("PLACE 4,4,NORTH");
        service.command("MOVE");
        service.command("LEFT");

        for (int i = 0; i < 7; i++) {
            service.command("MOVE");
        }

        assertEquals("0,4,WEST", service.command("REPORT"));
    }
}
