package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(final String uuid) {
        super("Резюме " + uuid + " нет в хранилище.",uuid);
    }
}
