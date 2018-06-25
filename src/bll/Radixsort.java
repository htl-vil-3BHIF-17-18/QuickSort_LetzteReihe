package bll;

import java.util.ArrayList;
import java.util.Arrays;

import bll.SortHelper.SORT_TYPE;
import gui.MainFrame;

public class Radixsort extends SortingMethod {

	public Radixsort(MainFrame f, SORT_TYPE sortType) {
		super(f, sortType);
	}

	public void sort(ArrayList<Integer> arrayToSort) {
		radixsort(arrayToSort, arrayToSort.size());
	}

	static int getMax(ArrayList<Integer> arr, int n) {
		int mx = arr.get(0);
		for (int i = 1; i < n; i++)
			if (arr.get(i) > mx)
				mx = arr.get(i);
		return mx;
	}

	private void countSort(ArrayList<Integer> arr, int n, int exp) {
		int output[] = new int[n];
		int i;
		int count[] = new int[10];
		Arrays.fill(count, 0);

		for (i = 0; i < n; i++)
			count[(arr.get(i) / exp) % 10]++;

		for (i = 1; i < 10; i++)
			count[i] += count[i - 1];

		for (i = n - 1; i >= 0; i--) {
			output[count[(arr.get(i) / exp) % 10] - 1] = arr.get(i);
			count[(arr.get(i) / exp) % 10]--;
		}

		for (i = 0; i < n; i++) {
			arr.set(i, output[i]);
			f.drawArray(arr);
		}

	}

	private void radixsort(ArrayList<Integer> arr, int n) {
		int m = getMax(arr, n);
		for (int exp = 1; m / exp > 0; exp *= 10)
			countSort(arr, n, exp);
	}

}
