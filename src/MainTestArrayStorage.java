import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation.
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "z");
        Resume r2 = new Resume("uuid2", "x");
        Resume r3 = new Resume("uuid3", "a");
        System.out.println("Resume1: " + r1.getUuid() + "\nName:" + r1.getFullName());

//        ARRAY_STORAGE.save(r1);
//        try {
//            ARRAY_STORAGE.save(r1);
//        } catch (ExistStorageException e) {
//            System.out.println(e.getMessage());
//        }
//
//        ARRAY_STORAGE.save(r2);
//        ARRAY_STORAGE.save(r3);
//
//        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
//        System.out.println("Size: " + ARRAY_STORAGE.size());
//        try {
//            System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
//        } catch (NotExistStorageException e) {
//            System.out.println(e.getMessage());
//        }
//
//        printAllSorted();
//        ARRAY_STORAGE.delete(r1.getUuid());
//        printAllSorted();
//        ARRAY_STORAGE.clear();
//        printAllSorted();
//
//        System.out.println("Size: " + ARRAY_STORAGE.size());
//    }
//
//    static void printAll() {
//        System.out.println("\nGet All");
//        for (Resume r : ARRAY_STORAGE.getAll()) {
//            System.out.println(r);
//        }
//    }
//
//    static void printAllSorted() {
//        System.out.println("\nGet All Sorted");
//        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
//            System.out.println(r);
//        }
//    }
    }
}
