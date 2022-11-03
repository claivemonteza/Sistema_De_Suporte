package eda.controller;

import java.util.List;

public interface GenericDAO<T> {

	public void gravar(T t);
	public void remover(int index);
	public T pesquisar(String str);
	public List<T> lista();
}
