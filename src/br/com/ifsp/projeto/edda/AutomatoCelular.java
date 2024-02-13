
package br.com.ifsp.projeto.edda;
 //import org.knowm.xchart.*;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
//Declaração dos atributos do Automato Celular
public class AutomatoCelular {
    private Celula[][] grade;
    private Grafo grafo; // atributo que utilizaremos pra chamar métodos do Grafo (guarda as regras de transição)
    private int tamanho;//Refere-se ao tamanho da matriz (grade) formada por células
    private Random random;// Objeto para gerar os números aleatórios que utilizaremos para preencher a matriz e determinar a mudança de estado de uma célula
    private List<Celula[][]> gradesAnteriores;  // Lista para armazenar informações da grade anterior
/*Listas para guardar a quantidade de celulas em cada estado por geração para 
simplificar a construção do Gráfico*/
    private List<Integer> quantidadeSuscetiveisPorGeracao;
    private List<Integer> quantidadeInfectadosPorGeracao;
    private List<Integer> quantidadeRecuperadosPorGeracao;
//Método contrutor da classe (iniciação dos atributos)
 public AutomatoCelular(int tamanho) {
        this.tamanho = tamanho;
        this.grade = new Celula[tamanho][tamanho];
        this.grafo = new Grafo();
        this.random = new Random();
        this.gradesAnteriores = new ArrayList<>();
        this.quantidadeSuscetiveisPorGeracao = new ArrayList<>();
        this.quantidadeInfectadosPorGeracao = new ArrayList<>();
        this.quantidadeRecuperadosPorGeracao = new ArrayList<>();
        /*Uso de uma lista de posições: cada posição se torna um elemento 
         * e assim conseguimos as embaralhar apesar de termos um número fixo 
         * de celulas suscetiveis e infectadas(na primeira geração) */
        List<int[]> posicoes = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                posicoes.add(new int[]{i, j});
            }
        }Collections.shuffle(posicoes);// chamada para a biblioteca embaralhar as posições
        int numeroSuscetiveis = (int) (0.995 * tamanho * tamanho);  // Número exato de células suscetíveis 
     // Loop For para percorrer e preencher a grade com celulas nos estado atual= Suscetivel ou estado atual= Infectado
        for (int k = 0; k < tamanho * tamanho; k++) {
            int i = posicoes.get(k)[0];
            int j = posicoes.get(k)[1];

                        if (numeroSuscetiveis > 0) {// preencher a grade até que numeroSuscetiveis=0
                            grade[i][j] = new Celula(Estado.SUSCETIVEL);
                            numeroSuscetiveis--;// 
                        } else { // quando não numeroSuscetiveis=0, o looping passa a preencher as celulas com estado atual= infectado
                            grade[i][j] = new Celula(Estado.INFECTADO);
                        }
                    }
       contarCelulasPorEstado();// contagem das celulas por estado para preencher as listas que serão representadas pelo gráfico
        gradesAnteriores.add(cloneGrade(grade));
                }
[02:33, 30/11/2023] Kelly Bobona: MÉTODO CONTAR CELULAS POR ESTADO

 // Método para contagem das celulas por estado---- utilizar listas para construção do gráfico
 public void contarCelulasPorEstado() {
     int suscetiveis = 0;
     int infectados = 0;
     int recuperados = 0;

     for (int i = 0; i < tamanho; i++) {
         for (int j = 0; j < tamanho; j++) {
             Estado estado = grade[i][j].getEstado();
             switch (estado) {
                 case SUSCETIVEL:
                     suscetiveis++;
                     break;
                 case INFECTADO:
                     infectados++;
                     break;
                 case RECUPERADO:
                     recuperados++;
                     break;
             }
         }
     }

     quantidadeSuscetiveisPorGeracao.add(suscetiveis);
     quantidadeInfectadosPorGeracao.add(infectados);
     quantidadeRecuperadosPorGeracao.add(recuperados);

 }