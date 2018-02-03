package com.knoha.martianrobots.core.services.robots.spi;

import com.knoha.martianrobots.core.models.robots.spi.Robot;
import com.knoha.martianrobots.core.models.surfaces.spi.Surface;

import java.util.List;

public interface RobotControlService {

    void moveRobotsOnSurface(Surface surface, List<Robot> robots);

}
