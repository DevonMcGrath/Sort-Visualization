/* Name: Heap
 * Contributor: Randy Fortier
 */

package sorting;

public class Heap {
	private int[] elements;
	private int size;

	public Heap(int arraySize) {
		elements = new int[arraySize];
		size = 0;
	}

	public Heap(int[] array) {
		elements = array;
		size = array.length - 1; // don't count our filler first element
	}

	private void print() {
		printNode(1, 0);
		System.out.println("");
	}

	private void printAsArray() {
		for (int i = 1; i <= size; i++) {
			System.out.printf("%d ", elements[i]);
		}
		System.out.println("\n");
	}

	private void printNode(int i, int depth) {
		if (i > size) {
			return;
		}

		printNode(right(i), depth + 1);

		for (int j = 0; j < depth; j++) {
			System.out.print("   ");
		}
		System.out.println("" + elements[i]);

		printNode(left(i),  depth + 1);
	}

	// O(1)
	public int getMax() {
		return elements[1];
	}

	// O(lg n)
	public int removeMax() {
		if (size < 1) {
			// no element to return
			return -1;
		}

		int max = elements[1];

		elements[1] = elements[size];
		size--;
		heapify(1);

		return max;
	}

	// O(lg n)
	private void heapify(int i) {
		int left = left(i);
		int right = right(i);
		int largest;

		if ((left <= size) && (elements[left] > elements[i])) {
			largest = left;
		} else {
			largest = i;
		}

		if ((right <= size) && (elements[right] > elements[largest])) {
			largest = right;
		}

		if (largest != i) {
			swap(i, largest);
			heapify(largest);
		}
	}

	// O(n)
	public void buildHeap() {
		for (int i = size/2; i >= 1; i--) {
			heapify(i);
		}
	}

	public void insertArray(int value) {
		size++;
		elements[size] = value;
	}

	// O(lg n)
	public void insert(int value) {
		size++;
		elements[size] = value;

		// Maintain heap property
		int j = size;
		while ((elements[j] > elements[parent(j)]) && (j > 1)) {
			swap(j, parent(j));

			j = parent(j);
		}
	}

	public void swap(int i, int j) {
		int temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

	private int parent(int i) { return i / 2; }	
	private int left(int i) { return 2 * i; }
	private int right(int i) { return 2 * i + 1; }

	public int getSize() { return size; }
	public void decrementSize() { size--; }

	// O(n lg n)
	public static void heapsort(int[] array) {
		Heap heap = new Heap(array);
		heap.buildHeap();
		for (int i = heap.getSize(); i > 1; i--) {
			heap.swap(1, i);
			heap.decrementSize();
			heap.heapify(1);
		}
	}

	public static void main(String[] args) {
		Heap heap1 = new Heap(11);
		heap1.insert(4);
		heap1.insert(1);
		heap1.insert(3);
		heap1.insert(2);
		heap1.insert(16);
		heap1.insert(9);
		heap1.insert(10);
		heap1.insert(14);
		heap1.insert(8);
		heap1.insert(7);
		heap1.printAsArray();
		heap1.print();

		Heap heap2 = new Heap(11);
		heap2.insertArray(4);
		heap2.insertArray(1);
		heap2.insertArray(3);
		heap2.insertArray(2);
		heap2.insertArray(16);
		heap2.insertArray(9);
		heap2.insertArray(10);
		heap2.insertArray(14);
		heap2.insertArray(8);
		heap2.insertArray(7);
		//heap2.printAsArray();
		//heap2.print();
		heap2.buildHeap();
		//heap2.print();

		//log("Maximum value: " + heap2.getMax());
		//log("Removing maximum: " + heap2.removeMax());
		//heap2.print();

		int[] array = new int[] {-1,5,1,17,8,4,11,3,9,21};
		printArray(array);
		heapsort(array);
		printArray(array);
	}

	public static void printArray(int[] array) {
		System.out.print("[");
		for (int i = 1; i < array.length; i++) {
			if (i > 1)
				System.out.print(",");
			System.out.print(array[i]);
		}
		System.out.println("]");
	}
}