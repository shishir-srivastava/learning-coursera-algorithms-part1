package week1.unionfind;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class QuickFindTest {

    private static QuickFind qf;

    @BeforeEach
    void initialize() {
        qf = new QuickFind(5);
    }

    @AfterEach
    void tearDown() {
        qf = null;
    }

    @Test
    void union() {
        assertArrayEquals(qf.getArr(), new int[]{0, 1, 2, 3, 4});
        qf.union(1, 2);
        assertArrayEquals(qf.getArr(), new int[]{0, 1, 1, 3, 4});
        qf.union(4, 0);
        assertArrayEquals(qf.getArr(), new int[]{4, 1, 1, 3, 4});
        qf.union(1, 3);
        assertArrayEquals(qf.getArr(), new int[]{4, 1, 1, 1, 4});
    }

    @Test
    void testExceptions() {
        assertThrows(IndexOutOfBoundsException.class, () -> qf.union(-1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> qf.union(2, -2));
        assertThrows(IndexOutOfBoundsException.class, () -> qf.union(1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> qf.union(6, 2));

        assertThrows(IndexOutOfBoundsException.class, () -> qf.connected(6, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> qf.connected(6, -2));
    }

    @Test
    void connected() {
        qf.union(1, 2);
        qf.union(4, 0);
        qf.union(1, 3);

        assertTrue(qf.connected(0, 4));
        assertTrue(qf.connected(4, 0));
        assertTrue(qf.connected(2, 1));
        assertTrue(qf.connected(3, 2));

        assertFalse(qf.connected(0, 1));
        assertFalse(qf.connected(1, 4));
        assertFalse(qf.connected(4, 2));
    }
}