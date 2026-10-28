package test.com.urise.webapp.storage.junit6;

import com.urise.webapp.storage.ListStorage;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

public class ListStorageTest extends AbstractStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }
    @Ignore
    @Test
    public void checkOverflowStorageExceptionTest() {
    }
}