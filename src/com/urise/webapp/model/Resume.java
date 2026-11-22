package com.urise.webapp.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class.
 */
public class Resume {
    // Unique identifier
    private final String uuid;
    private final String fullName;

    public Resume() {
        this(UUID.randomUUID().toString(), null);
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "Uuid не может быть null");
        Objects.requireNonNull(fullName, "Full name не может быть null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getFullName() {
        return this.fullName;
    }

    @Override
    public boolean equals(final Object o) {
        if (null == o || this.getClass() != o.getClass()) return false;

        final Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "UUID: " + uuid + " " + "Name: " + fullName;
    }
}
