package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void saveResume(Resume resume) {
        checkStorageLimit(resume);
        addNewResume(resume);
        increaseSize();
    }

    protected void clearSlot() {
        storage[--size] = null;
    }

    @Override
    protected Resume getAt(int index) {
        return storage[index];
    }

    @Override
    protected Resume[] doGetAll() {
        return Arrays.copyOf(storage, size);
    }

    protected void checkStorageLimit(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Нет свободного места.", resume.getUuid());
        }
    }

    @Override
    protected void checkExistResume(Resume resume) {
        int resumeIndex = findResumeIndex(resume.getUuid());
        if (resumeIndex >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    protected void increaseSize() {
        size++;
    }

    @Override
    protected int computeSize() {
        return size;
    }

    @Override
    protected void doUpdate(Resume resume, int resumeIndex) {
        storage[resumeIndex] = resume;
    }

    protected abstract void addNewResume(Resume resume);
}

