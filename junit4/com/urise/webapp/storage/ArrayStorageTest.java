package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    private static Storage storage;
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
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume("UUID_1"), storage.get("UUID_1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("UUID_4");
    }

    @Test
    public void delete() {
        System.out.println("Delete method:");
        System.out.println("Before: " + Arrays.toString(storage.getAll()));
        storage.delete("UUID_1");
        System.out.println("After: " + Arrays.toString(storage.getAll()));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.get("UUID_4");
    }

    @Test
    public void update() {
        storage.update(new Resume("UUID_1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("UUID_4"));
    }

    @Test
    public void save() {
        storage.save(new Resume("UUID_4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() {
        storage.save(new Resume("UUID_1"));
    }

    @Test
    public void getAll() {
        storage.getAll();
    }

    @Test
    public void clear() {
        System.out.println("Clear method:");
        System.out.println("Before: " + Arrays.toString(storage.getAll()));
        storage.clear();
        System.out.println("After: " + Arrays.toString(storage.getAll()));
    }

    @Test(expected = StorageException.class)
    public void checkOverflowStorageException() {
        for (int i = 0; i < STORAGE_LIMIT; i++) {
            storage.save(new Resume(Integer.toString(i)));
        }
    }
}