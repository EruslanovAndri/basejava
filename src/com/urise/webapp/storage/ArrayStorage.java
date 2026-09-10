package com.urise.webapp.storage;

import com.urise.webapp.exception.DuplicateException;
import com.urise.webapp.exception.NoFreeSpaceException;
import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new NoFreeSpaceException("Резюме с номером ( " +
                    resume.getUuid() + ") не может быть добавлено, нет свободного места.");
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
    protected int findResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean checkResumeIndex(int resumeIndex) {
        return resumeIndex == -1;
    }
}
