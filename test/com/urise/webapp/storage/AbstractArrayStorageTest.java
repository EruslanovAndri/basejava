package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    public Storage storage;
    public static final int STORAGE_LIMIT = 10_000;
    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";

    @Before
    public void setUp() {
        storage = new ArrayStorage();
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getNotExist() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void save() {
    }

    @Test
    public void size() {
    }

    @Test
    public void update() {
    }
}