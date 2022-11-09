package eda.model;

import eda.estrutura.ListaLigadas;
import eda.estrutura.Node;

public class Curso implements Comparable<Curso>{

	private String nome;
	private ListaLigadas<PlanoCurricular> planosCurriculares = new ListaLigadas<PlanoCurricular>();
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public ListaLigadas<PlanoCurricular> getPlanosCurriculares() {
		return planosCurriculares;
	}

	public void setPlanosCurriculares(ListaLigadas<PlanoCurricular> planosCurriculares) {
		this.planosCurriculares = planosCurriculares;
	}
	
	
	public void adicionarPlanoCurricular(PlanoCurricular pc) {
		this.planosCurriculares.add(pc);
	}
	
	public ListaLigadas<String> descricoesDoPlano(){
		ListaLigadas<String> descricoes = new ListaLigadas<String>();
		Node<PlanoCurricular> aux = planosCurriculares.List();
		while(aux!=null) {
			descricoes.add(aux.getData().getDescricao()); 
			aux = aux.getNext();
		}
		return descricoes;
	}
	
	@Override
	public String toString() {
		return "Curso: " + nome + "\n"
				+ planosCurriculares.toString() + "\n";
	}

	@Override
	public int compareTo(Curso o) {
		return this.nome.compareTo(o.getNome());
	}
}
