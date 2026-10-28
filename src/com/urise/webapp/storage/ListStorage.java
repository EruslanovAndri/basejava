package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected int findResume(String uuid) {
        return IntStream.range(0, storage.size())
                .filter((i -> uuid.equals(storage.get(i).getUuid())))
                .findFirst()
                .orElse(-1);
    }

    @Override
    protected Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void updateResume(Resume resume, int resumeIndex) {
        storage.set(resumeIndex, resume);
    }

    @Override
    protected void deleteResume(int resumeIndex) {
        storage.remove(resumeIndex);
    }

    @Override
    public Resume[] getAll() {
        return List.copyOf(storage).toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
