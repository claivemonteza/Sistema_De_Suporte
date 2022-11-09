package eda.estrutura;
/*
 * Data: 29/Ago/2022
 * Instituiçãoo: USTM
 * Docente: Godinho Quissico
 * CLASSE GENÉRICA PARA DEFINIR UMA LISTA LIGADA
 */


public class ListaLigadas <T extends Comparable<T>> {
	//variavel que contere o nó especial para guardar a referê111ncia ao primeiro elemento 
	private Node <T> head;	
	
	//variavel que contere o número de elementos na lista.
	private static int size;	
	
	/*Construtor da nossa lista*/
	public ListaLigadas(){
		head=new Node<>(); //cria o elemento especial designado head (cabeça da lista).
		size=0;	
	}
	
	/*Método para fazer a inserção de elemento na cabeça da lista*/
	public boolean add(T elem){
		try{
			if(isEmpty()){
				head.setNext(new Node<>(elem));
			}else{
				head.setNext(new Node<>(elem, List()));
			}
			size++;
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/*Método para fazer a inserção em qualque parte da lista*/
	public boolean add(T elem, int pos){
		try{
			if(pos<=size+1 || pos >= 1){
				switch(pos){
				case 1: 
					add(elem);
					break;
				default:
					Node<T> aux= List();
					Node<T> novo = new Node<>(elem);
					int cont=1;
					while(aux!=null){
						if(cont == pos-1)
							break;
						cont++;
						aux=aux.getNext();
					}
					novo.setNext(aux.getNext());
					aux.setNext(novo);
					break;
				}
			}else throw new RuntimeException();
			size++;
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/*Método que retorna o número de elementos contidos na lista*/
	public int getSize(){
		return size;
	}
	
	/*Metodo que verifica se a lista esta vazia*/
	public boolean isEmpty(){
		return head.getNext() == null;
	}
	
	/*M�todo que esvazia a lista*/
	public void EmptyList(){
		head.setNext(null);
		size=0;
	}
	
	/*Metodo que verifica se um determinado elemento existe na lista*/
	public boolean exists(T elem){
		return Search(elem)!=null;
	}
	
	
	/*Metodo que retorna o nó que contém o elemento passado por parámetro*/
	private Node<T> Search(T elem){
		try{
			Node<T> aux = List();
			while(aux!=null){
				if(aux.compareTo(elem) == 0)
					return aux;
				aux=aux.getNext();
			}
			throw new RuntimeException();
		}catch(Exception e){
			return null;
		}
	}
	
	/*Metodo que retorna a lista de nó que conté elementos*/
	public Node<T> List(){
		return head.getNext();
	}
	
	/*Metodo que retorna uma string com todos os elementos na ordem de inserção*/
	public String toString(){
		try{
			StringBuilder str = new StringBuilder();
			Node<T> aux = List();
			while(aux!=null){
				str.append(aux.getData());
				str.append(" ");
				aux=aux.getNext();
			}
			return str.toString();
		}catch(Exception e){
			return "";
		}
	}
	
	/*Metodo que remove da lista um determinado elemento recebido por parámetro*/
	public boolean remove(T elem){
		try{			
			Node <T> toRemove=Search(elem);
			if(isEmpty() || toRemove==null) throw new RuntimeException();
			else{
				
				if(List().compareTo(elem)==0){
					head.setNext(List().getNext());
				}else{
					Node<T> aux = List();
					while(aux!=null){
						if(aux.getNext().compareTo(elem)==0)
							break;
						aux=aux.getNext();
					}
					aux.setNext(toRemove.getNext());
				}
			}
			size--;
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/*Metodo que remove um elemento de uma determinada posição da lista*/
	public boolean remove(int pos){
		try{
			
			if(pos <= size && pos >= 1){
				switch(pos){
				case 1: 
					head.setNext(List().getNext()); 
					break;
				default:
					Node<T> aux = List();
					int cont=1;
					while(aux!=null){
						if(cont == pos -1)
							break;
						aux=aux.getNext();
						cont++;
					}
					Node<T> toRemove=aux.getNext();
					aux.setNext(toRemove.getNext());
					break;
				}
			}else throw new RuntimeException();
			size--;
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	/*Metodo que retorna o nó que contém o elemento de uma determinada posição por par�metro*/
	public Node<T> get(int pos){
		try{
			
			int auxsize = 0;
			Node<T> aux = List();
			while(aux!=null){
				if(auxsize == pos)
					return aux;
				aux=aux.getNext();
				auxsize++;
			}
			throw new RuntimeException();
		}catch(Exception e){
			return null;
		}
	}
}
