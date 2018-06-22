package bll;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import gui.MainFrame;

public class SortHelper {

	private static SortHelper instance;
	private static MainFrame f;

	public enum SORT_TYPE {
		quicksort, mergesort
	}

	private SORT_TYPE selectedSort = SORT_TYPE.quicksort;

	private SortHelper() {
	}

	public void sort(ArrayList<Integer> arrayToSort) {
		if (selectedSort == SORT_TYPE.quicksort) {
			quicksort(arrayToSort, 0, arrayToSort.size() - 1);
		}
	}

	public static SortHelper getInstance(MainFrame frame) {
		if (instance == null) {
			instance = new SortHelper();
		}
		f = frame;
		return instance;
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
		int pivotElement = data.get((left + right) / 2); // pivot element ist
															// die mitte des
															// array

		while (i <= j) {
			while (data.get(i) < pivotElement)
				i++;
			while (data.get(j) > pivotElement)
				j--;
			if (i <= j) {
				if (i != j) {
					switchElements(data, i, j);
					HashMap<Integer, Color> hashmap = new HashMap<Integer, Color>();
					hashmap.put(i, Color.red);
					hashmap.put(j, Color.red);
					hashmap.put(data.indexOf(pivotElement), Color.blue);
					f.drawArray(data, hashmap);
				}
				i++;
				j--;
			}
		}
		return i;
	}

	private void switchElements(ArrayList<Integer> list, int index1, int index2) {
		int temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}

	public void setSelectedSort(SORT_TYPE selectedSort) {
		this.selectedSort = selectedSort;
	}

	public String[] getSortTypes() {
		String[] types = new String[SORT_TYPE.values().length];
		for(int i = 0; i < SORT_TYPE.values().length; i++)
			types[i] = SORT_TYPE.values()[i].toString();
		return types;
	}
	
}
