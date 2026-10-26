package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int findResumeIndex(String uuid) {
        Resume searchResume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchResume);
    }

    @Override
    protected void addNewResume(Resume resume) {
        int resumePosition = findResumeIndex(resume.getUuid());
        resumePosition = -resumePosition - 1;
        System.arraycopy(storage, resumePosition, storage, resumePosition + 1, size - resumePosition);
        storage[resumePosition] = resume;
    }
}
