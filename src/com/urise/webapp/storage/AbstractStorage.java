package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public final void delete(String uuid) {
        int resumePosition = findResume(uuid);
        validateExistResume(resumePosition, uuid);
        deleteResume(resumePosition);
    }

    @Override
    public final Resume get(String uuid) {
        int resumePosition = findResume(uuid);
        validateExistResume(resumePosition, uuid);
        return getResume(resumePosition);
    }

    @Override
    public final void save(Resume resume) {
        int resumePosition = findResume(resume.getUuid());
        checkExistResume(resume, resumePosition);
        saveResume(resume);
    }

    @Override
    public final void update(Resume resume) {
        int resumePosition = findResume(resume.getUuid());
        validateExistResume(resumePosition, resume.getUuid());
        updateResume(resume, resumePosition);
    }

    protected abstract void deleteResume(int resumePosition);

    protected abstract Resume getResume(int position);

    protected abstract void saveResume(Resume resume);

    protected abstract void updateResume(Resume resume, int resumePosition);

    protected abstract int findResume(String uuid);

    private void checkExistResume(Resume resume, int resumePosition) {
        if (resumePosition >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    private void validateExistResume(int resumePosition, String uuid) {
        if (resumePosition < 0) {
            throw new NotExistStorageException(uuid);
        }
    }
}
