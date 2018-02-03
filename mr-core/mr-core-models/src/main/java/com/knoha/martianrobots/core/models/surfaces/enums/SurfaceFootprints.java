package com.knoha.martianrobots.core.models.surfaces.enums;

public enum SurfaceFootprints {

    D("Discovered"),
    U("Unknown"),
    E("Edge point");

    private String description;

    SurfaceFootprints(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
