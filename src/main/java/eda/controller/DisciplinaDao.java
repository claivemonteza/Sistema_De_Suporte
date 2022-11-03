package eda.controller;

import java.util.ArrayList;
import java.util.List;

import eda.model.Disciplina;

public class DisciplinaDao implements GenericDAO<Disciplina> {
	
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();

	@Override
	public void gravar(Disciplina t) {
		this.disciplinas.add(t);		
	}

	@Override
	public void remover(int index) {
		this.disciplinas.remove(index);
	}
	
	public void remover(Disciplina disciplina) {
		this.disciplinas.remove(disciplina);
	}

	@Override
	public Disciplina pesquisar(String str) {
		for (Disciplina d: disciplinas) {
			if(d.getNome().toLowerCase().equals(str.toLowerCase())) {
				return d;
			}
		}
		return null;
	}
	
	public Disciplina get(int index) {
		return disciplinas.get(index-1);
	}

	@Override
	public List<Disciplina> lista() {
		return disciplinas;
	}

}
