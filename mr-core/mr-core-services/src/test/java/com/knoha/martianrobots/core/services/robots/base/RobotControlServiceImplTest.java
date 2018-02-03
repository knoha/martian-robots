package com.knoha.martianrobots.core.services.robots.base;

import com.knoha.martianrobots.core.models.robots.enums.RobotInstruction;
import com.knoha.martianrobots.core.models.robots.spi.Robot;
import com.knoha.martianrobots.core.models.surfaces.spi.Surface;
import com.knoha.martianrobots.core.services.robots.spi.RobotControlService;
import com.knoha.martianrobots.test.BaseMockTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class RobotControlServiceImplTest extends BaseMockTest {

    private RobotControlService service;

    private Surface surface;

    @Override
    protected void prepareMocks() {
        service = new RobotControlServiceImpl();

        surface = mock(Surface.class);
    }

    @Test
    public void moveRobotsOnSurface() {
        final Robot r1 = mock(Robot.class);
        when(r1.popInstruction()).thenReturn(RobotInstruction.R).thenReturn(null);

        final Robot r2 = mock(Robot.class);
        when(r2.popInstruction()).thenReturn(RobotInstruction.L).thenReturn(null);

        final Robot r3 = mock(Robot.class);
        when(r3.popInstruction()).thenReturn(RobotInstruction.F).thenReturn(null);

        final List<Robot> robots = new ArrayList<>();
        robots.add(r1);
        robots.add(r2);
        robots.add(r3);

        service.moveRobotsOnSurface(surface, robots);

        verify(r1, times(1)).setSurface(surface);
        verify(r2, times(1)).setSurface(surface);
        verify(r3, times(1)).setSurface(surface);

        verify(r1, times(2)).popInstruction();
        verify(r2, times(2)).popInstruction();
        verify(r3, times(2)).popInstruction();

        verify(r1, times(1)).rotateRight();
        verify(r2, times(1)).rotateLeft();
        verify(r3, times(1)).moveOneStepForward();
    }
}