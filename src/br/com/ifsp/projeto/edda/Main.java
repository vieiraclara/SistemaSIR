package br.com.ifsp.projeto.edda;

import java.util.List;

public class Main {
	  public static void main(String[] args) {
	       int tamanho = 200; // Defina o tamanho desejado da grade
	       AutomatoCelular automato = new AutomatoCelular(tamanho);
	       // Exibir o estado inicial se necessário
	       exibirGrade("Estado Inicial", automato.getGrade());
	       int geracoes = 100; // Defina o número desejado de iterações
	       
	       automato.simular(geracoes);
	    
    	   // Exibir o estado final após as iterações
	       exibirGrade("Estado Final", automato.getGrade());
		   System.out.println("Numero de celulas suscetiveis: "+automato.getQuantidadeSuscetiveisPorGeracao());
		   System.out.println("Numero de celulas infectadas: "+automato.getQuantidadeInfectadosPorGeracao());
		   System.out.println("Numero de celulas recuperadas: "+automato.getQuantidadeRecuperadosPorGeracao());
		   automato.exibirGrafico();
		 
	  }
	   private static void exibirGrade(String titulo, Celula[][] grade) {
	       System.out.println(titulo + ":");
	       for (Celula[] linha : grade) {
	           for (Celula celula : linha) {
	               System.out.print(celula.getEstado().toString().charAt(0) + " ");
	           }
	           System.out.println();
	       }
	       System.out.println();
	   }
	   
}