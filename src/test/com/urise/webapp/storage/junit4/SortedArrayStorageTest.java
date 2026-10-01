package test.com.urise.webapp.storage.junit4;

import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;


public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    @Override
    protected Storage createStorage() {
        return new SortedArrayStorage();
    }
}