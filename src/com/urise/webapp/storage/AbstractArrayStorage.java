package com.urise.webapp.storage;

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
    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    protected void saveResume(Resume resume) {
        checkStorageLimit(resume);
        addNewResume(resume);
        size++;
    }

    protected void checkStorageLimit(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Нет свободного места.", resume.getUuid());
        }
    }

    @Override
    protected void deleteResume(int resumeIndex) {
        System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - resumeIndex - 1);
        storage[--size] = null;
    }

    @Override
    protected Resume getResume(int index) {
        return storage[index];
    }

    @Override
    protected void updateResume(Resume resume, int resumeIndex) {
        storage[resumeIndex] = resume;
    }

    protected abstract void addNewResume(Resume resume);

    protected abstract void deleteResumeByIndex(int resumeIndex);
}

