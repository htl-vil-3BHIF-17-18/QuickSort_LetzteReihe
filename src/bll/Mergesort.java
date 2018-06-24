package bll;

import java.util.ArrayList;

import bll.SortHelper.SORT_TYPE;
import gui.MainFrame;

public class Mergesort extends SortingMethod {

	public Mergesort(MainFrame f, SORT_TYPE sortType) {
		super(f, sortType);
	}

	public void sort(ArrayList<Integer> arrayToSort) {
		mergesort(arrayToSort, 0, arrayToSort.size() - 1);
	}

	private void mergesort(ArrayList<Integer> arrayToSort, int l, int r) {
		if (l < r) {
			int q = (l + r) / 2;
			mergesort(arrayToSort, l, q);
			mergesort(arrayToSort, q + 1, r);
			merge(arrayToSort, l, q, r);
		}
	}

	private void merge(ArrayList<Integer> arrayToSort, int l, int q, int r) {
		int[] arr = new int[arrayToSort.size()];
		int i, j;
		for (i = l; i <= q; i++) {
			arr[i] = arrayToSort.get(i);
		}
		for (j = q + 1; j <= r; j++) {
			arr[r + q + 1 - j] = arrayToSort.get(j);
		}
		i = l;
		j = r;
		for (int k = l; k <= r; k++) {
			if (arr[i] <= arr[j]) {
				arrayToSort.set(k, arr[i]);
				f.drawArray(arrayToSort);
				i++;
			} else {
				arrayToSort.set(k, arr[j]);
				f.drawArray(arrayToSort);
				j--;
			}
		}
	}
}
