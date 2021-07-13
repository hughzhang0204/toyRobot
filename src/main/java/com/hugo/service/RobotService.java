package com.hugo.service;

import com.hugo.component.Command;
import com.hugo.component.Direction;
import com.hugo.component.Robot;
import org.springframework.util.StringUtils;

public class RobotService {
    private static String ERROR_INVALID_COMMAND = "Invalid command, Please re-input!";

    private Robot robot;

    public RobotService() {
        robot = new Robot();
    }

    public void setRobot(Robot robot){
        this.robot = robot;
    }

    /**
     * Calls the appropriate Robot method depending on input.
     *
     * @param input     Raw command
     * @return          Report text, invalid input error message or empty string
     */
    public String command(String input) {
        if (!StringUtils.isEmpty(input)) {

            input = input.trim().toUpperCase();
            Command command = Command.get(input);

            switch(command) {
                case MOVE:
                    robot.move();
                    break;
                case LEFT:
                    robot.left();
                    break;
                case RIGHT:
                    robot.right();
                    break;
                case REPORT:
                    return robot.report();
                default:
                    if (this.isValidPlaceCommand(input)) {
                        this.executePlaceCommand(input);
                    } else {
                        return ERROR_INVALID_COMMAND;
                    }
            }
        }

        return ERROR_INVALID_COMMAND;
    }

    /**
     * Parses the input and calls the PLACE command.
     *
     * @param input     Raw input
     */
    private void executePlaceCommand(String input) {
        String[] args = input.replaceAll("PLACE ", "").split(",");

        int x = Integer.valueOf(args[0]);
        int y = Integer.valueOf(args[1]);
        Direction facing = Direction.valueOf(args[2].toUpperCase());

        robot.place(x, y, facing);
    }

    /**
     * Matches the PLACE command input against a regular expression.
     *
     * @param input     Place command input
     * @return          TRUE if a valid-formatted command
     */
    private boolean isValidPlaceCommand(String input) {
        return input.matches("^PLACE [0-9]+,[0-9]+,(EAST|WEST|NORTH|SOUTH)");
    }
}
