package eda.model;

import eda.estrutura.ListaLigadas;
import eda.estrutura.Node;

public class Curso implements Comparable<Curso>{

	/*Variável para armazenar o nome do curso*/
	private String nome;
	
	// Variávelpara armazenar a lista de plano curriculares
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
	
	/*Metodo que permitir adicionar plano curricular*/
	public void adicionarPlanoCurricular(PlanoCurricular pc) {
		this.planosCurriculares.add(pc);
	}
	
	/*Metodo que retorna a lista de descrições dos plano curriculares*/
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
