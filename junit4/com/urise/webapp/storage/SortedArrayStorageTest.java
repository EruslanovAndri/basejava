package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SortedArrayStorageTest extends AbstractArrayStorage {
    private static Storage sortedStorage;
    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";

    @Before
    public void setUp() {
        sortedStorage = new SortedArrayStorage();
        sortedStorage.clear();
        sortedStorage.save(new Resume(UUID_1));
        sortedStorage.save(new Resume(UUID_2));
        sortedStorage.save(new Resume(UUID_3));
    }

    @Test
    public void delete() {
        System.out.println("Delete method:");
        System.out.println("Before: " + Arrays.toString(sortedStorage.getAll()));
        sortedStorage.delete("UUID_1");
        System.out.println("After: " + Arrays.toString(sortedStorage.getAll()));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        sortedStorage.get("UUID_4");
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume("UUID_1"), sortedStorage.get("UUID_1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        Assert.assertEquals(new Resume("UUID_4"), sortedStorage.get("UUID_4"));
    }

    @Test
    public void clear() {
        System.out.println("Clear method:");
        System.out.println("Before: " + Arrays.toString(sortedStorage.getAll()));
        sortedStorage.clear();
        System.out.println("After: " + Arrays.toString(sortedStorage.getAll()));
    }

    @Test
    public void save() {
        sortedStorage.save(new Resume("UUID_4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() {
        sortedStorage.save(new Resume("UUID_1"));
    }

    @Test
    public void update() {
        sortedStorage.update(new Resume("UUID_1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        sortedStorage.update(new Resume("UUID_4"));
    }

    @Test(expected = StorageException.class)
    public void checkOverflowStorageException() {
        for (int i = 0; i < STORAGE_LIMIT; i++) {
            sortedStorage.save(new Resume(Integer.toString(i)));
        }
    }

    @Override
    protected int findResumeIndex(String uuid) {
        return 0;
    }

    @Override
    protected boolean checkResumeIndex(int resumeIndex) {
        return false;
    }

    @Override
    protected void deleteByIndex(int resumeIndex) {

    }

    @Override
    protected void saveResume(Resume resume, int resumeIndex) {
    }
}