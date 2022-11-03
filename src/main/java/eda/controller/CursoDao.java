package eda.controller;

import java.util.ArrayList;
import java.util.List;

import eda.model.Curso;
import eda.model.PlanoCurricular;

public class CursoDao implements GenericDAO<Curso> {

	private List<Curso> cursos = new ArrayList<Curso>();
	
	@Override
	public void gravar(Curso t) {
		this.cursos.add(t);
	}

	@Override
	public void remover(int index) {
		this.cursos.remove(index-1);
	}

	public void remover(Curso curso) {
		this.cursos.remove(curso);
	}
	
	@Override
	public Curso pesquisar(String str) {
		for (Curso c: this.cursos) {
			if(c.getNome().toLowerCase().equals(str.toLowerCase())) {
				return c;
			}
		}
		return null;
	}

	
	public Curso get(int index) {
		return this.cursos.get(index-1);
	}
	
	@Override
	public List<Curso> lista() {
		return this.cursos;
	}
	
	public void addPlanoCurricular(int index, PlanoCurricular p) {
		Curso c = get(index);
		c.getPlanosCurriculares().add(p);
	}

}
