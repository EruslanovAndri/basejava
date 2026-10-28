package test.com.urise.webapp.storage.junit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapStorage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MapStorageTest extends AbstractStorageTest {
    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";
    private static final String UUID_4 = "UUID_4";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Ignore
    @Test(expected = StorageException.class)
    public void checkOverflowStorageExceptionTest() {
    }

    @Override
    @Test
    public void getAllTest() {
        List<Resume> all = Arrays.asList(storage.getAll());
        assertEquals(3, storage.size());
        assertTrue(all.contains(RESUME_1));
        assertTrue(all.contains(RESUME_2));
        assertTrue(all.contains(RESUME_3));
        assertFalse(all.contains(RESUME_4));
    }
}
