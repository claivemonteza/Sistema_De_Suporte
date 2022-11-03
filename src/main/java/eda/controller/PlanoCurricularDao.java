package eda.controller;

import java.util.ArrayList;
import java.util.List;

import eda.model.PlanoCurricular;

public class PlanoCurricularDao implements GenericDAO<PlanoCurricular> {

	private List<PlanoCurricular> planoCurriculares = new ArrayList<PlanoCurricular>();

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
	public PlanoCurricular pesquisar(String str) {
		for (PlanoCurricular p: this.planoCurriculares) {
			if(p.getDescricao().toLowerCase().equals(str.toLowerCase())) {
				return p;
			}
		}
		return null;
	}

	public PlanoCurricular get(int index) {
		return this.planoCurriculares.get(index-1);
	}
	
	@Override
	public List<PlanoCurricular> lista() {
		return this.planoCurriculares;
	}

}
