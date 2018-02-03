package com.knoha.martianrobots.core.models.surfaces.base;

import com.knoha.martianrobots.core.models.ModelDefinitions;
import com.knoha.martianrobots.core.models.surfaces.enums.SurfaceFootprints;
import com.knoha.martianrobots.core.models.surfaces.spi.Surface;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component(ModelDefinitions.ModelNames.MARTIAN_SURFACE)
public class MartianSurfaceImpl implements Surface {

    private final String[][] area;

    private final int areaX;
    private final int areaY;

    private final Set<Point> edgePoints = new HashSet<>();

    public MartianSurfaceImpl(final int x, final int y) {
        // + 1 to each, because start coordinate is 0:0
        this.areaX = x + 1;
        this.areaY = y + 1;

        this.area = new String[this.areaY][this.areaX];

        // Init surface with undiscovered marks
        for (int i = 0; i < this.areaY; i++) {
            for (int j = 0; j < this.areaX; j++) {
                this.area[i][j] = SurfaceFootprints.U.name();
            }
        }
    }

    @Override
    public String[][] getArea() {
        return this.area;
    }

    @Override
    public int getAreaX() {
        return areaX;
    }

    @Override
    public int getAreaY() {
        return areaY;
    }

    @Override
    public Set<Point> getEdgePoints() {
        return edgePoints;
    }

    @Override
    public void printArea() {
        for (int i = this.areaY - 1; i >= 0; i--) {
            for (int j = 0; j < this.areaX; j++) {
                System.out.print(this.area[i][j] + " ");
            }

            System.out.println();
        }
    }

}
