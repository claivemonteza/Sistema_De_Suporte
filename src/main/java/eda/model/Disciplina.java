package eda.model;

import eda.estrutura.ListaLigadas;
import eda.estrutura.Node;

public class Disciplina implements Comparable<Disciplina> {

	/*Variável para armazenar o nome da disciplina*/
	private String nome;
	
	// Variávelpara armazenar a lista de topicos
	private ListaLigadas<String> topicos = new ListaLigadas<String>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ListaLigadas<String> getTopicos() {
		return topicos;
	}

	public void setTopicos(ListaLigadas<String> topicos) {
		this.topicos = topicos;
	}

	/*Metodo que permite adicionar topico*/
	public void adicionarTopico(String topico) {
		this.topicos.add(topico);
	}

	/*Metodo que permite remover o topico numa determinada posição*/
	public void removerTopico(int index) {
		this.topicos.remove(index - 1);
	}

	@Override
	public String toString() {
		return "Disciplina: " + nome + "\n" + "            Topicos: \n" + topicos() + "\n";
	}

	/*Metodo que retorna os topicos em formato string*/
	private String topicos() {
		String listaFormatada = "";
		Node<String> aux = this.topicos.List();
		while (aux != null) {
			listaFormatada += "                   - ".concat(aux.getData() + "\n");
			aux = aux.getNext();
		}
		return listaFormatada;

	}

	@Override
	public int compareTo(Disciplina o) {
		return this.nome.compareTo(o.getNome());
	}
}
