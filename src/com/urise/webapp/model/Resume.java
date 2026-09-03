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
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;

        final Resume resume = (Resume) o;
        return this.uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return this.uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }
}
