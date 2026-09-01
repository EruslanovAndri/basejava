package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {
    private int size;
    public Resume[] storage = new Resume[10000];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int resumeIndex = findResumeIndex(resume.getUuid());
        if (resumeIndex != -1) {
            storage[resumeIndex] = resume;
        } else {
            throw new NoSuchElementException("Резюме с номером ( " + resume.getUuid() +
                    " ) нет в хранилище и его не возможно обновить.");
        }
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            throw new ArrayIndexOutOfBoundsException("В хранилище нет свободного места, резюме с номером (" +
                    resume.getUuid() + ") не может быть добавлено.");
        }

        boolean isDuplicate = false;
        int resumeIndex = findResumeIndex(resume.getUuid());
        if (size != 0 && resumeIndex != -1) {
            isDuplicate = true;
        }

        if (!isDuplicate) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Резюме с номером ( " + resume.getUuid() + " ) уже существует в хранилище.");
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (resumeIndex == -1) {
            throw new NoSuchElementException("Резюме с номером ( " + uuid + " ) нет в хранилище.");
        }
        return storage[resumeIndex];
    }

    public void delete(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (resumeIndex == -1) {
            throw new NoSuchElementException("Резюме с номером ( " + uuid + " ) невозможно удалить его " +
                    "нет в хранилище.");
        }
        System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - resumeIndex - 1);
        storage[--size] = null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
