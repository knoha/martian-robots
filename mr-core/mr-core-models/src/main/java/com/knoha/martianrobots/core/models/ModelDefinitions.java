package com.knoha.martianrobots.core.models;

public final class ModelDefinitions {

    private ModelDefinitions() {
        throw new UnsupportedOperationException();
    }

    public static final class ModelNames {

        public static final String MARTIAN_ROBOT = "com.knoha.martianrobots.core.models.robots.MartianRobot";
        public static final String MARTIAN_SURFACE = "com.knoha.martianrobots.core.models.surfaces.MartianSurface";

        private ModelNames() {
            throw new UnsupportedOperationException();
        }
    }

}
