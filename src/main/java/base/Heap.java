package base;

import java.util.*;

/**
 * The class of a binary min heap data structure.
 * Please refer to <a href="https://en.wikipedia.org/wiki/Heap_(data_structure)">this wikipedia page</a> for more details.
 * <p>
 * Note: We want to accept any type which is comparable to itself.
 * @param <T>
 */
public class Heap<T extends Comparable<T>> {
    private final ArrayList<T> container;

    public Heap() {
        container = new ArrayList<>();
    }

    /**
     * Find a min item of a min-heap.
     *
     * @return If size is 0, throw {@link IllegalStateException}.
     * Otherwise, return the first element of {@link #container}
     */
    public T peek() {
        // TODO
        if (size() == 0)
            throw new IllegalStateException();
        else
            return container.get(0);
    }

    /**
     * Remove the minimum item (root) of a min heap, respectively
     *
     * @return If size is 0, throw {@link IllegalStateException}. Otherwise, temporarily save the first element.
     * Afterward, set the first position to the last element, and remove the last element.
     * Call {@link #heapifyDown()}, then return the original first element
     */
    public T poll() {
        // TODO
        if (size() == 0)
            throw new IllegalStateException();
        T item = container.get(0);
        container.set(0, container.get(size() - 1));
        container.remove(size() - 1);

        heapifyDown();

        return item;
    }

    private void heapifyDown() {
        int pos = 0;
        while (hasLeft(pos)) {
            int smallerChildIndex = getLeftIndex(pos);
            if (hasRight(pos) && container.get(getRightIndex(pos)).compareTo(container.get(getLeftIndex(pos))) < 0) {
                smallerChildIndex = getRightIndex(pos);
            }
            if (container.get(pos).compareTo(container.get(smallerChildIndex)) < 0) {
                break;
            } else {
                swap(pos, smallerChildIndex);
            }
            pos = smallerChildIndex;
        }
    }

    /**
     * Add the object into {@link #container}, then call {@link #heapifyUp()}
     *
     * @param obj the object to add
     */
    public void add(T obj) {
        //TODO
        container.add(obj);
        heapifyUp();
    }

    public void addAll(Collection<T> list) {
        list.forEach(this::add);
    }

    /**
     * While the last element has a parent and is smaller than its parent, swap the two elements. Then, check again
     * with the new parent until there's either no parent or we're larger than our parent.
     * you can refer to {@link #heapifyDown()}.
     */
    private void heapifyUp() {
        // TODO
        int pos = size() - 1;
        while (hasParent(pos) && container.get(pos).compareTo(container.get(getParentIndex(pos))) < 0) {
            swap(pos, getParentIndex(pos));
            pos = getParentIndex(pos);
        }
    }

    public int size() {
        return container.size();
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    private int getLeftIndex(int i) {
        return 2 * i + 1;
    }

    private int getRightIndex(int i) {
        return 2 * i + 2;
    }

    private boolean hasParent(int i) {
        return getParentIndex(i) >= 0;
    }

    private boolean hasLeft(int i) {
        return getLeftIndex(i) < container.size();
    }

    private boolean hasRight(int i) {
        return getRightIndex(i) < container.size();
    }

    private void swap(int i1, int i2) {
        Collections.swap(container, i1, i2);
    }

    /**
     * The iterator to go through the private {@link #container}.
     */
    public class HeapIterator implements Iterator<T> {
        // TODO
        private int currentIndex;

        public boolean hasNext() {
            return currentIndex < container.size();
        }

        public T next() {
            if (hasNext()) {
                T element = container.get(currentIndex);
                currentIndex++;
                return element;
            }
            return null;
        }
    }

    public HeapIterator iterator() {
        return new HeapIterator();
    }

}
