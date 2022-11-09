package eda.model;

import eda.estrutura.ListaLigadas;
import eda.estrutura.Node;

public class PlanoCurricular implements Comparable<PlanoCurricular>{

	private String descricao;
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
	
	public ListaLigadas<String> nomesDeDisciplinas(){
		ListaLigadas<String> nomes = new ListaLigadas<String>();
		Node<Disciplina> aux = disciplinas.List();
		while(aux!=null) {
			nomes.add(aux.getData().getNome()); 
			aux = aux.getNext();
		}
		return nomes;
	}
	
	public void adicionarDisciplina(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}
	
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
