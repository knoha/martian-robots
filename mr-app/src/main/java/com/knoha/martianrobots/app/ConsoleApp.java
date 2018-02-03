package com.knoha.martianrobots.app;

import com.knoha.martianrobots.app.exceptions.ConsoleApplicationException;
import com.knoha.martianrobots.core.models.robots.enums.CardinalDirection;
import com.knoha.martianrobots.core.models.robots.enums.RobotInstruction;
import com.knoha.martianrobots.core.models.robots.spi.Robot;
import com.knoha.martianrobots.core.models.surfaces.spi.Surface;
import com.knoha.martianrobots.core.services.robots.spi.RobotControlService;
import com.knoha.martianrobots.utils.log.ApplicationLog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleApp {

    private static final ApplicationLog LOG = new ApplicationLog(ConsoleApp.class.getName());

    private static ApplicationContext context;

    public static void main(String[] args) throws ConsoleApplicationException {
        LOG.print("Starting console application ...");

        context = new ClassPathXmlApplicationContext("app-context.xml");

        new ConsoleApp().run(args);

        LOG.print("Console application has finished.");
    }

    private void run(final String[] args) throws ConsoleApplicationException {
        if (args == null || args.length == 0) {
            LOG.print("No robots has been located on Mars surface. Aborting connection ...");
            return;
        }

        final Surface surface = buildWorldSurface(args[0]);
        final List<Robot> robots = prepareRobots(args);

        final RobotControlService robotControlService = context.getBean(RobotControlService.class);

        robotControlService.moveRobotsOnSurface(surface, robots);
    }

    private Surface buildWorldSurface(final String worldSizeString) throws ConsoleApplicationException {
        final String[] worldComponents = worldSizeString.trim().split("\\s");

        if (worldComponents.length != 2) {
            final String message = MessageFormat.format("Failed to create area on by this coordinates: {0}", Arrays.asList(worldComponents));
            throw new ConsoleApplicationException(message);
        }

        final int worldXCoordinate = Integer.parseInt(worldComponents[0], 10);
        final int worldYCoordinate = Integer.parseInt(worldComponents[1], 10);

        return context.getBean(Surface.class, worldXCoordinate, worldYCoordinate);
    }

    private List<Robot> prepareRobots(final String[] args) throws ConsoleApplicationException {
        final List<Robot> robots = new ArrayList<>();

        for (int i = 1; i < args.length; i = i + 2) {
            final String robotStartPointString = args[i];
            final String robotInstructionsString = args[i + 1];

            final String[] startPointComponents = robotStartPointString.trim().split("\\s");
            if (startPointComponents.length != 3) {
                final String message = MessageFormat.format("Failed to position robot by this coordinates: {0}", Arrays.asList(startPointComponents));
                throw new ConsoleApplicationException(message);
            }

            final int xCoordinate = Integer.parseInt(startPointComponents[0], 10);
            final int yCoordinate = Integer.parseInt(startPointComponents[1], 10);
            final CardinalDirection orientation = CardinalDirection.valueOf(startPointComponents[2]);

            final Robot robot = context.getBean(Robot.class, new Point(xCoordinate, yCoordinate), orientation);

            final String[] instructions = robotInstructionsString.trim().split("");
            for (final String instruction : instructions) {
                robot.addInstruction(RobotInstruction.valueOf(instruction));
            }

            robots.add(robot);
        }

        return robots;
    }

}
