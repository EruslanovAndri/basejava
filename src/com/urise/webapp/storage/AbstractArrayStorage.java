package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.NoSuchElementException;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    final public void delete(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (checkResumeIndex(resumeIndex)) {
            throw new NoSuchElementException("Резюме с номером ( " + uuid + " ) невозможно удалить его " +
                    "нет в хранилище.");
        }
        deleteByIndex(resumeIndex);
    }

    @Override
    final public Resume get(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (checkResumeIndex(resumeIndex)) {
            throw new NoSuchElementException("Резюме с номером ( " + uuid + " ) нет в хранилище.");
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
    public int size() {
        return size;
    }

    @Override
    final public void update(Resume resume) {
        int resumeIndex = findResumeIndex(resume.getUuid());
        if (checkResumeIndex(resumeIndex)) {
            throw new NoSuchElementException("Резюме с номером ( " + resume.getUuid() +
                    " ) нет в хранилище и его не возможно обновить.");
        }
        storage[resumeIndex] = resume;
    }

    protected abstract int findResumeIndex(String uuid);

    protected abstract boolean checkResumeIndex(int resumeIndex);

    protected abstract void deleteByIndex(int resumeIndex);
}
