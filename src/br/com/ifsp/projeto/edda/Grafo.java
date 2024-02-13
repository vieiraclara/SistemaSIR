package br.com.ifsp.projeto.edda;
import java.util.List;
import java.util.*;
// ********Classe para a Implementação das probabilidades de transição de Estado **********
// vertices- representação dos 3 estados do modelo SIR (suscetivel, infectado, recuperado)
// Arestas ponderadas - reprsentção da probabilidade de mudança de estado
class Grafo{
    private double[][] transicoes; // Criação de uma matriz para armazenar os valores da mudança de um estado para outro da célula
   // Uso do Map para indicar a ordem de probabilidade de acordo com o a matriz de transição
    private Map<Estado, List<Aresta>> vertices = new HashMap<>();

// Iniciação dos atributos da classe Grafo (determinação dos valores de cada peso das arestas, fornecendo a lógica de transmissão do modelo
    public Grafo() {
    	super ();      
    	// As transicoes[X][Y] representa a probabilidade de transição de X para Y
        this.transicoes = new double[Estado.values().length][Estado.values().length];
        this.transicoes[Estado.SUSCETIVEL.ordinal()][Estado.RECUPERADO.ordinal()] = 0.03;
        this.transicoes[Estado.INFECTADO.ordinal()][Estado.RECUPERADO.ordinal()] = 0.6;
        this.transicoes[Estado.INFECTADO.ordinal()][Estado.SUSCETIVEL.ordinal()] = 0.01;
        this.transicoes[Estado.INFECTADO.ordinal()][Estado.INFECTADO.ordinal()] = 1 - this.transicoes[Estado.INFECTADO.ordinal()][Estado.RECUPERADO.ordinal()];
        this.transicoes[Estado.RECUPERADO.ordinal()][Estado.SUSCETIVEL.ordinal()] = 0.1;
        this.transicoes[Estado.RECUPERADO.ordinal()][Estado.RECUPERADO.ordinal()] = 1 - this.transicoes[Estado.RECUPERADO.ordinal()][Estado.SUSCETIVEL.ordinal()];
        this.transicoes[Estado.SUSCETIVEL.ordinal()][Estado.SUSCETIVEL.ordinal()] = 1 - this.transicoes[Estado.RECUPERADO.ordinal()][Estado.SUSCETIVEL.ordinal()];
 }
    // Lógica para calcular a probabilidade de transição de SUSCETIVEL para INFECTADO
    public double getProbabilidadeTransicao(Estado estadoAtual, Estado estadoDestino, int vizinhosInfectados) {
        if (estadoAtual == Estado.SUSCETIVEL && estadoDestino == Estado.INFECTADO) {
            return 1 - Math.pow(2.71, -(vizinhosInfectados * 0.1));
        } else {
            // Se não for a transição de SUSCETIVEL para INFECTADO, retorna a probabilidade definida anteriormente
            return transicoes[estadoAtual.ordinal()][estadoDestino.ordinal()];
        }
    }
    public double getProbabilidadeTransicao(Estado estadoAtual, Estado estadoDestino) {
        return transicoes[estadoAtual.ordinal()][estadoDestino.ordinal()];
    }
    //métodos para a construção do nosso grafo (adição das arestas e dos vertices)
    public void addVertice(Estado vertice) {
        vertices.putIfAbsent(vertice, new ArrayList<>());
    } 
    public void addAresta(Estado origem, Estado destino, double peso) {
        vertices.get(origem).add(new Aresta(destino, peso));
    }
    // método para imprimir o nosso grafo
    public void imprimirGrafo() {
        for (Estado v : vertices.keySet()) {
            System.out.println(v + " -> " + vertices.get(v));
        }
    } 
}