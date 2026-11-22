package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Comparator<Resume> COMPARATOR =
            Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract void doDelete(SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract Resume[] doGetAllSorted();

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract SK findSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    @Override
    public final void delete(String uuid) {
        SK searchKey = findExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        SK searchKey = findExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        Resume[] resumes = doGetAllSorted();
        List<Resume> list = new ArrayList<>(Arrays.asList(resumes));
        list.sort(COMPARATOR);
        return list;
    }

    @Override
    public final void save(Resume resume) {
        SK searchKey = findNotExistedSearchKey(resume);
        doSave(resume, searchKey);
    }

    @Override
    public final void update(Resume resume) {
        SK searchKey = findExistedSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    private SK findNotExistedSearchKey(Resume resume) {
        SK searchKey = findSearchKey(resume.getUuid());
        if (isExist(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        }
        return searchKey;
    }

    private SK findExistedSearchKey(String uuid) {
        SK searchKey = findSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
