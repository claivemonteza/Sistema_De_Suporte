package eda.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import eda.controller.CursoDao;
import eda.controller.DisciplinaDao;
import eda.controller.PlanoCurricularDao;

public class Menu {

	private static CursoDao cursoDao = new CursoDao();
	private static PlanoCurricularDao planoCurricularDao = new PlanoCurricularDao();
	private static DisciplinaDao disciplinaDao = new DisciplinaDao();

	private static Scanner sc = new Scanner(System.in);

	public static void init() {
		try {
			System.out.print("********** SISTEMA DE SUPORTE **********\n" + "Menu\n" + "1 - Cursos\n"
					+ "2 - Planos Curriculares\n" + "3 - Disciplinas\n" + "Selecione a opção: ");
			int opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				new CursoMenu(cursoDao, planoCurricularDao, disciplinaDao, sc).init();
				break;
			case 2:
				new PlanoMenu(planoCurricularDao, disciplinaDao, sc).init();
				break;
			case 3:
				new DisciplinaMenu(disciplinaDao, sc).init();
				break;
			default:
				System.exit(0);
				break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Ocurreu um erro! Verifique ao inserir os dados se a espaço.");
		}
	}
}
