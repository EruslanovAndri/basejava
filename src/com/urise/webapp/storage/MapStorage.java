package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        String uuid = resume.getUuid();
        storage.put(uuid, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage.replace((String) searchKey, resume);
    }

    @Override
    protected Object findSearchKey(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey);
    }
}
