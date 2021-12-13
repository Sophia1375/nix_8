package ua.com.alevel;

import java.util.Arrays;

public class MathSetImpl<E extends Number & Comparable<E>> implements MathSet<E> {

    private int INIT_CAP = 8;
    private Object[] arr;
    private int size = 0;

    public MathSetImpl() {
        arr = new Object[INIT_CAP];
    }

    public MathSetImpl(int capacity) {
        INIT_CAP = capacity;
        arr = new Object[INIT_CAP];
    }

    public MathSetImpl(Number[] numbers) {
        if (numbers == null) {
            this.arr = new Object[0];
        } else {
            this.arr = numbers;
            size = numbers.length;
        }

    }

    public MathSetImpl(Number[]... numbers) {
        INIT_CAP = 0;
        for (Number[] numArr : numbers) {
            INIT_CAP += numArr.length;
        }
        arr = new Object[INIT_CAP];
        for (Number[] numArr : numbers) {
            for (Number e : numArr) {
                add((E) e);
            }
        }
    }


    public MathSetImpl(MathSet<E> mathSet) {
        Number[] array = mathSet.toArray();
        INIT_CAP = array.length;
        arr = new Object[INIT_CAP];
        this.add(array);
    }

    public MathSetImpl(MathSet<E>... mathSets) {
        INIT_CAP = 0;
        for (MathSet<E> mathSet : mathSets) {
            INIT_CAP += mathSet.size();
        }
        arr = new Object[INIT_CAP];
        for (MathSet<E> mathSet : mathSets) {
            add(mathSet.toArray());
        }
    }

    @Override
    public int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Number[] toArray() {
        Number[] objects = new Number[size];
        for (int i = 0; i < size; i++) {
            objects[i] = (Number) arr[i];
        }
        return objects;
    }

    @Override
    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] result = new Number[lastIndex - firstIndex];
        var resultIdx = 0;
        for (int i = firstIndex; i < lastIndex; i++) {
            result[resultIdx++] = (Number) arr[i];
        }
        return result;
    }

    @Override
    public MathSet<E> cut(int firstIndex, int lastIndex) {
        Number[] es = toArray(firstIndex, lastIndex);
        return new MathSetImpl<>(es);
    }

    @Override
    public void clear() {
        size = 0;
        INIT_CAP = 8;
        arr = new Object[INIT_CAP];
    }

    @Override
    public void clear(E[] numbers) {
        for (E number : numbers) {
            remove(number);
        }
    }

    @Override
    public void add(Number e) {
        if (contains((E) e)) {
            return;
        }
        if (size >= arr.length) {
            grow();
        }
        arr[size] = e;
        size++;
    }

    private boolean contains(E e) {
        if (e == null) return false;
        for (Object obj : arr) {
            if (e.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Number... n) {
        for (Number e : n) {
            add(e);
        }
    }

    @Override
    public void join(MathSet<E> ms) {
        add(ms.toArray());
    }

    @Override
    public void join(MathSet<E>... ms) {
        for (MathSet<E> mathSet : ms) {
            add(mathSet.toArray());
        }
    }

    @Override
    public void intersection(MathSet<E> ms) {
        for (int i = 0; i < size(); i++) {
            final E element = get(i);
            var isDeleteNecessary = true;
            for (int j = 0; j < ms.size(); j++) {
                if (ms.get(j).equals(element)) {
                    isDeleteNecessary = false;
                    break;
                }
            }
            if (isDeleteNecessary) {
                remove(element);
                i--;
            }
        }
    }

    @Override
    public void intersection(MathSet<E>... ms) {
        for (MathSet<E> mathSet : ms) {
            intersection(mathSet);
        }
    }

    @Override
    public void sortDesc() {
        bubbleSort(false, 0, size() - 1);
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        if(firstIndex>size||lastIndex>size)return;
        bubbleSort(false, firstIndex, lastIndex);
    }

    @Override
    public void sortDesc(E value) {
        final int indexOf = indexOf(value);
        if (indexOf != -1) {
            bubbleSort(false, indexOf, size() - 1);
        }
    }

    private int indexOf(E value) {
        for (int i = 0; i < arr.length; i++) {
            if (value.equals(arr[i]))
                return i;
        }
        return -1;
    }

    @Override
    public void sortAsc() {
        bubbleSort(true, 0, size() - 1);
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        if(firstIndex>size||lastIndex>size)return;
        bubbleSort(true, firstIndex, lastIndex);
    }

    @Override
    public void sortAsc(E value) {
        final int indexOf = indexOf(value);
        if (indexOf != -1) {
            bubbleSort(true, indexOf, size() - 1);
        }
    }

    private void grow() {
        if (arr.length < INIT_CAP) {
            arr = Arrays.copyOf(arr, INIT_CAP);
        } else {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
    }

    private boolean remove(Object o) {
        boolean isDeleted = false;
        for (int i = 0; i < size; i++) {
            if (!isDeleted) {
                if (o.equals(arr[i])) {
                    arr[i] = null;
                    isDeleted = true;
                }
            } else {
                arr[i - 1] = arr[i];
                arr[i] = null;
            }
        }
        if (isDeleted) size--;
        return isDeleted;
    }

    @Override
    public E get(int index) {
        if (index > size - 1) {
            throw new RuntimeException("index more than size");
        }
        return (E) arr[index];
    }

    @Override
    public E getMax() {
        E max = (E) arr[0];
        for (int i = 0; i < size(); i++) {
            final E element = (E) arr[i];
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    @Override
    public E getMin() {
        E min = (E) arr[0];
        for (int i = 0; i < size(); i++) {
            final E element = (E) arr[i];
            if (element.compareTo(min) < 0) {
                min = element;
            }
        }
        return min;
    }

    @Override
    public Number getAverage() {
        var sum = 0d;
        for (int i = 0; i < size; i++) {
            sum += ((E) arr[i]).doubleValue();
        }
        sum /= size();
        return sum;
    }

    @Override
    public Number getMedian() {
        sortAsc();
        Number median;
        if (size % 2 == 0)

            median = (((E) arr[size / 2]).doubleValue() + ((E) arr[size / 2 - 1]).doubleValue()) / 2;
        else
            median = (Number) arr[size / 2];
        return median;
    }

    private void bubbleSort(boolean asc, int start, int end) {
        for (int i = start; i < end; i++)
            for (int j = start; j < end - (i - start); j++) {
                final E first = (E) arr[j];
                final E second = (E) arr[j + 1];
                if (asc ? first.compareTo(second) > 0 : first.compareTo(second) < 0) {
                    arr[j] = second;
                    arr[j + 1] = first;
                }
            }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            builder.append((E) arr[i]);
            builder.append(" ");
        }
        builder.append("]");
        return builder.toString();
    }
}
