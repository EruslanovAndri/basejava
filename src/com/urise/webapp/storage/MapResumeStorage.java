package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    protected final Map<Resume, Resume> storage = new HashMap<>();

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage.replace((Resume) searchKey, resume);
    }

    @Override
    protected Object findSearchKey(String uuid) {
        return new Resume(uuid, null);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected List<Resume> doGetAllSorted(Comparator<Resume> comparator) {
        return storage.values().stream().sorted(comparator).toList();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
