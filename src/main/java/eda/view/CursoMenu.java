package eda.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import eda.controller.CursoDao;
import eda.controller.DisciplinaDao;
import eda.controller.PlanoCurricularDao;
import eda.model.Curso;
import eda.model.PlanoCurricular;
import eda.util.Componentes;

public class CursoMenu {

	private CursoDao cursoDao;
	private PlanoCurricularDao planoCurricularDao;
	private DisciplinaDao disciplinaDao;

	private Scanner sc;

	public CursoMenu(CursoDao cursoDao, PlanoCurricularDao planoCurricularDao, DisciplinaDao disciplinaDao,
			Scanner sc) {
		this.cursoDao = cursoDao;
		this.planoCurricularDao = planoCurricularDao;
		this.disciplinaDao = disciplinaDao;
		this.sc = sc;
	}

	public void init() {
		System.out.print("********** CURSOS **********\n" + "1 - Criar curso\n" + "2 - Actualizar curso\n"
				+ "3 - Remover curso\n" + "4 - Lista de cursos\n" + "5 - Voltar\n" + "Selecione a opção: ");
		int opcao = sc.nextInt();
		switch (opcao) {
		case 1:
			criarCurso();
			break;
		case 2:
			actualizarCurso();
			break;
		case 3:
			removerCurso();
			break;
		case 4:
			listaDeCursos();
			break;
		case 5:
			Menu.init();
		default:
			Menu.init();
			break;
		}
	}

	public void criarCurso() {

		String gravar = "";
		do {
			System.out.print("********** CRIAR CURSO **********\n" + "Nome do curso: ");
			String nome = sc.next();
			Curso curso = new Curso();
			curso.setNome(nome);
			adicionaNoCurso(curso);
			this.cursoDao.gravar(curso);
			System.out.print("Deseja continuar adicionar curso (S/N): ");
			gravar = sc.next();
		} while (gravar.equals("S") || gravar.equals("s"));
		init();

	}

	public void actualizarCurso() {
		System.out.println("********** ACTUALIZAR CURSO **********\n");
		try {
			Curso curso = selecionarCurso("ACTUALIZAR CURSO");
			if (curso != null) {
				menuPlano(curso);
			}
			init();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Ocurreu um erro! Certifique-se de selecionar as opções acima");
			actualizarCurso();
		}
	}

	public void removerCurso() {
		try {
			Curso curso = selecionarCurso("REMOVER CURSO");
			if (curso != null) {
				this.cursoDao.remover(curso);
				System.out.println("Removido com sucesso!");
			}
			init();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Ocurreu um erro! Certifique-se de selecionar as opções acima");
			removerCurso();
		}
	}

	public void listaDeCursos() {
		try {
			List<String> nomes = this.cursoDao.lista().stream().map(p -> p.getNome()).collect(Collectors.toList());
			System.out.println("********** LISTA DE CURSOS **********\n" + Componentes.listaFormatada(nomes)
					+ "0 - Voltar\n" + "Selecione a disciplina: ");
			int opcao = sc.nextInt();
			if (opcao != 0) {
				Componentes.detalhes(opcao, this.cursoDao.lista());
			}
			init();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Ocurreu um erro! Certifique-se de selecionar as opções acima");
			listaDeCursos();
		}
	}

	public PlanoCurricular adicionaNoCurso(Curso curso) {
		PlanoCurricular planoSelecionado = null;
		System.out.print("********** PLANO CURRICULAR **********\n" + "1 - Criar plano\n" + "2 - Selecionar plano\n"
				+ "Selecione a opção: ");
		int opcao = sc.nextInt();
		switch (opcao) {
		case 1:
			AdicionarPlano(curso);
			break;
		case 2:
			if (!this.planoCurricularDao.lista().isEmpty()) {
				selecionarPlano(curso);
			} else {
				System.out.println("Não exite nenhum registo!!!");
				adicionaNoCurso(curso);
			}
			break;
		default:
			;
			break;
		}
		return planoSelecionado;
	}

	public void menuPlano(Curso curso) {
		System.out.println("********** Planos curriculares do curso " + curso.getNome() + " **********\n"
				+ "1 - Adicionar Plano\n" + "2 - Remover Plano\n" + "0 - Voltar\n" + "Selecione opção: ");
		int opcao = sc.nextInt();
		switch (opcao) {
		case 1:
			AdicionarPlano(curso);
			break;
		case 2:
			removerPlanoDoCurso(curso);
			break;
		default:
			;
			break;
		}
	}

	private void AdicionarPlano(Curso curso) {
		String gravar = "";
		do {
			PlanoCurricular planoCurricular = new PlanoMenu(planoCurricularDao, disciplinaDao, sc).gravarPlano();
			curso.adicionarPlanoCurricular(planoCurricular);
			System.out.print("Deseja continuar adicionar Plano Curricular (S/N):");
			gravar = sc.next();

		} while (gravar.equals("S") || gravar.equals("s"));
	}

	private void selecionarPlano(Curso curso) {
		String gravar = "";
		do {
			PlanoCurricular plano = new PlanoMenu(planoCurricularDao, disciplinaDao, sc)
					.selecionarPlano("SELECIONE O PLANO CURRICULAR");
			curso.adicionarPlanoCurricular(plano);
			System.out.print("Deseja continuar selecionar plano (S/N):");
			gravar = sc.next();
		} while (gravar.equals("S") || gravar.equals("s"));
	}

	private void removerPlanoDoCurso(Curso curso) {
		try {
			List<String> nomes = curso.getPlanosCurriculares().stream().map(p -> p.getDescricao())
					.collect(Collectors.toList());
			System.out.println("********** REMOVER PLANO **********\n" + Componentes.listaFormatada(nomes)
					+ "0 - Voltar\n" + "Selecione o plano: ");
			int opcao = sc.nextInt();
			if (opcao != 0) {
				curso.getPlanosCurriculares().remove(opcao);
				System.out.println("Removido com sucesso!");
			}
			init();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Ocurreu um erro! Certifique-se de selecionar as opções acima");
			removerPlanoDoCurso(curso);
		}
	}

	public Curso selecionarCurso(String titulo) {
		List<String> nomes = cursoDao.lista().stream().map(p -> p.getNome()).collect(Collectors.toList());
		System.out.println("********** " + titulo + " **********\n" + Componentes.listaFormatada(nomes) + "0 - Voltar\n"
				+ "Selecione o curso: ");
		int opcao = sc.nextInt();
		if (opcao != 0) {
			return cursoDao.get(opcao);
		}
		return null;
	}

}