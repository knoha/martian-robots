package com.knoha.martianrobots.core.models.robots.enums;

public enum CardinalDirection {

    N("North"),
    E("East"),
    S("South"),
    W("West");

    private String directionName;

    CardinalDirection(final String directionName) {
        this.directionName = directionName;
    }

    public String getDirectionName() {
        return directionName;
    }
}
