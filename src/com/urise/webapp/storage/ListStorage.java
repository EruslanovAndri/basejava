package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ListStorage extends AbstractStorage<Integer> {
    protected final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume[] doGetAllSorted() {
         return storage.stream().toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doDelete(Integer searchKey) {
        storage.remove(searchKey.intValue());
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        storage.add(resume);
    }

    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {
        storage.set(searchKey, resume);
    }

    @Override
    protected Integer findSearchKey(String uuid) {
        return IntStream.range(0, storage.size())
                .filter((i -> uuid.equals(storage.get(i).getUuid())))
                .findFirst()
                .orElse(-1);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return (Integer) searchKey >= 0;
    }
}
