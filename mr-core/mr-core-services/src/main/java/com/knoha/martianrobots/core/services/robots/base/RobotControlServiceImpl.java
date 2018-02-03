package com.knoha.martianrobots.core.services.robots.base;

import com.knoha.martianrobots.core.models.robots.enums.RobotInstruction;
import com.knoha.martianrobots.core.models.robots.spi.Robot;
import com.knoha.martianrobots.core.models.surfaces.spi.Surface;
import com.knoha.martianrobots.core.services.ServiceDefinitions;
import com.knoha.martianrobots.core.services.robots.spi.RobotControlService;
import com.knoha.martianrobots.utils.log.ApplicationLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(ServiceDefinitions.ServiceNames.ROBOT_CONTROL)
public class RobotControlServiceImpl implements RobotControlService {

    private static final ApplicationLog LOG = new ApplicationLog(RobotControlServiceImpl.class.getName());

    @Override
    public void moveRobotsOnSurface(final Surface surface, final List<Robot> robots) {
        if (surface == null) {
            LOG.print("Surface not found.");
            return;
        }

        for (int i = 1; i <= robots.size(); i++) {
            LOG.print("Putting robot {0} on a surface.", i);

            final Robot robot = robots.get(i - 1);
            robot.setSurface(surface);

            RobotInstruction nextInstruction = robot.popInstruction();
            while (nextInstruction != null) {
                executeInstruction(robot, nextInstruction);

                robot.inBounds();

                nextInstruction = robot.popInstruction();
            }
        }

        LOG.print("Discovered area:");
        surface.printArea();

        LOG.print("Current position of {0} robot(s):", robots.size());
        robots.forEach(r -> LOG.print(r.toString()));
    }

    private void executeInstruction(final Robot robot, final RobotInstruction instruction) {
        if (RobotInstruction.L.equals(instruction)) {
            robot.rotateLeft();
        }

        if (RobotInstruction.R.equals(instruction)) {
            robot.rotateRight();
        }

        if (RobotInstruction.F.equals(instruction)) {
            robot.moveOneStepForward();
        }
    }
}
