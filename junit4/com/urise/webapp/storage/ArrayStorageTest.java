package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class ArrayStorageTest extends AbstractArrayStorageTest {
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
        storage.delete("UUID_1");
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
        storage.clear();
    }

    @Test(expected = StorageException.class)
    public void checkOverflowStorageException() {
        for (int i = 0; i < STORAGE_LIMIT; i++) {
            storage.save(new Resume(Integer.toString(i)));
        }
    }
}