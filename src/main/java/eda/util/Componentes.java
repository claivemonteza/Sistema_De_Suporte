package eda.util;

import eda.estrutura.ListaLigadas;
import eda.estrutura.Node;

public class Componentes {

	public static String listaFormatada(ListaLigadas<String> dados) {
		int i = 1;
		String listaFormatada = "";
		Node<String> aux = dados.List();
		
		while(aux!=null) {
			listaFormatada += String.valueOf(i).concat(" - ").concat(aux.getData() + "\n");
			aux = aux.getNext();
			i++;
		}
		return listaFormatada;
	}
	
	public static void detalhes(int index, ListaLigadas<?> dados) {
		Object obj = dados.get(index - 1).getData();
		System.out.println(obj.toString());
	}
}
