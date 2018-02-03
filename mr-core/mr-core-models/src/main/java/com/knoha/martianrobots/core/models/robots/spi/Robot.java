package com.knoha.martianrobots.core.models.robots.spi;

import com.knoha.martianrobots.core.models.robots.enums.CardinalDirection;
import com.knoha.martianrobots.core.models.robots.enums.RobotInstruction;
import com.knoha.martianrobots.core.models.surfaces.spi.Surface;

import java.awt.*;
import java.io.Serializable;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Robot extends Serializable {

    Map<CardinalDirection, CardinalDirection> RIGHT_ROTATION_90_MAP = Collections.unmodifiableMap(Stream.of(
            new SimpleEntry<>(CardinalDirection.N, CardinalDirection.E),
            new SimpleEntry<>(CardinalDirection.E, CardinalDirection.S),
            new SimpleEntry<>(CardinalDirection.S, CardinalDirection.W),
            new SimpleEntry<>(CardinalDirection.W, CardinalDirection.N)
    ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)));

    Map<CardinalDirection, CardinalDirection> LEFT_ROTATION_90_MAP = Collections.unmodifiableMap(Stream.of(
            new SimpleEntry<>(CardinalDirection.N, CardinalDirection.W),
            new SimpleEntry<>(CardinalDirection.W, CardinalDirection.S),
            new SimpleEntry<>(CardinalDirection.S, CardinalDirection.E),
            new SimpleEntry<>(CardinalDirection.E, CardinalDirection.N)
    ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)));


    Point getStartPoint();

    Point getCurrentPoint();

    Point getPreviousPoint();

    CardinalDirection getOrientation();

    void rotateRight();

    void rotateLeft();

    void moveStepsForward(int steps);

    void moveOneStepForward();

    void addInstruction(RobotInstruction instruction);

    RobotInstruction popInstruction();

    Surface getSurface();

    void setSurface(Surface surface);

    boolean inBounds();

}
