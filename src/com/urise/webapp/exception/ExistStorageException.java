package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(final String uuid) {
        super("Резюме " + uuid + " уже есть в хранилище.",uuid);
    }
}
