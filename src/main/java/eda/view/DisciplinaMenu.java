package eda.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import eda.controller.DisciplinaDao;
import eda.model.Disciplina;
import eda.util.Componentes;

public class DisciplinaMenu {
	private Scanner sc;
	private DisciplinaDao disciplinaDao;

	public DisciplinaMenu(DisciplinaDao disciplinaDao, Scanner sc) {
		this.disciplinaDao = disciplinaDao;
		this.sc = sc;
	}

	public void init() {
		System.out.print("********** DISCIPLINA **********\n" + "1 - Criar disciplina\n" + "2 - Actualizar disciplina\n"
				+ "3 - Remover disciplina\n" + "4 - Lista de disciplinas\n" + "5 - Voltar\n" + "Selecione a opção: ");
		int opcao = sc.nextInt();
		switch (opcao) {
		case 1:
			criarDisciplina();
			break;
		case 2:
			actualizarDisciplina();
			break;
		case 3:
			removerDisciplina();
			break;
		case 4:
			listaDeDisciplinas();
			break;
		case 5:
			Menu.init();
			break;
		default:
			System.exit(0);
			break;
		}
	}

	public void criarDisciplina() {
		gravarDisciplina();
		init();
	}

	public Disciplina selecionarDisciplina(String titulo) {
		List<String> nomes = this.disciplinaDao.lista().stream().map(p -> p.getNome()).collect(Collectors.toList());
		System.out.println("********** " + titulo + " **********\n" + Componentes.listaFormatada(nomes) + "0 - Voltar\n"
				+ "Selecione uma disciplina: ");
		int opcao = sc.nextInt();
		if (opcao != 0) {
			return this.disciplinaDao.get(opcao);
		}
		return null;
	}

	public Disciplina gravarDisciplina() {
		System.out.print("********** CRIAR DISCIPLINA **********\n" + "Nome: ");
		String nome = sc.next();

		if (this.disciplinaDao.pesquisar(nome) != null) {
			System.out.println("Já existe uma disciplina com este nome");
		} else {
			Disciplina disciplina = new Disciplina();
			disciplina.setNome(nome);
			adicionarTopico(disciplina);
			this.disciplinaDao.gravar(disciplina);
			return disciplina;
		}
		return null;
	}

	private void actualizarDisciplina() {
		try {
			if (!this.disciplinaDao.lista().isEmpty()) {
				Disciplina disciplina = selecionarDisciplina("ACTUALIZAR DISCIPLINA");
				if (disciplina != null) {
					menuTopito(disciplina);
				}
			} else {
				System.out.println("Não exite nenhum registo!!!");
			}
			init();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Ocurreu um erro! Certifique-se de selecionar as opções acima");
			actualizarDisciplina();
		}
	}

	private void removerDisciplina() {
		try {
			Disciplina disciplina = selecionarDisciplina("REMOVER DISCIPLINA");
			if (disciplina != null) {
				this.disciplinaDao.remover(disciplina);
				System.out.println("Removido com sucesso!");
			}
			init();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Ocurreu um erro! Certifique-se de selecionar as opções acima");
			removerDisciplina();
		}
	}

	public void menuTopito(Disciplina disciplina) {
		System.out.println("********** Topicos de " + disciplina.getNome() + " **********\n" + "1 - Adicionar topico\n"
				+ "2 - Remover topico\n" + "0 - Voltar\n" + "Selecione opção: ");
		int opcao = sc.nextInt();
		switch (opcao) {
		case 1:
			adicionarTopico(disciplina);
			break;
		case 2:
			removerTopico(disciplina);
			break;
		default:
			actualizarDisciplina();
			break;
		}
	}

	public void adicionarTopico(Disciplina disciplina) {
		String opcao;
		do {
			System.out.print("Topico: ");
			String topico = sc.next();
			disciplina.adicionarTopico(topico);
			System.out.print("Deseja continuar adicionar topicos (S/N)? ");
			opcao = sc.next();
		} while (opcao.equals("S") || opcao.equals("s"));

	}

	public void removerTopico(Disciplina disciplina) {
		int opcao;
		String desejaRemover = "";
		do {
			System.out.print("Remover topico\n" + Componentes.listaFormatada(disciplina.getTopicos()) + "0 - voltar\n"
					+ "Selecione o topico: ");
			opcao = sc.nextInt();
			if (opcao != 0) {
				disciplina.removerTopico(opcao);
				System.out.print("Deseja remover topico (S/N): ");
				desejaRemover = sc.next();
			}
		} while (desejaRemover.equals("S") || desejaRemover.equals("s"));
		actualizarDisciplina();
	}

	private void listaDeDisciplinas() {
		try {
			List<String> nomes = this.disciplinaDao.lista().stream().map(p -> p.getNome()).collect(Collectors.toList());
			System.out.println("********** LISTA DE DISCIPLINAS **********\n" + Componentes.listaFormatada(nomes)
					+ "0 - Voltar\n" + "Selecione uma disciplina para visualizar os detalhes: ");
			int opcao = sc.nextInt();
			if (opcao != 0) {
				Componentes.detalhes(opcao, this.disciplinaDao.lista());
			}
			init();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Ocurreu um erro! Certifique-se de selecionar as opções acima");
			listaDeDisciplinas();
		}
	}
}
