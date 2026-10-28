package test.com.urise.webapp.storage.junit6;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.jupiter.api.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(final Storage storage) {
        super(storage);
    }

    @Test
    public void checkOverflowStorageExceptionTest() {
        try {
            for (int i = 0; i < STORAGE_LIMIT - 3; i++) {
                storage.save(new Resume(Integer.toString(i)));
            }
        } catch (Exception e) {
            fail("Переполнение произошло раньше времени.");
        }
        assertThrows(StorageException.class, () -> storage.save(new Resume("UUID_4")));
    }
}
