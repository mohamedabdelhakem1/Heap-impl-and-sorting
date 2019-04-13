package eg.edu.alexu.csd.filestructure.sort.impl;

import java.util.ArrayList;
import java.util.Collections;
import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.INode;
import eg.edu.alexu.csd.filestructure.sort.ISort;

public class Sort<T extends Comparable<T>> implements ISort<T> {

	@Override
	public IHeap<T> heapSort(ArrayList<T> unordered) {
		return (new Heap<T>()).heapSort(unordered);
	}

	@Override
	public void sortSlow(ArrayList<T> unordered) {
		if (unordered == null)
			return;
		for (int i = 0; i < unordered.size() - 1; i++) {
			for (int j = 0; j < unordered.size() - 1 - i; j++) {
				if (unordered.get(j).compareTo(unordered.get(j + 1)) > 0) {
					Collections.swap(unordered, j, j + 1);
				}
			}
		}
	}

	@Override
	public void sortFast(ArrayList<T> unordered) {
		if (unordered == null)
			return;
		mergeSort(unordered, 0, unordered.size() - 1);

	}

	private void mergeSort(ArrayList<T> unordered, int l, int r) {
		if (l < r) {
			int mid = (l + r) / 2;
			mergeSort(unordered, l, mid);
			mergeSort(unordered, mid + 1, r);
			merge(unordered, l, mid, r);
		}
	}

	private void merge(ArrayList<T> unordered, int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		ArrayList<T> L = new ArrayList<T>();
		ArrayList<T> R = new ArrayList<T>();

		for (int i = 0; i < n1; ++i)
			L.add(unordered.get(l + i));
		for (int j = 0; j < n2; ++j)
			R.add(unordered.get(m + 1 + j));

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (L.get(i).compareTo(R.get(j)) <= 0) {
				unordered.set(k, L.get(i));
				i++;
			} else {
				unordered.set(k, R.get(j));
				j++;
			}
			k++;
		}

		while (i < n1) {
			unordered.set(k, L.get(i));
			i++;
			k++;
		}

		while (j < n2) {
			unordered.set(k, R.get(j));
			j++;
			k++;
		}
	}
}
