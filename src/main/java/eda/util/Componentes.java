package eda.util;

import java.util.List;

public class Componentes {

	public static String listaFormatada(List<String> dados) {
		int i = 1;
		String listaFormatada = "";
		for (String d : dados) {
			listaFormatada += String.valueOf(i).concat(" - ").concat(d + "\n");
			i++;
		}
		return listaFormatada;
	}
	
	public static void detalhes(int index, List<?> dados) {
		Object obj = dados.get(index - 1);
		System.out.println(obj.toString());
	}
}
