package eda.controller;

import eda.estrutura.ListaLigadas;
import eda.estrutura.Node;
import eda.model.Disciplina;

public class DisciplinaDao implements GenericDAO<Disciplina> {
	
	private ListaLigadas<Disciplina> disciplinas = new ListaLigadas<Disciplina>();
	
	@Override
	public void gravar(Disciplina t) {
		this.disciplinas.add(t);		
	}

	@Override
	public void remover(int index) {
		this.disciplinas.remove(index-1);
	}
	
	public void remover(Disciplina disciplina) {
		this.disciplinas.remove(disciplina);
	}

	@Override
	public boolean pesquisar(String str) {
		Disciplina disciplina = new Disciplina();
		disciplina.setNome(str);
		return this.disciplinas.exists(disciplina);
	}
	
	public Disciplina get(int index) {
		return this.disciplinas.get(index-1).getData();
	}

	@Override
	public ListaLigadas<Disciplina> lista() {
		return this.disciplinas;
	}

	public ListaLigadas<String> nomes(){
		ListaLigadas<String> nomes = new ListaLigadas<String>();
		Node<Disciplina> aux = lista().List();
		while(aux!=null) {
			nomes.add(aux.getData().getNome()); 
			aux = aux.getNext();
		}
		return nomes;
	}
	
}
