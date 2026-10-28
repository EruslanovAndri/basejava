package test.com.urise.webapp.storage.junit6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapStorage;
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

    @Ignore
    @Test
    public void checkOverflowStorageExceptionTest() {
    }

    @Override
    @Test
    public void getAllTest() {
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        List<Resume> all = Arrays.asList(storage.getAll());
        assertEquals(3, storage.size());
        assertTrue(all.contains(RESUME_1));
        assertTrue(all.contains(RESUME_2));
        assertTrue(all.contains(RESUME_3));
        assertFalse(all.contains(RESUME_4));
    }
}
