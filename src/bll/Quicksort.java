package bll;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import bll.SortHelper.SORT_TYPE;
import gui.MainFrame;

public class Quicksort extends SortingMethod {

	public Quicksort(MainFrame f, SORT_TYPE sortType) {
		super(f, sortType);
	}

	public void sort(ArrayList<Integer> arrayToSort) {
		quicksort(arrayToSort, 0, arrayToSort.size() - 1);
	}

	private void quicksort(ArrayList<Integer> arrayToSort, int left, int right) {
		int index = quickSortTeile(arrayToSort, left, right);
		if (left < index - 1)
			quicksort(arrayToSort, left, index - 1);
		if (index < right)
			quicksort(arrayToSort, index, right);
		f.drawArray(arrayToSort);
	}

	private int quickSortTeile(ArrayList<Integer> data, int left, int right) {
		int i = left;
		int j = right;
		int pivotElement = data.get((left + right) / 2);
		while (i <= j) {
			while (data.get(i) < pivotElement)
				i++;
			while (data.get(j) > pivotElement)
				j--;
			if (i <= j) {
				if (i != j) {
					HashMap<Integer, Color> hashmap = new HashMap<Integer, Color>();
					hashmap.put(i, Color.red);
					hashmap.put(j, Color.red);
					hashmap.put(data.indexOf(pivotElement), Color.blue);
					f.drawArray(data, hashmap);
					switchElements(data, i, j);
				}
				i++;
				j--;
			}
		}
		return i;
	}



}
