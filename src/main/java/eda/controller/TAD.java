package eda.controller;

import eda.estrutura.ListaLigadas;

public interface TAD<T> {

	/*Metodo que adiciona o novo object na lista*/
	public void gravar(T t);
	
	/*Metodo que remove o elemento uma determinada posição por parámetro na lista*/
	public void remover(int index);
	
	/*Metodo que remove o elemento na lista*/
	public void remover(T t);
	
	/*Metodo que retorna a verifica da existencia dum elemento com mesmo nome/descrição por parametro na lista*/
	public boolean pesquisar(String str);
	
	/*Metodo que retorna a lista*/
	public ListaLigadas<?> lista();
}
