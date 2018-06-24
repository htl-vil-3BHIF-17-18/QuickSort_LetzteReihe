package bll;

import java.util.ArrayList;

import bll.SortHelper.SORT_TYPE;
import gui.MainFrame;

public abstract class SortingMethod {

	protected MainFrame f;
	protected SortHelper.SORT_TYPE sortType;

	public SortingMethod(MainFrame f, SORT_TYPE sortType) {
		this.f = f;
		this.sortType = sortType;
	}

	public abstract void sort(ArrayList<Integer> arrayToSort);

	public void switchElements(ArrayList<Integer> list, int index1, int index2) {
		int temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
		f.incrementSwapCounter();
	}

	public SortHelper.SORT_TYPE getSortType() {
		return sortType;
	}
	
}
