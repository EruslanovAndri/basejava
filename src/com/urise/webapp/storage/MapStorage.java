package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new HashMap<>();
    protected final List<String> keysOrder = new ArrayList<>();

    @Override
    protected void deleteResume(int resumePosition) {
        String key = getKey(resumePosition);
        storage.remove(key);
        keysOrder.remove(key);
    }

    @Override
    protected Resume getResume(int resumePosition) {
        String key = getKey(resumePosition);
        return storage.get(key);
    }

    @Override
    protected void saveResume(Resume resume) {
        String uuid = resume.getUuid();
        storage.put(uuid, resume);
        if (!keysOrder.contains(uuid)) {
            keysOrder.add(uuid);
        }
    }

    @Override
    protected void updateResume(Resume resume, int resumePosition) {
        String key = getKey(resumePosition);
        storage.replace(key, resume);
    }

    @Override
    protected int findResume(String uuid) {
        return keysOrder.indexOf(uuid);
    }

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

    private String getKey(int resumePosition) {
        return keysOrder.get(resumePosition);
    }
}
