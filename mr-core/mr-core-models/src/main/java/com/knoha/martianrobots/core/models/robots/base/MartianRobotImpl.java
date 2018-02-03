package com.knoha.martianrobots.core.models.robots.base;

import com.knoha.martianrobots.core.models.ModelDefinitions;
import com.knoha.martianrobots.core.models.robots.enums.CardinalDirection;
import com.knoha.martianrobots.core.models.robots.enums.RobotInstruction;
import com.knoha.martianrobots.core.models.robots.spi.Robot;
import com.knoha.martianrobots.core.models.surfaces.enums.SurfaceFootprints;
import com.knoha.martianrobots.core.models.surfaces.spi.Surface;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component(ModelDefinitions.ModelNames.MARTIAN_ROBOT)
public class MartianRobotImpl implements Robot {

    private Point startPoint;
    private Point currentPoint;
    private Point previousPoint;

    private CardinalDirection orientation = CardinalDirection.N;

    private Queue<RobotInstruction> instructions;

    private Surface surface;

    private boolean lost = false;

    public MartianRobotImpl() {
        startPoint = new Point();
        currentPoint = new Point(startPoint);
        previousPoint = new Point(startPoint);
        instructions = new LinkedList<>();
    }

    public MartianRobotImpl(final Point startPoint, final CardinalDirection startOrientation) {
        Objects.requireNonNull(startPoint, "Start point is required.");

        this.startPoint = new Point(startPoint);
        this.currentPoint = new Point(startPoint);
        this.previousPoint = new Point(startPoint);

        this.orientation = startOrientation;

        this.instructions = new LinkedList<>();
    }

    @Override
    public Point getStartPoint() {
        return new Point(startPoint);
    }

    @Override
    public Point getCurrentPoint() {
        return currentPoint;
    }

    @Override
    public Point getPreviousPoint() {
        return previousPoint;
    }

    @Override
    public CardinalDirection getOrientation() {
        return orientation;
    }

    @Override
    public void rotateRight() {
        orientation = RIGHT_ROTATION_90_MAP.get(orientation);
    }

    @Override
    public void rotateLeft() {
        orientation = LEFT_ROTATION_90_MAP.get(orientation);
    }

    @Override
    public void moveStepsForward(final int steps) {
        int currentX = currentPoint.x;
        int currentY = currentPoint.y;

        if (CardinalDirection.N.equals(orientation)) {
            currentY += steps;
        } else if (CardinalDirection.E.equals(orientation)) {
            currentX += steps;
        } else if (CardinalDirection.S.equals(orientation)) {
            currentY -= steps;
        } else if (CardinalDirection.W.equals(orientation)) {
            currentX -= steps;
        }

        final Point nextPoint = new Point(currentX, currentY);
        if (surface != null && surface.getEdgePoints().contains(nextPoint)) {
            return;
        }

        previousPoint = new Point(currentPoint);

        currentPoint.setLocation(currentX, currentY);
    }

    @Override
    public void moveOneStepForward() {
        moveStepsForward(1);
    }

    @Override
    public void addInstruction(final RobotInstruction instruction) {
        instructions.add(instruction);
    }

    @Override
    public RobotInstruction popInstruction() {
        return instructions.poll();
    }

    @Override
    public Surface getSurface() {
        return surface;
    }

    @Override
    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    @Override
    public boolean inBounds() {
        if (surface == null || surface.getArea() == null || surface.getArea().length == 0) {
            return false;
        }

        try {
            surface.getArea()[currentPoint.y][currentPoint.x] = SurfaceFootprints.D.name();
            return true;
        } catch (final ArrayIndexOutOfBoundsException e) {
            surface.getEdgePoints().add(new Point(currentPoint));

            lost = true;

            return false;
        }
    }

    @Override
    public String toString() {
        final String defaultLocationTemplate = "{0} {1} {2}";
        final String lostLocationTemplate = "{0} {1} {2} {3}";

        final int x = currentPoint.x;
        final int y = currentPoint.y;

        return lost ?
                MessageFormat.format(lostLocationTemplate, x, y, orientation.name(), "LOST") :
                MessageFormat.format(defaultLocationTemplate, x, y, orientation.name());
    }
}
