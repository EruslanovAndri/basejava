package test.com.urise.webapp.storage.junit4;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;
    private static final int STORAGE_LIMIT = 10_000;
    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";
    private static final String UUID_4 = "UUID_4";

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
        storage.delete(UUID_4);
    }

    @Test
    public void get() {
        Resume resume = storage.get(UUID_1);
        assertEquals(UUID_1, resume.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void clear() {
        assertEquals(3, storage.size());
        storage.clear();
        assertTrue(storage == null || storage.size() == 0);
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertArrayEquals(storage.getAll(), resumes);
    }

    @Test
    public void save() {
        storage.save(new Resume(UUID_4));
        Resume resume = storage.get(UUID_4);
        assertEquals(storage.get(UUID_4), resume);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(storage.get(UUID_3));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1);
        storage.update(storage.get(UUID_1));
        assertSame(storage.get(UUID_1).getUuid(), resume.getUuid());
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