package com.knoha.martianrobots.core.services;

public final class ServiceDefinitions {

    private ServiceDefinitions() {
        throw new UnsupportedOperationException();
    }

    public static final class ServiceNames {

        public static final String ROBOT_CONTROL = "com.knoha.martianrobots.core.services.robots.RobotControlService";

        private ServiceNames() {
            throw new UnsupportedOperationException();
        }
    }
}
