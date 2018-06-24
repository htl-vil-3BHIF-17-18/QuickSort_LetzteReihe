package bll;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import bll.SortHelper.SORT_TYPE;
import gui.MainFrame;

public class Heapsort extends SortingMethod {

	public Heapsort(MainFrame f, SORT_TYPE sortType) {
		super(f, sortType);
	}

	public void sort(ArrayList<Integer> arrayToSort) {
		heapsort(arrayToSort);
	}

	private void heapsort(ArrayList<Integer> array) {
		int length = array.size();

		buildMaxHeap(array, length);
		for (int i = length - 1; i > 0; i--) {
			HashMap<Integer, Color> hashmap = new HashMap<Integer, Color>();
			hashmap.put(i, Color.red);
			hashmap.put(0, Color.red);
			f.drawArray(array);
			switchElements(array, i, 0);
			maxHeapify(array, 1, i);
		}
	}

	private void buildMaxHeap(ArrayList<Integer> array, int heapSize) {
		if (array == null) {
			throw new NullPointerException("null");
		}
		if (array.size() <= 0 || heapSize <= 0) {
			throw new IllegalArgumentException("illegal");
		}
		if (heapSize > array.size()) {
			heapSize = array.size();
		}

		for (int i = heapSize / 2; i > 0; i--) {
			maxHeapify(array, i, heapSize);
		}
	}

	private void maxHeapify(ArrayList<Integer> array, int index, int heapSize) {
		int l = index * 2;
		int r = l + 1;
		int largest;

		if (l <= heapSize && array.get(l - 1) > array.get(index - 1)) {
			largest = l;
		} else {
			largest = index;
		}

		if (r <= heapSize && array.get(r - 1) > array.get(largest - 1)) {
			largest = r;
		}

		if (largest != index) {
			HashMap<Integer, Color> hashmap = new HashMap<Integer, Color>();
			hashmap.put(index - 1, Color.red);
			hashmap.put(largest - 1, Color.red);
			f.drawArray(array, hashmap);
			switchElements(array, index - 1, largest - 1);
			maxHeapify(array, largest, heapSize);
		}
	}
}
