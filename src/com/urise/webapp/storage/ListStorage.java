package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.model.Resume;
import java.util.List;
import java.util.stream.IntStream;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected int findResumeIndex(String uuid) {
        int index = IntStream.range(0, storage.size())
                .filter((i -> uuid.equals(storage.get(i).getUuid())))
                .findFirst()
                .orElse(-1);
        return index;
    }

    @Override
    protected Resume getAt(int index) {
        return storage.get(index);
    }

    @Override
    protected Resume[] doGetAll() {
        return List.copyOf(storage).toArray(Resume[]::new);
    }

    @Override
    protected void checkExistResume(Resume resume) {
        if (storage.contains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    protected void saveResume(Resume resume, int resumeIndex) {
        storage.add(resume);
    }

    @Override
    protected int computeSize() {
        return storage.size();
    }

    @Override
    protected void doUpdate(Resume resume, int resumeIndex) {
        storage.set(resumeIndex, resume);
    }

    @Override
    protected void deleteByIndex(int resumeIndex) {
        storage.remove(resumeIndex);
    }
}
