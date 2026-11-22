package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    protected final Map<Resume, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume[] doGetAllSorted() {
        return storage.values().stream().toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doDelete(Resume searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume doGet(Resume searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doSave(Resume resume, Resume searchKey) {
        storage.put(resume, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Resume searchKey) {
        storage.replace((Resume) searchKey, resume);
    }

    @Override
    protected Resume findSearchKey(String uuid) {
        return new Resume(uuid, "dummy");
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return storage.containsKey(searchKey);
    }
}
