package test.com.urise.webapp.storage.junit4;

import static org.junit.jupiter.api.Assertions.fail;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void checkOverflowStorageException() {
        try {
            for (int i = 0; i < STORAGE_LIMIT - 3; i++) {
                storage.save(new Resume(Integer.toString(i)));
            }
        } catch (Exception e) {
            fail("Переполнение произошло раньше времени.");
        }
        storage.save(new Resume("UUID_4"));
    }
}