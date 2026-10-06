package test.com.urise.webapp.storage.junit4;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;
    private static final int STORAGE_LIMIT = 10_000;
    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void delete() {
        assertEquals(3, storage.size());
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        assertEquals(3, storage.size());
        storage.delete("UUID_5");
        assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        assertEquals(storage.get(UUID_1), storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        assertEquals(storage.get("UUID_5"), storage.get("UUID_5"));
    }

    @Test
    public void clear() {
        assertEquals(3, storage.size());
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        assertTrue(3 == storage.getAll().length);
    }

    @Test
    public void save() {
        assertEquals(3, storage.size());
        storage.save(new Resume("UUID_4"));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        assertEquals(3, storage.size());
        storage.save(storage.get(UUID_3));
        assertEquals(4, storage.size());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void update() {
        Resume r = storage.get(UUID_1);
        storage.update(storage.get(UUID_1));
        assertEquals(storage.get(UUID_1).hashCode(), r.hashCode());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(storage.get("UUID_5"));
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
        System.out.println("Имя метода: checkOverflowStorageException: " + storage.getClass());
        System.out.println("Кол-во добавленных резюме = " + storage.size());
        storage.save(new Resume("UUID_10"));
    }
}