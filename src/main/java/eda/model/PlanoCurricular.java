package eda.model;

import eda.estrutura.ListaLigadas;
import eda.estrutura.Node;

public class PlanoCurricular implements Comparable<PlanoCurricular>{
	/*Variável para armazenar o descrição do plano curricular*/
	private String descricao;
	// Variávelpara armazenar a lista de disciplinas
	private ListaLigadas<Disciplina> disciplinas = new ListaLigadas<Disciplina>();

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ListaLigadas<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	public void setDisciplinas(ListaLigadas<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	/*Metodo que retorna a lista de descrições das disciplinas do plano curricular*/
	public ListaLigadas<String> nomesDeDisciplinas(){
		ListaLigadas<String> nomes = new ListaLigadas<String>();
		Node<Disciplina> aux = disciplinas.List();
		while(aux!=null) {
			nomes.add(aux.getData().getNome()); 
			aux = aux.getNext();
		}
		return nomes;
	}
	
	/*Metodo que adicionar disciplinas no plano curricular*/
	public void adicionarDisciplina(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}
	
	/*Metodo que remover a disciplina de uma determinada posição*/
	public void removerDisciplina(int index) {
		this.disciplinas.remove(index);
	}
	
	@Override
	public String toString() {
		return "Plano curricular: " + descricao + "\n"
				+ this.disciplinas.toString()+"\n";
	}
	@Override
	public int compareTo(PlanoCurricular o) {
		return this.descricao.compareTo(o.getDescricao());
	}
	
}
