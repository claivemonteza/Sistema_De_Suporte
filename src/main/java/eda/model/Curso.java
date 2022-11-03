package eda.model;

import java.util.ArrayList;
import java.util.List;

public class Curso {

	private String nome;
	private List<PlanoCurricular> planosCurriculares = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<PlanoCurricular> getPlanosCurriculares() {
		return planosCurriculares;
	}

	public void setPlanosCurriculares(List<PlanoCurricular> planosCurriculares) {
		this.planosCurriculares = planosCurriculares;
	}
	
	
	public void adicionarPlanoCurricular(PlanoCurricular pc) {
		this.planosCurriculares.add(pc);
	}
	
	@Override
	public String toString() {
		return "Curso: " + nome + "\n"
				+ planosCurriculares.toString() + "\n";
	}
}
