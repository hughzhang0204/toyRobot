package service;

import com.hugo.component.Direction;
import com.hugo.component.Robot;
import com.hugo.service.RobotService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RobotServiceTest {
    private static String ERROR_INVALID_COMMAND = "Invalid command, Please re-input!";
    private static String STUB_REPORT = "mock report";

    private RobotService service;

    @Mock
    private Robot robot;

    @Before
    public void init() {
        service = new RobotService();
        service.setRobot(robot);
    }

    @Test
    public void shouldHaveValidFirstArgumentForPlaceCommand() throws Exception {
        assertEquals(ERROR_INVALID_COMMAND, service.command("PLACE FOO,1,NORTH"));
    }

    @Test
    public void shouldHaveValidSecondArgumentForPlaceCommand() throws Exception {
        assertEquals(ERROR_INVALID_COMMAND, service.command("PLACE 1,FOO,NORTH"));
    }

    @Test
    public void shouldHaveValidThirdArgumentForPlaceCommand() throws Exception {
        assertEquals(ERROR_INVALID_COMMAND, service.command("PLACE 1,1,FOO"));
    }

    @Test
    public void shouldIgnoreBadInput() throws Exception {
        assertEquals(ERROR_INVALID_COMMAND, service.command("FOO BAR"));
    }

    @Test
    public void shouldIgnoreMissingInput() throws Exception {
        assertEquals(ERROR_INVALID_COMMAND, service.command(""));
    }

    @Test
    public void shouldCallPlace() throws Exception {
        service.command("PLACE 123,456,NORTH");
        verify(robot).place(123, 456, Direction.NORTH);
    }

    @Test
    public void shouldCallMove() throws Exception {
        service.command("MOVE");
        verify(robot).move();
    }

    @Test
    public void shouldCallLeft() throws Exception {
        service.command("LEFT");
        verify(robot).left();
    }

    @Test
    public void shouldCallRight() throws Exception {
        service.command("RIGHT");
        verify(robot).right();
    }

    @Test
    public void shouldCallReport() throws Exception {
        when(robot.report()).thenReturn(STUB_REPORT);
        assertEquals(STUB_REPORT, service.command("REPORT"));
    }

    @Test
    public void shouldBeCaseInsensitive() throws Exception {
        service.command("mOvE");
        verify(robot).move();
    }
}
