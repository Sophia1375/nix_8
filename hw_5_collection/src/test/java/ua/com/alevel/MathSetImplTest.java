package ua.com.alevel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MathSetImplTest {

    private MathSet<Integer> mathSet;

    @BeforeEach
    void setUp() {
        mathSet = new MathSetImpl<>();
    }

    @Test
    void add() {
        mathSet.add(1);
        mathSet.add(2);
        mathSet.add(3);
        assertEquals(3, mathSet.size());
    }

    @Test
    void addArray() {
        var integers = new Integer[]{1, 2};
        mathSet.add(integers);
        assertEquals(2, mathSet.size());
    }


    @Test
    void size() {
        assertEquals(0, mathSet.size());
        var integers = new Integer[]{1, 2};
        mathSet.add(integers);
        assertEquals(2, mathSet.size());
    }

    @Test
    void toArray() {
        var integers = new Integer[]{1, 2, 3};
        mathSet = new MathSetImpl<>(integers);
        final Number[] result = mathSet.toArray();
        assertEquals(result.length, result.length);
        for (int i = 0; i < integers.length; i++) {
            assertEquals(integers[i], result[i]);
        }
    }

    @Test
    void testToArray() {
        var integers = new Integer[]{1, 2, 3};
        mathSet = new MathSetImpl<>(integers);
        final Number[] result = mathSet.toArray(0, 2);
        assertEquals(2, result.length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(integers[i], result[i]);
        }
    }

    @Test
    void cut() {
        var integers = new Integer[]{1, 2, 3, 4};
        mathSet.add(integers);
        final MathSet<Integer> mathSetResult = mathSet.cut(0, 2);
        assertEquals(2, mathSetResult.size());
        for (int i = 0; i < mathSetResult.size(); i++) {
            assertEquals(integers[i], mathSetResult.get(i));
        }
    }

    @Test
    void clear() {
        var integers = new Integer[]{1, 2, 3, 4};
        mathSet.add(integers);
        assertEquals(4, mathSet.size());
        mathSet.clear();
        assertEquals(0, mathSet.size());
    }

    @Test
    void join() {
        var integers = new Integer[]{1, 2, 3, 4};
        mathSet.add(integers);
        var rightMathSet = new MathSetImpl<Integer>(new Number[]{4, 5, 6, 7});
        mathSet.join(rightMathSet);
        assertEquals(7, mathSet.size());
    }

    @Test
    void testIntersection() {
        var integers = new Integer[]{1, 2, 3, 4, 5};
        mathSet.add(integers);
        var rightMathSet = new MathSetImpl<Integer>(new Number[]{4, 5, 6, 7});
        mathSet.intersection(rightMathSet);
        assertEquals(2, mathSet.size());
        assertEquals(4, (int) mathSet.get(0));
        assertEquals(5, (int) mathSet.get(1));
    }

    @Test
    void sortDesc() {
        var integers = new Integer[]{1, 3, 4, 8, 5};
        mathSet.add(integers);
        mathSet.sortDesc();
        var result = new Integer[]{8, 5, 4, 3, 1};
        for (int i = 0; i < mathSet.size(); i++) {
            assertEquals(result[i], mathSet.get(i));
        }
    }

    @Test
    void testSortDesc() {
        var integers = new Integer[]{1, 3, 4, 8, 5};
        mathSet.add(integers);
        mathSet.sortDesc(1, 4);
        final Integer[] result = {1, 8, 5, 4, 3};
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], mathSet.get(i));
        }
    }

    @Test
    void testSortDescByValue() {
        var integers = new Integer[]{1, 3, 4, 8, 5};
        mathSet.add(integers);
        mathSet.sortDesc(3);
        final Integer[] result = {1, 8, 5, 4, 3};
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], mathSet.get(i));
        }
    }

    @Test
    void sortAsc() {
        var integers = new Integer[]{1, 3, 4, 8, 5};
        mathSet.add(integers);
        mathSet.sortAsc();
        var result = new Integer[]{1, 3, 4, 5, 8};
        for (int i = 0; i < mathSet.size(); i++) {
            assertEquals(result[i], mathSet.get(i));
        }
    }

    @Test
    void testSortAsc() {
        var integers = new Integer[]{1, 3, 4, 8, 5};
        mathSet.add(integers);
        mathSet.sortAsc(1, 4);
        final Integer[] result = {1, 3, 4, 5, 8};
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], mathSet.get(i));
        }
    }

    @Test
    void testSortAscByValue() {
        var integers = new Integer[]{1, 3, 4, 8, 5};
        mathSet.add(integers);
        mathSet.sortAsc(3);
        final Integer[] result = {1, 3, 4, 5, 8};
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], mathSet.get(i));
        }
    }

    @Test
    void get() {
        var integers = new Integer[]{1, 3, 4, 8, 5};
        mathSet.add(integers);
        assertEquals(1, (int) mathSet.get(0));
        assertEquals(3, (int) mathSet.get(1));
    }

    @Test
    void getMax() {
        var integers = new Integer[]{1, 3, 4, 8, 5};
        mathSet.add(integers);
        assertEquals(8, (int) mathSet.getMax());
    }

    @Test
    void getMin() {
        var integers = new Integer[]{3, 8, 4, 1, 5};
        mathSet.add(integers);
        assertEquals(1, (int) mathSet.getMin());
    }

    @Test
    void getAverage() {
        var integers = new Integer[]{1, 3, 4, 8, 5};
        mathSet.add(integers);
        assertEquals(4.2, mathSet.getAverage());
    }

    @Test
    void getMedian() {
        var integers = new Integer[]{1, 3, 4, 8, 5};
        mathSet.add(integers);
        assertEquals(4, mathSet.getMedian());
    }
}