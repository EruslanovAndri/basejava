package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    public final void delete(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (checkResumeIndex(resumeIndex)) {
            throw  new NotExistStorageException(uuid);
        }
        deleteByIndex(resumeIndex);
        storage[--size] = null;
    }

    @Override
    public final Resume get(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (checkResumeIndex(resumeIndex)) {
            throw new NotExistStorageException(uuid);
        }
        return storage[resumeIndex];
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public final void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Нет свободного места.", resume.getUuid());
        }
        int resumeIndex = findResumeIndex(resume.getUuid());
        if (resumeIndex >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(resume, resumeIndex);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public final void update(Resume resume) {
        int resumeIndex = findResumeIndex(resume.getUuid());
        if (checkResumeIndex(resumeIndex)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        storage[resumeIndex] = resume;
    }

    protected abstract int findResumeIndex(String uuid);

    protected abstract boolean checkResumeIndex(int resumeIndex);

    protected abstract void deleteByIndex(int resumeIndex);

    protected abstract void saveResume(Resume resume, int resumeIndex);
}

