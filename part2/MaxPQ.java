import java.util.Arrays;

public class MaxPQ<T extends Comparable<T>> implements PQInterface<T> {
    private T[] pq;
    private int N;
    private final float PERCENTAGE = 0.75f;

    MaxPQ(int maxN) {
        pq = (T[]) new Comparable[maxN + 1];
        N = 0;
    }

    private void resize() {
        pq = Arrays.copyOf(pq, pq.length * 2);
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void insert(T t) {
        pq[++N] = t;
        swim(N);
        if ((float) N / (pq.length - 1) > PERCENTAGE) {
            resize();
        }
    }

    @Override
    public T max() {
        return pq[1];
    }

    @Override
    public T getmax() {
        exch(1, N);
        sink(1, N - 1);
        return pq[N--];
    }

    private void swim(int current) {
        int parent = current / 2;
        if (current > 1 && less(current, parent)) {
            exch(current, parent);
            swim(parent);
        }
    }

    private void sink(int parent, int N) {
        while (2 * parent <= N) {
            int child = 2 * parent;
            if (child < N && less(child + 1, child)) child++;
            if (!less(child, parent)) break;
            exch(child, parent);
            parent = child;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
}
