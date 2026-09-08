package com.urise.webapp.storage;

import com.urise.webapp.exception.DuplicateException;
import com.urise.webapp.exception.NoFreeSpaceException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume resume) {
        int resumeIndex = findResumeIndex(resume.getUuid());
        System.out.println("Update index " + resumeIndex);
        if (resumeIndex < 0) {
            throw new NoSuchElementException("Резюме с номером ( " + resume.getUuid() +
                    " ) нет в хранилище и его не возможно обновить.");
        }
        System.out.println("Resume was updated in Sorted array storage");
        storage[resumeIndex] = resume;
    }

    @Override
    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new NoFreeSpaceException("В хранилище нет свободного места, резюме с номером (" +
                    resume.getUuid() + ") не может быть добавлено.");
        }

        int resumeIndex = findResumeIndex(resume.getUuid());
        System.out.println("Resume index " + resumeIndex);
        if (resumeIndex >= 0) {
            throw new DuplicateException("Резюме с номером ( " + resume.getUuid() +
                    " ) уже существует в хранилище.");
        }
        int resumePosition = -resumeIndex - 1;
        System.arraycopy(storage, resumePosition, storage, resumePosition + 1, size - resumePosition);
        storage[resumePosition] = resume;
        size++;
    }

    public Resume get(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (resumeIndex < 0) {
            throw new NoSuchElementException("Резюме с номером ( " + uuid + " ) нет в хранилище.");
        }
        return storage[resumeIndex];
    }

    @Override
    protected int findResumeIndex(String uuid) {
        Resume searchResume = new Resume();
        searchResume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchResume);
    }
}
