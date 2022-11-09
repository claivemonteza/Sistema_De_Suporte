package eda.estrutura;

public class Node <T extends Comparable<T>> {

	// Variável para armazenar dado útil
	private T data; 
	
	// Variávelpara armazenar endereço do próximo nó
	private Node<T> next;	
	
	
	// Construtor para criar e inserir o novo elemento a cabeça da lista
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
	
	// Construtor para criar novo elemento
	public Node(T data) {
		this.data = data;
		this.next = null;
	}
	
	// Construtor utilizado na criação do object cabeça
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
