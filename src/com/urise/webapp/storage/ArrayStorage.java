package com.urise.webapp.storage;

import com.urise.webapp.exception.DuplicateException;
import com.urise.webapp.exception.NoFreeSpaceException;
import com.urise.webapp.model.Resume;
import java.util.NoSuchElementException;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void delete(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (resumeIndex == -1) {
            throw new NoSuchElementException("Резюме с номером ( " + uuid + " ) невозможно удалить его " +
                    "нет в хранилище.");
        }
        System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - resumeIndex - 1);
        storage[--size] = null;
    }

    @Override
    public Resume get(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (resumeIndex == -1) {
            throw new NoSuchElementException("Резюме с номером ( " + uuid + " ) нет в хранилище.");
        }
        return storage[resumeIndex];
    }

    @Override
    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new NoFreeSpaceException("В хранилище нет свободного места, резюме с номером (" +
                    resume.getUuid() + ") не может быть добавлено.");
        }
        int resumeIndex = findResumeIndex(resume.getUuid());
        if (resumeIndex != -1) {
            throw new DuplicateException("Резюме с номером ( " + resume.getUuid() +
                    " ) уже существует в хранилище.");
        }
        storage[size] = resume;
        size++;
    }

    @Override
    public void update(Resume resume) {
        int resumeIndex = findResumeIndex(resume.getUuid());
        if (resumeIndex == -1) {
            throw new NoSuchElementException("Резюме с номером ( " + resume.getUuid() +
                    " ) нет в хранилище и его не возможно обновить.");
        }
        storage[resumeIndex] = resume;
    }

    @Override
    protected int findResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
