package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
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

    @Override
    protected void deleteByIndex(int resumeIndex) {
        System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - resumeIndex - 1);
    }

    @Override
    protected void saveResume(Resume resume, int resumeIndex) {
        int resumePosition = -resumeIndex - 1;
        System.arraycopy(storage, resumePosition, storage, resumePosition + 1, size - resumePosition);
        storage[resumePosition] = resume;
    }
}
