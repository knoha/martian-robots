package com.knoha.martianrobots.core.models.robots.base;

import com.knoha.martianrobots.core.models.robots.enums.CardinalDirection;
import com.knoha.martianrobots.core.models.robots.enums.RobotInstruction;
import com.knoha.martianrobots.core.models.robots.spi.Robot;
import com.knoha.martianrobots.core.models.surfaces.base.MartianSurfaceImpl;
import com.knoha.martianrobots.core.models.surfaces.spi.Surface;
import com.knoha.martianrobots.test.BaseMockTest;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class MartianRobotImplTest extends BaseMockTest {

    private Robot robot;

    private Point startPoint;

    @Override
    protected void prepareMocks() {
        startPoint = new Point(4, 5);

        robot = new MartianRobotImpl(startPoint, CardinalDirection.W);
    }

    @Test
    public void constructors() {
        final Robot r1 = new MartianRobotImpl();
        assertNotNull(r1);
        assertNotNull(r1.getStartPoint());
        assertEquals(r1.getStartPoint(), r1.getPreviousPoint());
        assertEquals(r1.getStartPoint(), r1.getCurrentPoint());
        assertNotNull(r1.getOrientation());
        assertEquals(CardinalDirection.N, r1.getOrientation());
        assertNull(r1.popInstruction());

        final Robot r2 = new MartianRobotImpl(new Point(1, 1), CardinalDirection.W);
        assertNotNull(r2);
        assertNotNull(r2.getStartPoint());
        assertEquals(r2.getStartPoint(), r2.getPreviousPoint());
        assertEquals(r2.getStartPoint(), r2.getCurrentPoint());
        assertNotNull(r2.getOrientation());
        assertEquals(CardinalDirection.W, r2.getOrientation());
        assertNull(r2.popInstruction());
    }

    @Test
    public void getStartPoint() {
        final Point point = robot.getStartPoint();
        assertNotNull(point);
        assertEquals(point, startPoint);
    }

    @Test
    public void getCurrentPoint() {
        final Point point = robot.getCurrentPoint();
        assertNotNull(point);
        assertEquals(point, startPoint);
    }

    @Test
    public void getPreviousPoint() {
        final Point point = robot.getPreviousPoint();
        assertNotNull(point);
        assertEquals(point, startPoint);
    }

    @Test
    public void getOrientation() {
        assertEquals(CardinalDirection.W, robot.getOrientation());
    }

    @Test
    public void rotateRight() {
        robot.rotateRight();
        assertEquals(CardinalDirection.N, robot.getOrientation());

        robot.rotateRight();
        assertEquals(CardinalDirection.E, robot.getOrientation());

        robot.rotateRight();
        assertEquals(CardinalDirection.S, robot.getOrientation());

        robot.rotateRight();
        assertEquals(CardinalDirection.W, robot.getOrientation());
    }

    @Test
    public void rotateLeft() {
        robot.rotateLeft();
        assertEquals(CardinalDirection.S, robot.getOrientation());

        robot.rotateLeft();
        assertEquals(CardinalDirection.E, robot.getOrientation());

        robot.rotateLeft();
        assertEquals(CardinalDirection.N, robot.getOrientation());

        robot.rotateLeft();
        assertEquals(CardinalDirection.W, robot.getOrientation());
    }

    @Test
    public void moveOneStepForward() {
        Point currentPoint = new Point(robot.getCurrentPoint());
        robot.moveOneStepForward();
        assertEquals(3, robot.getCurrentPoint().x);
        assertEquals(5, robot.getCurrentPoint().y);
        assertEquals(currentPoint, robot.getPreviousPoint());

        robot.rotateRight();
        robot.moveOneStepForward();
        assertEquals(3, robot.getCurrentPoint().x);
        assertEquals(6, robot.getCurrentPoint().y);

        robot.rotateRight();
        robot.moveOneStepForward();
        assertEquals(4, robot.getCurrentPoint().x);
        assertEquals(6, robot.getCurrentPoint().y);

        robot.rotateRight();
        robot.moveOneStepForward();
        assertEquals(4, robot.getCurrentPoint().x);
        assertEquals(5, robot.getCurrentPoint().y);
    }

    @Test
    public void moveOneStepForward_EdgePointMeat() {
        final Surface surface = new MartianSurfaceImpl(5, 5);
        robot.setSurface(surface);

        robot.rotateRight();
        robot.moveOneStepForward();
        assertFalse(robot.inBounds());
        assertEquals(4, robot.getCurrentPoint().x);
        assertEquals(6, robot.getCurrentPoint().y);

        robot.rotateRight();
        robot.rotateRight();
        robot.moveOneStepForward();
        robot.moveOneStepForward();
        assertTrue(robot.inBounds());
        assertEquals(4, robot.getCurrentPoint().x);
        assertEquals(4, robot.getCurrentPoint().y);

        robot.rotateRight();
        robot.rotateRight();
        robot.moveOneStepForward();
        robot.moveOneStepForward();
        assertTrue(robot.inBounds());
        assertEquals(4, robot.getCurrentPoint().x);
        assertEquals(5, robot.getCurrentPoint().y);

        robot.moveOneStepForward();
        assertTrue(robot.inBounds());
        assertEquals(4, robot.getCurrentPoint().x);
        assertEquals(5, robot.getCurrentPoint().y);
    }

    @Test
    public void addInstruction() {
        assertNull(robot.popInstruction());

        robot.addInstruction(RobotInstruction.R);
        assertEquals(RobotInstruction.R, robot.popInstruction());
        assertNull(robot.popInstruction());
    }

    @Test
    public void getSurface() {
        assertNull(robot.getSurface());
    }

    @Test
    public void setSurface() {
        final Surface surface = new MartianSurfaceImpl(1, 1);
        robot.setSurface(surface);
        assertEquals(surface, robot.getSurface());
    }

    @Test
    public void inBounds() {
        Surface surface = new MartianSurfaceImpl(1, 1);
        robot.setSurface(surface);
        assertFalse(robot.inBounds());

        surface = new MartianSurfaceImpl(10, 10);
        robot.setSurface(surface);
        assertTrue(robot.inBounds());
    }

    @Test
    public void testToString() {
        assertEquals("4 5 W", robot.toString());
    }
}