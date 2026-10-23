package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public final void clear() {
        doClear();
    }

    @Override
    public final void delete(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        validateExistResume(resumeIndex, uuid);
        deleteByIndex(resumeIndex);
        clearSlot();
    }

    @Override
    public final Resume get(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        validateExistResume(resumeIndex, uuid);
        return getAt(resumeIndex);
    }

    @Override
    public final Resume[] getAll() {
        return doGetAll();
    }

    @Override
    public final void save(Resume resume) {
        checkStorageLimit(resume);
        checkExistResume(resume);
        saveResume(resume, findResumeIndex(resume.getUuid()));
        increaseSize();
    }

    @Override
    public final int size() {
        return computeSize();
    }

    @Override
    public final void update(Resume resume) {
        int resumeIndex = findResumeIndex(resume.getUuid());
        validateExistResume(resumeIndex, resume.getUuid());
        doUpdate(resume, resumeIndex);
    }

    protected abstract void deleteByIndex(int resumeIndex);

    protected abstract int findResumeIndex(String uuid);

    protected abstract Resume getAt(int index);

    protected abstract Resume[] doGetAll();

    protected abstract void checkExistResume(Resume resume);

    protected abstract void saveResume(Resume resume);

    protected abstract int computeSize();

    protected abstract void doUpdate(Resume resume, int resumeIndex);

    private void validateExistResume(int resumeIndex, String uuid) {
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
    }
}
