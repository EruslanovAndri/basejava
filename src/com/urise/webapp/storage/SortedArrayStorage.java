package com.urise.webapp.storage;

import com.urise.webapp.exception.DuplicateException;
import com.urise.webapp.exception.NoFreeSpaceException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new NoFreeSpaceException(DEFAULT_EXCEPTION_MESSAGE +
                    resume.getUuid() + ") не может быть добавлено, нет свободного места.");
        }

        int resumeIndex = findResumeIndex(resume.getUuid());
        if (resumeIndex >= 0) {
            throw new DuplicateException(DEFAULT_EXCEPTION_MESSAGE + resume.getUuid() +
                    " ) уже существует в хранилище.");
        }
        int resumePosition = -resumeIndex - 1;
        System.arraycopy(storage, resumePosition, storage, resumePosition + 1, size - resumePosition);
        storage[resumePosition] = resume;
        size++;
    }

    @Override
    protected int findResumeIndex(String uuid) {
        Resume searchResume = new Resume();
        searchResume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchResume);
    }

    @Override
    protected boolean checkResumeIndex(int resumeIndex) {
        return resumeIndex < 0;
    }
}
