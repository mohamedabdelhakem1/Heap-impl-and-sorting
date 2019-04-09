package eg.edu.alexu.csd.filestructure.sort.impl;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.ISort;

public class Sort<T extends Comparable<T>> implements ISort<T> {

	@Override
	public IHeap<T> heapSort(ArrayList<T> unordered) {
		Heap<T> heap = new Heap<T>();
		IHeap<T> heapCopy = new Heap<T>();
		heap.build(unordered);
		try {
			heapCopy = heap.clone();
		} catch (CloneNotSupportedException e) {
			
		}
		ArrayList<T> sorted = new ArrayList<>();
		for (int i = unordered.size(); i > 0; i--) {
			T t = (T) heap.extract();
			sorted.add(0,t);
		}
		unordered = sorted;
		return heapCopy;
	}

	@Override
	public void sortSlow(ArrayList<T> unordered) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sortFast(ArrayList<T> unordered) {
		// TODO Auto-generated method stub

	}

}
