package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public final void delete(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        validateExistResume(resumeIndex, uuid);
        deleteResume(resumeIndex);
    }

    @Override
    public final Resume get(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        validateExistResume(resumeIndex, uuid);
        return getResume(resumeIndex);
    }

    @Override
    public final void save(Resume resume) {
        int resumeIndex = findResumeIndex(resume.getUuid());
        checkExistResume(resume, resumeIndex);
        saveResume(resume);
    }

    @Override
    public final void update(Resume resume) {
        int resumeIndex = findResumeIndex(resume.getUuid());
        validateExistResume(resumeIndex, resume.getUuid());
        updateResume(resume, resumeIndex);
    }

    protected abstract void deleteResume(int resumeIndex);

    protected abstract Resume getResume(int index);

    protected abstract void saveResume(Resume resume);

    protected abstract void updateResume(Resume resume, int resumeIndex);

    protected abstract int findResumeIndex(String uuid);

    private void checkExistResume(Resume resume, int resumeIndex) {
        if (resumeIndex >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    private void validateExistResume(int resumeIndex, String uuid) {
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
    }
}
