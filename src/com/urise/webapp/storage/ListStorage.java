package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return List.copyOf(storage).toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        storage.set((Integer) searchKey, resume);
    }

    @Override
    protected Object findSearchKey(String uuid) {
        return IntStream.range(0, storage.size())
                .filter((i -> uuid.equals(storage.get(i).getUuid())))
                .findFirst()
                .orElse(-1);
    }
}
