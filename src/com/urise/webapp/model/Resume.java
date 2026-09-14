package com.urise.webapp.model;

import java.util.UUID;

/**
 * Initial resume class.
 */
public class Resume implements Comparable<Resume> {
    // Unique identifier
    private final String uuid;

    public Resume() {
        this(UUID.randomUUID().toString());

    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }


    public String getUuid() {
        return this.uuid;
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

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
