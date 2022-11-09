package eda.controller;

import eda.estrutura.ListaLigadas;

public interface GenericDAO<T> {

	public void gravar(T t);
	public void remover(int index);
	public boolean pesquisar(String str);
	public ListaLigadas<?> lista();
}
