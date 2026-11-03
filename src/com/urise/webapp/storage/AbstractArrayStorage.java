package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract void addNewResume(Resume resume, int index);

    protected abstract void deleteResumeByIndex(int resumeIndex);

    @Override
    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        checkStorageLimit(resume);
        addNewResume(resume, (Integer) index);
        size++;
    }

    protected void checkStorageLimit(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Нет свободного места.", resume.getUuid());
        }
    }

    @Override
    protected void doDelete(Object searchKey) {
        deleteResumeByIndex((Integer) searchKey);
        storage[--size] = null;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage[(int) searchKey] = resume;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    protected boolean isNotExist(Object searchKey) {
        return (Integer) searchKey < 0;
    }
}

