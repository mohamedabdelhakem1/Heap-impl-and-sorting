package eg.edu.alexu.csd.filestructure.sort.impl;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import javax.management.RuntimeErrorException;
import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.INode;

public class Heap<T extends Comparable<T>> implements IHeap<T> {
	private int capacity = 101;
	private int heapSize = 0;
	private Node[] arr;

	public Heap() {
		arr = (Node[]) Array.newInstance(Node.class, capacity);
	}

	public Heap(Node[] arr, int heapsize, int capacity) {
		this.arr = arr;
		this.heapSize = heapsize;
		this.capacity = capacity;

	}

	@Override
	public INode<T> getRoot() {
		if (heapSize == 0) {
			throw new RuntimeErrorException(new Error());
		}
		return arr[1];
	}

	@Override
	public int size() {
		return heapSize;
	}

	@Override
	public T extract() {
		if (heapSize < 1) {
			throw new RuntimeErrorException(new Error());
		}
		T max = arr[1].getValue();
		arr[1].setValue(arr[heapSize].getValue());
		heapSize--;
		heapify(arr[1]);
		arr[heapSize + 1] = null;
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
		if (right(i) <= heapSize && (right.getValue()).compareTo(node.getValue()) > 0) {
			largest = right;
		} else {
			largest = (Heap<T>.Node) node;
		}
		if (left(i) <= heapSize && (left.getValue()).compareTo(largest.getValue()) > 0) {
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
		if (heapSize >= capacity - 1) {
			capacity = capacity * 2;
			Node[] newArr = (Node[]) Array.newInstance(Node.class, capacity);
			for (int i = 1; i <= heapSize; i++) {
				newArr[i] = arr[i];
			}
			arr = newArr;
		}

		heapSize++;
		Node node = new Node();
		node.setIndex(heapSize);
		node.setValue(element);
		arr[heapSize] = node;
		increaseKey(heapSize);
	}

	private void increaseKey(int index) {
		int parent = 0;
		while (index > 1 && (arr[index].getParent().getValue()).compareTo(arr[index].getValue()) < 0) {
			parent = ((Node) arr[index].getParent()).getIndex();
			T temp = arr[index].getValue();
			arr[index].setValue(arr[index].getParent().getValue());
			arr[index].getParent().setValue(temp);
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
		heapSize = unordered.size();
		if (heapSize > capacity + 1) {
			capacity = heapSize + 1;
		}
		arr = (Node[]) Array.newInstance(Node.class, capacity);
		Iterator<T> iter = unordered.iterator();
		int i = 1;
		while (iter.hasNext()) {
			Node node = new Node();
			node.setIndex(i);
			node.setValue(iter.next());
			arr[i] = node;
			i++;
		}

		for (int j = heapSize / 2; j > 0; j--) {
			heapify(arr[j]);
		}
	}

	private int right(int i) {
		return 2 * i + 1;
	}

	private int left(int i) {
		return 2 * i;
	}

	@Override
	protected IHeap<T> clone() throws CloneNotSupportedException {
		Node[] a = (Node[]) Array.newInstance(Node.class, capacity);
		Heap<T> heap = new Heap<T>(a, heapSize, capacity);
		
		for (int i = 1; i <= heapSize; i++) {
			Node n = new Node();
			n.setIndex(arr[i].getIndex());
			n.setValue(arr[i].getValue());
			a[i] = n;
		}
		return heap;
	}

	/**
	 * 
	 * @author SHIKO
	 *
	 */
	class Node implements INode<T> {
		private T key;
		private int index;
		@Override
		public INode<T> getLeftChild() {
			if (2 * index > arr.length - 1) {
				return null;
			}
			return arr[2 * index];
		}

		@Override
		public INode<T> getRightChild() {
			if (2 * index + 1 > arr.length - 1) {
				return null;
			}
			return arr[2 * index + 1];
		}

		@Override
		public INode<T> getParent() {
			if (index / 2 < 1) {
				return null;
			}
			return arr[index / 2];
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
