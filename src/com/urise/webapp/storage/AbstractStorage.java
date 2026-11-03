package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract void deleteResume(Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void saveResume(Resume resume);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract Object findSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract boolean isNotExist(Object searchKey);

    @Override
    public final void delete(String uuid) {
        var searchKey = findExistedSearchKey(uuid);
        deleteResume(searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        var searchKey = findExistedSearchKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public final void save(Resume resume) {
        findNotExistedSearchKey(resume);
        saveResume(resume);
    }

    @Override
    public final void update(Resume resume) {
        var searchKey = findExistedSearchKey(resume.getUuid());
        updateResume(resume, searchKey);
    }

    private boolean findNotExistedSearchKey(Resume resume) {
        var searchKey = findSearchKey(resume.getUuid());
        if (isExist(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        }
        return true;
    }

    private Object findExistedSearchKey(String uuid) {
        var searchKey = findSearchKey(uuid);
        if (isNotExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
