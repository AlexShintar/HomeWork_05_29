package homework_05_29;

import homework_05_29.exception.NullItemException;
import homework_05_29.model.IntegerList;
import homework_05_29.model.IntegerListImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class IntegerListImplTest {
    private final IntegerList test = new IntegerListImpl(10);

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 20; i++) {
            test.add(i);
        }
    }

    @Test
    void add() {
        int testSize = test.size();
        test.add(123);
        Assertions.assertTrue(test.contains(123));
        Assertions.assertEquals(test.size(), testSize + 1);

    }

    @Test
    void testAdd() {
        Integer temp = test.get(1);
        int testSize = test.size();
        test.add(1, 123);
        Assertions.assertEquals(123, test.get(1));
        Assertions.assertEquals(test.get(2), temp);
        Assertions.assertEquals(test.size(), testSize + 1);
    }

    @Test
    void set() {
        int testSize = test.size();
        test.set(1, 123);
        Assertions.assertEquals(123, test.get(1));
        Assertions.assertEquals(test.size(), testSize);
    }

    @Test
    void remove() {
        int testSize = test.size();
        Integer temp = test.get(2);
        test.remove((Integer) 1);
        Assertions.assertFalse(test.contains(1));
        Assertions.assertEquals(test.size(), testSize - 1);
        Assertions.assertEquals(test.get(1), temp);
        Assertions.assertThrows(NullItemException.class,
                () -> test.remove((Integer) 1));
    }

    @Test
    void testRemove() {
        int testSize = test.size();
        Integer temp = test.get(2);
        test.remove(1);
        Assertions.assertFalse(test.contains(1));
        Assertions.assertEquals(test.size(), testSize - 1);
        Assertions.assertEquals(test.get(1), temp);
    }

    @Test
    void contains() {
        test.add(2,999);
        test.add(7,456);
        test.add(11,888);
        Assertions.assertFalse(test.contains(333));
        Assertions.assertTrue(test.contains(456));
    }

    @Test
    void indexOf() {
        Assertions.assertEquals(test.indexOf(1), 1);
        Assertions.assertEquals(test.indexOf(123), -1);
    }

    @Test
    void lastIndexOf() {
        test.add(1);
        Assertions.assertEquals(test.lastIndexOf(1), 20);
        Assertions.assertEquals(test.indexOf(123), -1);
    }

    @Test
    void get() {
        Assertions.assertEquals(test.get(1), 1);
    }

    @Test
    void testEquals() {
        test.clear();
        IntegerList newList = new IntegerListImpl(1);
        test.add(1);
        newList.add(1);
        Assertions.assertTrue(test.equals(newList));
    }

    @Test
    void size() {
        Assertions.assertEquals(test.size(), 20);
        test.add(123);
        Assertions.assertEquals(test.size(), 21);
    }

    @Test
    void isEmpty() {
        Assertions.assertFalse(test.isEmpty());
        test.clear();
        Assertions.assertTrue(test.isEmpty());
    }

    @Test
    void clear() {
        test.clear();
        Assertions.assertTrue(test.isEmpty());
        Assertions.assertEquals(test.size(), 0);
    }

    @Test
    void toArray() {
        test.clear();
        test.add(1);
        test.add(2);
        Assertions.assertEquals(Arrays.toString(test.toArray()), "[1, 2]");
    }
}