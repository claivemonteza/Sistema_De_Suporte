package eda.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import eda.controller.DisciplinaDao;
import eda.controller.PlanoCurricularDao;
import eda.model.Disciplina;
import eda.model.PlanoCurricular;
import eda.util.Componentes;

public class PlanoMenu {

	private Scanner sc;
	private PlanoCurricularDao planoCurricularDao;
	private DisciplinaDao disciplinaDao;

	public PlanoMenu(PlanoCurricularDao planoCurricularDao, DisciplinaDao disciplinaDao, Scanner sc) {
		this.planoCurricularDao = planoCurricularDao;
		this.disciplinaDao = disciplinaDao;
		this.sc = sc;
	}

	public void init() {
		System.out.print("********** PLANO CURRICULAR **********\n" + "1 - Criar plano curricular\n"
				+ "2 - Actualizar plano curricular\n" + "3 - Remover plano curricular\n"
				+ "4 - Lista de plano curricular\n" + "5 - Voltar\n" + "Selecione a opção: ");
		int opcao = sc.nextInt();
		switch (opcao) {
		case 1:
			criarPlanoCurricular();
			break;
		case 2:
			actualizarPlanoCurricular();
			break;
		case 3:
			removerPlanoCurricular();
			break;
		case 4:
			listaDePlanosCurriculares();
			break;
		case 5:
			Menu.init();
			break;
		default:
			Menu.init();
			break;
		}
	}

	public void criarPlanoCurricular() {
		gravarPlano();
		init();
	}

	public void actualizarPlanoCurricular() {
		try {
			PlanoCurricular planoCurricular = selecionarPlano("ACTUALIZAR PLANO CURRICULAR");
			if (planoCurricular != null) {
				menuDisciplinas(planoCurricular);
			}
			init();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Ocurreu um erro! Certifique-se de selecionar as opções acima");
			actualizarPlanoCurricular();
		}
	}

	public void removerPlanoCurricular() {
		try {
			PlanoCurricular planoCurricular = selecionarPlano("REMOVER PLANO CURRICULAR");
			if (planoCurricular != null) {
				this.planoCurricularDao.remover(planoCurricular);
				System.out.println("Removido com sucesso!");
			}
			init();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Ocurreu um erro! Certifique-se de selecionar as opções acima");
			removerPlanoCurricular();
		}
	}

	private void listaDePlanosCurriculares() {
		try {
			if (!this.planoCurricularDao.lista().isEmpty()) {
				List<String> descricoes = this.planoCurricularDao.lista().stream().map(p -> p.getDescricao())
						.collect(Collectors.toList());
				System.out.println("********** LISTA DE PLANOS CURRICULARES **********\n"
						+ Componentes.listaFormatada(descricoes) + "0 - Voltar\n" + "Selecione a Plano: ");
				int opcao = sc.nextInt();
				if (opcao != 0) {
					Componentes.detalhes(opcao, this.planoCurricularDao.lista());
				}
			} else {
				System.out.println("Não exite nenhum registo!!!");
			}
			init();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Ocurreu um erro! Certifique-se de selecionar as opções acima");
			listaDePlanosCurriculares();
		}
	}

	public void menuDisciplinas(PlanoCurricular planoCurricular) {
		System.out.println("********** Disciplinas ao Plano curricular de " + planoCurricular.getDescricao()
				+ " **********\n" + "1 - Adicionar Disciplina\n" + "2 - Remover disciplina\n" + "0 - Voltar\n"
				+ "Selecione opção: ");
		int opcao = sc.nextInt();
		switch (opcao) {
		case 1:
			adicionaPC(planoCurricular);
			break;
		case 2:
			removerDisciplinaNoPlano(planoCurricular);
			break;
		default:
			actualizarPlanoCurricular();
			break;
		}
	}

	public Disciplina adicionaPC(PlanoCurricular planoCurricular) {
		Disciplina disciplinaSelecionada = null;
		System.out.print("********** DISCIPLINA **********\n" + "1 - Criar disciplina\n" + "2 - Seleciona disciplina\n"
				+ "Selecione a opção: ");
		int opcao = sc.nextInt();
		switch (opcao) {
		case 1:
			AdicionarDisciplinas(planoCurricular);
			break;
		case 2:
			if (!this.disciplinaDao.lista().isEmpty()) {
				selecionarDisciplinas(planoCurricular);
			} else {
				System.out.println("Não exite nenhum registo!!!");
				AdicionarDisciplinas(planoCurricular);
			}
			break;
		default:;
			break;
		}

		return disciplinaSelecionada;
	}

	public PlanoCurricular gravarPlano() {
		System.out.print("********** CRIAR PLANO CURRICULAR **********\n" + "Descrição: ");
		String descricao = sc.next();
		if (this.planoCurricularDao.pesquisar(descricao) != null) {
			System.out.println("Já existe um plano curricular com essa descrição");
		} else {
			PlanoCurricular pc = new PlanoCurricular();
			pc.setDescricao(descricao);
			adicionaPC(pc);
			this.planoCurricularDao.gravar(pc);
			return pc;
		}
		return null;
	}

	private void AdicionarDisciplinas(PlanoCurricular planoCurricular) {
		String gravar = "";
		do {
			Disciplina disciplina = new DisciplinaMenu(disciplinaDao, sc).gravarDisciplina();
			planoCurricular.adicionarDisciplina(disciplina);
			System.out.print("Deseja continuar adicionar disciplina (S/N): ");
			gravar = sc.next();

		} while (gravar.equals("S") || gravar.equals("s"));

	}

	private void selecionarDisciplinas(PlanoCurricular planoCurricular) {
		String gravar = "";
		do {
			Disciplina disciplina = new DisciplinaMenu(disciplinaDao, sc).selecionarDisciplina("SELECIONE A DISCIPLINA");
			planoCurricular.adicionarDisciplina(disciplina);
			System.out.print("Deseja continuar selecionar disciplinas (S/N):");
			gravar = sc.next();
		} while (gravar.equals("S") || gravar.equals("s"));
	}

	private void removerDisciplinaNoPlano(PlanoCurricular planoCurricular) {
		try {
			List<String> nomes = planoCurricular.getDisciplinas().stream().map(p -> p.getNome())
					.collect(Collectors.toList());
			System.out.println("********** REMOVER DISCIPLINA **********\n" + Componentes.listaFormatada(nomes)
					+ "0 - Voltar\n" + "Selecione a disciplina: ");
			int opcao = sc.nextInt();
			if (opcao != 0) {
				planoCurricular.getDisciplinas().remove(opcao);
				System.out.println("Removido com sucesso!");
			}
			init();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Ocurreu um erro! Certifique-se de selecionar as opções acima");
			removerDisciplinaNoPlano(planoCurricular);
		}
	}

	public PlanoCurricular selecionarPlano(String titulo) {
		List<String> nomes = planoCurricularDao.lista().stream().map(p -> p.getDescricao())
				.collect(Collectors.toList());
		System.out.println("********** " + titulo + " **********\n" + Componentes.listaFormatada(nomes) + "0 - Voltar\n"
				+ "Selecione o plano: ");
		int opcao = sc.nextInt();
		if (opcao != 0) {
			return planoCurricularDao.get(opcao);
		}

		return null;
	}
}
