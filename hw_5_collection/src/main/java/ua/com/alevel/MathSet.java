package ua.com.alevel;

public interface MathSet<T extends Number & Comparable<T>> {
    void add(Number n);

    void add(Number... n);

    void join(MathSet<T> ms);

    void join(MathSet<T>... ms);

    void intersection(MathSet<T> ms);

    void intersection(MathSet<T>... ms);

    void sortDesc();

    void sortDesc(int firstIndex, int lastIndex);

    void sortDesc(T value);

    void sortAsc();

    void sortAsc(int firstIndex, int lastIndex);

    void sortAsc(T value);

    T get(int index);

    T getMax();

    T getMin();

    Number getAverage();

    Number getMedian();

    Number[] toArray();

    Number[] toArray(int firstIndex, int lastIndex);

    MathSet cut(int firstIndex, int lastIndex);

    void clear();

    void clear(T[] numbers);

    int size();
}
