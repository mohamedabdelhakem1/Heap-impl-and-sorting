package eg.edu.alexu.csd.filestructure.sort.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import javax.management.RuntimeErrorException;
import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.INode;

public class Heap<T extends Comparable<T>> implements IHeap<T> {
	private ArrayList<INode<T>> arr;
	private int heapsize = 0;
	public Heap() {
		arr = new ArrayList<INode<T>>();
	}

	public Heap(ArrayList<INode<T>> arr) {
		this.arr = arr;
	}

	@Override
	public INode<T> getRoot() {
		if (arr.size() == 0) {
			throw new RuntimeErrorException(new Error());
		}
		return arr.get(0);
	}

	@Override
	public int size() {
		return arr.size();
	}

	@Override
	public T extract() {
		if (arr.size() == 0) {
			throw new RuntimeErrorException(new Error());
		}
		T max = (T) arr.get(0).getValue();
		arr.get(0).setValue(arr.get(arr.size() - 1).getValue());
		arr.remove(arr.size() - 1);
		heapsize--;
		if (arr.size() != 0)
			heapify(arr.get(0));
		return max;
	}
	
	@Override
	public void heapify(INode<T> node) {
		if (node == null) {
			throw new RuntimeErrorException(new Error());
		}
		Node left = (Heap<T>.Node) node.getLeftChild();
		Node right = (Heap<T>.Node) node.getRightChild();
		int i = ((Node) node).getIndex();
		Node largest;
		if (right(i) < heapsize && right != null && (right.getValue()).compareTo(node.getValue()) > 0) {
			largest = right;
		} else {
			largest = (Heap<T>.Node) node;
		}
		if (left(i) < heapsize && left != null && (left.getValue()).compareTo(largest.getValue()) > 0) {
			largest = left;
		}
		if (!largest.equals(node)) {
			T temp = node.getValue();
			node.setValue(largest.getValue());
			largest.setValue(temp);
			heapify(largest);
		}
	}

	@Override
	public void insert(T element) {
		if (element == null) {
			throw new RuntimeErrorException(new Error());
		}
		heapsize++;
		Node node = new Node();
		node.setIndex(arr.size());
		node.setValue(element);
		arr.add(node);
		increaseKey(arr.size() - 1);
	
	}

	private void increaseKey(int index) {
		int parent = 0;
		while (index > 0 && (arr.get(index).getParent().getValue()).compareTo(arr.get(index).getValue()) < 0) {
			parent = ((Node) arr.get(index).getParent()).getIndex();
			T temp = (T) arr.get(index).getValue();
			arr.get(index).setValue(arr.get(index).getParent().getValue());
			arr.get(index).getParent().setValue(temp);
			index = parent;
		}
	}

	@Override
	public void build(Collection<T> unordered) {
		if (unordered == null) {
			throw new RuntimeErrorException(new Error());
		}
		if (unordered.size() == 0) {
			return;
		}
		heapsize = unordered.size();
		arr = new ArrayList<INode<T>>();
		Iterator<T> iter = unordered.iterator();
		while (iter.hasNext()) {
			INode<T> node = new Node();
			((Heap<T>.Node) node).setIndex(arr.size());
			node.setValue(iter.next());
			arr.add(node);
		}

		for (int j = arr.size() / 2; j >= 0; j--) {
			heapify(arr.get(j));
		}
	}

	private int right(int i) {
		return 2 * i + 2;
	}

	private int left(int i) {
		return 2 * i + 1;
	}
	public IHeap<T> heapSort(ArrayList<T> unordered) {
		build(unordered);
		int num = unordered.size();
		for (int i = num-1 ; i >= 0; i--) {
			T temp = (T) arr.get(0).getValue();
			arr.get(0).setValue(arr.get(i).getValue());
			arr.get(i).setValue(temp);
			heapsize--;
			heapify(arr.get(0));
		}
		return this;
	}
	/**
	 * 
	 * @author SHIKO
	 *
	 */
	public class Node implements INode<T> {
		private T key;
		private int index;
		@Override
		public INode<T> getLeftChild() {
			if (2 * index + 1 >= arr.size()) {
				return null;
			}
			return arr.get(2 * index + 1);
		}

		@Override
		public INode<T> getRightChild() {
			if (2 * index + 2 >= arr.size()) {
				return null;
			}
			return arr.get(2 * index + 2);
		}

		@Override
		public INode<T> getParent() {
			if (index == 0) {
				return null;
			}
			return arr.get((index-1) / 2);
		}

		@Override
		public T getValue() {
			return key;
		}

		@Override
		public void setValue(T value) {
			key = value;
		}

		public void setIndex(int i) {
			index = i;
		}

		public int getIndex() {
			return index;
		}
	}
}
