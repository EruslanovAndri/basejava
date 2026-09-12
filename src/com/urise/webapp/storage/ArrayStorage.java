package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage extends AbstractArrayStorage {
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

    @Override
    protected void deleteByIndex(int resumeIndex) {
        storage[resumeIndex] = storage[resumeIndex + 1];
    }

    @Override
    protected void saveResume(Resume resume, int resumeIndex) {
        storage[size] = resume;
    }
}
