/*
 * Name: Aydan Pirani
 * Assignment: Sorting
 * Assignment Desc: Make a program that can sort arrays at varying rates and display the time taken
 */
package aydan_Pirani_Sorting;

import java.util.ArrayList;
import java.util.Arrays;

public class Aydan_Pirani_Sorting<E> extends ArrayList<E> {

	public static void main (String[] args) {
		int[] sizes = {50, 100, 200, 250, 300};
		createTable(sizes);
	}

	public static void createTable (int[] sizes) {
		System.out.println("      ___________________________________________________ ");
		System.out.println("     | SIZES |  SELECTION   MERGE   QUICK    HEAP   RADIX|");
		System.out.println("     |___________________________________________________|");
		for (int i = 0; i < sizes.length; i++) {
			int[] array = createArray(sizes[i]);
			double long1 = Math.round(selectionSort(Arrays.copyOf(array,sizes[i])) / 1000.0)/1000.0;
			double long2 = Math.round(mergeSort(Arrays.copyOf(array,sizes[i])) / 1000.0)/1000.0;
			double long3 = Math.round(quickSort(Arrays.copyOf(array,sizes[i])) / 1000.0)/1000.0;
			double long4 = Math.round(heapSort(Arrays.copyOf(array,sizes[i])) / 1000.0)/1000.0;
			double long5 = Math.round(radixSort(Arrays.copyOf(array,sizes[i])) / 1000.0)/1000.0;
			if (i == 0) {
				System.out.printf("     |0" + sizes[i] + "   |  " + "%.2f" + "        " + "%.2f" + "    " + "%.2f" + "     " + "%.2f" + "   " + "%.2f" + "  |", long1, long2, long3, long4, long5);
				System.out.println();
			} else {
				System.out.printf("     |" + sizes[i] + "   |  " + "%.2f" + "        " + "%.2f" + "    " + "%.2f" + "     " + "%.2f" + "   " + "%.2f" + "  |", long1, long2, long3, long4, long5);
				System.out.println();
			}
			System.out.println("     |___________________________________________________|");
		}
	}

	public static int[] createArray(int length) {
		int[] output = new int[length];
		for (int i = 0; i < length; i++) {
			output[i] = (int) Math.floor(Math.random()*100);
		}
		return output;
	}

	public static long selectionSort(int[] array) { //Selection sort algorithm
		long startTime = System.nanoTime();
		for (int i = 0; i < array.length; i++) {
			int currentIndex = array[i];
			for (int j = i; j < array.length; j++) {
				if (array[j] < currentIndex){
					currentIndex = array[j];
					int tempVar = array[i];
					array[i] = currentIndex;
					array[j] = tempVar;
				}
			}
		}
		long endTime = System.nanoTime();
		return (endTime - startTime);
	}

	public static long mergeSort(int[] list) {
		long startTime = System.nanoTime();
		if (list.length > 1) {

			int[] firstHalf = new int[list.length / 2];
			System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
			mergeSort(firstHalf);
			int secondLength = list.length - list.length / 2;
			int[] secondHalf = new int[secondLength];
			System.arraycopy(list, list.length / 2,
					secondHalf, 0, secondLength);
			mergeSort(secondHalf);

			merge(firstHalf, secondHalf, list);
		}
		long endTime = System.nanoTime();
		return (endTime - startTime);
	}

	/** Merge two sorted lists */
	public static void merge(int[] firstHalf, int[] secondHalf, int[] temp) {
		int positionList1 = 0; // Current index in firstHalf
		int positionList2 = 0; // Current index in secondHalf
		int outputPosition = 0; // Current index in temp

		while (positionList1 < firstHalf.length && positionList2 < secondHalf.length) {
			if (firstHalf[positionList1] < secondHalf[positionList2])
				temp[outputPosition++] = firstHalf[positionList1++];
			else
				temp[outputPosition++] = secondHalf[positionList2++];
		}

		while (positionList1 < firstHalf.length)
			temp[outputPosition++] = firstHalf[positionList1++];

		while (positionList2 < secondHalf.length)
			temp[outputPosition++] = secondHalf[positionList2++];
	}


	public static long quickSort (int[] array) {
		long startTime = System.nanoTime();

		quickSort(array, 0, array.length);

		long endTime = System.nanoTime();
		return (endTime - startTime);
	}
	public static void quickSort (int[] array, int low, int high) {
		if (high-low > 1){
			int pivotPosition = low;
			int sizeLessThan = 0;
			for (int i = low; i < array.length; i++) {
				if (array[i] < array[pivotPosition]) {
					int temporaryInt = array[pivotPosition];
					array[pivotPosition] = array[i];
					array[i] = temporaryInt;
					pivotPosition++; sizeLessThan++;
				}
			}
			quickSort(array, low, sizeLessThan);
			quickSort(array, sizeLessThan, high-1);
		}
	}

	public static long heapSort (int[] array) {
		long startTime = System.nanoTime();
		// Create a Heap of integers

		Heap<Integer> heap = new Heap<Integer>();

		// Add elements to the heap
		for (int i = 0; i < array.length; i++)
			heap.add(array[i]);

		// Remove elements from the heap
		for (int i = array.length - 1; i >= 0; i--)
			array[i] = heap.remove();

		long endTime = System.nanoTime();
		return (endTime - startTime);
	}

	public static long radixSort (int[] array) {
		long startTime = System.nanoTime();

		radixSort(array,1);	

		long endTime = System.nanoTime();
		return (endTime - startTime);
	}

	public static void radixSort(int[] array, int index) {
		int currentMax = 0;
		for (int i = 0; i < array.length; i++) {
			currentMax = array[i];
			if (array[i] > currentMax) {
				currentMax = array[i];
			}
		}
		index = (int) Math.ceil(Math.log10(currentMax));

		ArrayList<Integer> output = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			output.add(array[i]);
		}

		for (int i = 0; i < index; i++) {
			bucketSort(output , i);
		}

	}

	public static void bucketSort(ArrayList<Integer> array, int power) {
		ArrayList<Integer>[] bucket = new ArrayList[10];

		for (int i = 0; i < array.size(); i++) {
			int key = (int) (array.get(i) % Math.pow(10, power));

			if (bucket[key] == null) { 
				bucket[key] = new java.util.ArrayList<>(); 
			}

			bucket[key].add(array.get(i));
		}
		int k = 0;
		for (int i = 0; i < bucket.length; i++) {
			if (bucket[i] != null) {
				for (int j = 0; j < bucket[i].size(); j++) {
					array.set(k, bucket[i].get(j));
					k++;
				}
			}
		}

	}

	public static int[] convertToList(ArrayList <Integer> array) {
		int[] output = new int[array.size()];
		for (int i = 0; i < array.size(); i++) {
			output[i] = array.get(i);
		}
		return output;
	}
}

class Heap<E extends Comparable> {
	private java.util.ArrayList<Integer> list = new java.util.ArrayList<Integer>();

	/** Create a default heap */
	public Heap() {
	}

	/** Create a heap from an array of objects */
	public Heap(int[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	/** Add a new object into the heap */
	public void add(int newObject) {
		list.add(newObject); // Append to the heap
		int currentIndex = list.size() - 1; // The index of the last node

		while (currentIndex > 0) {
			int parentIndex = (currentIndex - 1) / 2;
			// Swap if the current object is greater than its parent
			if (list.get(currentIndex).compareTo(
					list.get(parentIndex)) > 0) {
				int temp = list.get(currentIndex);
				list.set(currentIndex, list.get(parentIndex));
				list.set(parentIndex, temp);
			}
			else
				break; // the tree is a heap now

			currentIndex = parentIndex;
		}
	}

	/** Remove the root from the heap */
	public int remove() {
		if (list.size() == 0) return (Integer) null;

		int removedObject = list.get(0);
		list.set(0, list.get(list.size() - 1));
		list.remove(list.size() - 1);

		int currentIndex = 0;
		while (currentIndex < list.size()) {
			int leftChildIndex = 2 * currentIndex + 1;
			int rightChildIndex = 2 * currentIndex + 2;

			// Find the maximum between two children
			if (leftChildIndex >= list.size()) break; // The tree is a heap
			int maxIndex = leftChildIndex;
			if (rightChildIndex < list.size()) {
				if (list.get(maxIndex).compareTo(
						list.get(rightChildIndex)) < 0) {
					maxIndex = rightChildIndex;
				}
			}

			// Swap if the current node is less than the maximum
			if (list.get(currentIndex).compareTo(
					list.get(maxIndex)) < 0) {
				int temp = list.get(maxIndex);
				list.set(maxIndex, list.get(currentIndex));
				list.set(currentIndex, temp);
				currentIndex = maxIndex;
			}
			else
				break; // The tree is a heap
		}

		return removedObject;
	}

	/** Get the number of nodes in the tree */
	public int getSize() {
		return list.size();
	}
}
