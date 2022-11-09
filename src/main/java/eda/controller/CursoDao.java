package eda.controller;

import eda.estrutura.ListaLigadas;
import eda.estrutura.Node;
import eda.model.Curso;
import eda.model.PlanoCurricular;

public class CursoDao implements GenericDAO<Curso> {

	private ListaLigadas<Curso> cursos = new ListaLigadas<Curso>();
	
	@Override
	public void gravar(Curso t) {
		this.cursos.add(t);
	}

	@Override
	public void remover(int index) {
		this.cursos.remove(index);
	}

	public void remover(Curso curso) {
		this.cursos.remove(curso);
	}
	
	@Override
	public boolean pesquisar(String str) {
		Curso curso = new Curso();
		curso.setNome(str);
		return this.cursos.exists(curso);
	}

	
	public Curso get(int index) {
		return this.cursos.get(index).getData();
	}
	
	@Override
	public ListaLigadas<Curso> lista() {
		return this.cursos;
	}
	
	public void addPlanoCurricular(int index, PlanoCurricular p) {
		Curso c = get(index);
		c.getPlanosCurriculares().add(p);
	}
	
	public ListaLigadas<String> nomes(){
		ListaLigadas<String> nomes = new ListaLigadas<String>();
		Node<Curso> aux = lista().List();
		while(aux!=null) {
			nomes.add(aux.getData().getNome()); 
			aux = aux.getNext();
		}
		return nomes;
	}

}
