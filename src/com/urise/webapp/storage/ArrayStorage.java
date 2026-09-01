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

    public void save(Resume resume) {
        if (size == storage.length) {
            throw new ArrayIndexOutOfBoundsException("В хранилище нет свободного места, резюме с номером (" +
                    resume.getUuid() + ") не может быть добавлено.");
        }

        boolean isDuplicate = false;
        for (int i = 0; i < size; i++) {
            if (size != 0 && storage[i].getUuid().equals(resume.getUuid())) {
                isDuplicate = true;
            }
        }
        if (!isDuplicate) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Резюме с номером ( " + resume.getUuid() + " ) уже существует в хранилище.");
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = getIndexByUuid(uuid);
        if (!isPresentResume(uuid) && resumeIndex == -1) {
            throw new NoSuchElementException("Резюме с номером ( " + uuid + " ) нет в хранилище.");
        }
        return storage[resumeIndex];
    }

    public void delete(String uuid) {
        int resumeIndex = getIndexByUuid(uuid);
        if (!isPresentResume(uuid) && resumeIndex == -1) {
            throw new NoSuchElementException("Резюме с номером ( " + uuid + " ) невозможно удалить его " +
                    "нет в хранилище.");
        } else {
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - resumeIndex - 1);
            storage[--size] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndexByUuid(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                index = i;
                break;
            }
        }
        return index;
    }

    private boolean isPresentResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return true;
            }
        }
        return false;
    }
}
