package eda.model;

import java.util.ArrayList;
import java.util.List;

public class PlanoCurricular {

	private String descricao;
	private List<Disciplina> disciplinas = new ArrayList<>();
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	public void adicionarDisciplina(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}
	
	public void removerDisciplina(int index) {
		this.disciplinas.remove(index);
	}
	
	@Override
	public String toString() {
		return "Plano curricular: " + descricao + "\n"
				+ this.disciplinas.toString()+"\n";
	}
	
}
