package test.com.urise.webapp.storage.junit4;

import com.urise.webapp.storage.MapResumeStorage;
import test.com.urise.webapp.storage.junit6.AbstractStorageTest;

public class MapResumeStorageTest extends AbstractStorageTest {
    public MapResumeStorageTest() {
        super(new MapResumeStorage());
    }
}
