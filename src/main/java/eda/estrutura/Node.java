package eda.estrutura;

public class Node <T extends Comparable<T>> {

	private T data;
	private Node<T> next;
	
	
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
	
	public Node(T data) {
		this.data = data;
		this.next = null;
	}
	
	public Node() {
		this.data = null;
		this.next = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public int compareTo(T data) {
		return this.data.compareTo(data);
	}
}
