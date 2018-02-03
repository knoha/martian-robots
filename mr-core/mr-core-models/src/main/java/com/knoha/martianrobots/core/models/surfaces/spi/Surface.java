package com.knoha.martianrobots.core.models.surfaces.spi;

import java.awt.*;
import java.io.Serializable;
import java.util.Set;

public interface Surface extends Serializable {

    String[][] getArea();

    int getAreaX();

    int getAreaY();

    Set<Point> getEdgePoints();

    void printArea();

}
