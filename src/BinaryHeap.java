import java.util.Arrays;
import java.util.NoSuchElementException;

public class BinaryHeap {

    private int d = 2;
    private int[] heap;
    private int heapSize;
    
    public BinaryHeap(int capacity) {
        heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);

    }


    public boolean isEmpty() {
        return heapSize == 0;
    }


    public boolean isFull() {
        return heapSize == heap.length;
    }


    private int parent(int i) {
        return (i - 1) / d;
    }

    private int child(int i, int k) {
        return d * i + k;
    }


    public void insert(int x) {
        if (isFull())
            throw new NoSuchElementException("Heap is full, No space to insert new element");
        heap[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }


    public int delete(int x) {
        if (isEmpty())
            throw new NoSuchElementException("Heap is empty, No element to delete");
        int key = heap[x];
        heap[x] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(x);
        return key;
    }


    private void heapifyUp(int i) {
        int temp = heap[i];
        while (i > 0 && temp > heap[parent(i)]) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = temp;
    }


    private void heapifyDown(int i) {
        int child;
        int temp = heap[i];
        while (child(i, 1) < heapSize) {
            child = maxChild(i);
            if (temp < heap[child]) {
                heap[i] = heap[child];
            } else break;
            i = child;
        }
        heap[i] = temp;
    }

    private int maxChild(int i) {
        int leftChild = child(i, 1);
        int rightChild = child(i, 2);
        if (heap[leftChild] > heap[rightChild]) return leftChild;
        return rightChild;
    }


    public void printHeap() {
        System.out.print("nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }


    public int findMax() {
        if (isEmpty())
            throw new NoSuchElementException("Heap is empty.");
        return heap[0];
    }

    public int search(int value) {

        for (int index = 0; index < heap.length; index++) {
            if (heap[index] == value) {
                return index;
            }
        }
        return -1;
    }

}