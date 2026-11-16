package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    private static final Comparator<Resume> COMPARATOR =
            Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract Object findSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract List<Resume> doGetAllSorted(Comparator<Resume> comparator);

    @Override
    public final void delete(String uuid) {
        var searchKey = findExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        var searchKey = findExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        final Comparator<Resume> compareByName = (o1, o2) -> {
            int result = o1.getFullName().compareTo(o2.getFullName());
            if (result != 0) {
                return result;
            } else {
                return o1.getUuid().compareTo(o2.getUuid());
            }
        };
        return doGetAllSorted(COMPARATOR);
    }

    @Override
    public final void save(Resume resume) {
        var searchKey = findNotExistedSearchKey(resume);
        doSave(resume, searchKey);
    }

    @Override
    public final void update(Resume resume) {
        var searchKey = findExistedSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    private Object findNotExistedSearchKey(Resume resume) {
        var searchKey = findSearchKey(resume.getUuid());
        if (isExist(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        }
        return searchKey;
    }

    private Object findExistedSearchKey(String uuid) {
        var searchKey = findSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
