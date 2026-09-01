package com.urise.webapp.model;

/**
 * Initial resume class.
 */
public class Resume {
    // Unique identifier
    private String uuid;

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
