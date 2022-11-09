package eda.controller;

import eda.estrutura.ListaLigadas;
import eda.estrutura.Node;
import eda.model.PlanoCurricular;

public class PlanoCurricularDao implements GenericDAO<PlanoCurricular> {

	private ListaLigadas<PlanoCurricular> planoCurriculares = new ListaLigadas<PlanoCurricular>();

	@Override
	public void gravar(PlanoCurricular t) {
		this.planoCurriculares.add(t);
	}

	@Override
	public void remover(int index) {
		this.planoCurriculares.remove(index-1);
	}
	
	public void remover(PlanoCurricular t) {
		this.planoCurriculares.remove(t);
	}

	@Override
	public boolean pesquisar(String str) {
		PlanoCurricular plano = new PlanoCurricular();
		plano.setDescricao(str);
		return this.planoCurriculares.exists(plano);	
	}

	public PlanoCurricular get(int index) {
		return this.planoCurriculares.get(index-1).getData();
	}
	
	@Override
	public ListaLigadas<PlanoCurricular> lista() {
		return this.planoCurriculares;
	}

	public ListaLigadas<String> descricoes(){
		ListaLigadas<String> descricoes = new ListaLigadas<String>();
		Node<PlanoCurricular> aux = lista().List();
		while(aux!=null) {
			descricoes.add(aux.getData().getDescricao()); 
			aux = aux.getNext();
		}
		return descricoes;
	}
}
