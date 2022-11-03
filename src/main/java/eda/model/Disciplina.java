package eda.model;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

	private String nome;
	private List<String> topicos = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<String> getTopicos() {
		return topicos;
	}
	
	public void setTopicos(List<String> topicos) {
		this.topicos = topicos;
	}
	
	public void adicionarTopico(String topico) {
		this.topicos.add(topico);
	}
	
	public void removerTopico(int index) {
		this.topicos.remove(index-1);
	}
	
	@Override
	public String toString() {
		return "Disciplina: " + nome + "\n"
				+ "            Topicos: \n"+topicos()+"\n";
	}
	
	private String topicos() {
		String listaFormatada = "";
		for (String topico : this.topicos) {
			listaFormatada += "                   - ".concat(topico+"\n");
		}
		return listaFormatada;
	}
}
