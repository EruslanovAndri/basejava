package test.com.urise.webapp.storage.junit4;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.storage.ListStorage;
import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Ignore
    @Test(expected = StorageException.class)
    public void checkOverflowStorageExceptionTest() {
    }
}