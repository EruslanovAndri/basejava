package test.com.urise.webapp.storage.junit4;

import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.Storage;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    @Override
    protected Storage createStorage() {
        return new ArrayStorage();
    }
}