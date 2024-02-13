package br.com.ifsp.projeto.edda;
//As arestas sao ponderadas (tem peso)--- o peso == probabilidade
public class Aresta {
 private Estado destino;// do tipo Estado porque os vertices reprsentam cada estado da celula
 private double peso;

 public Aresta(Estado destino, double peso) {
     this.destino = destino; 
     this.peso = peso;
 }
 public void pesoEstado() {
 }

 public Estado getDestino() {
     return destino;
 }

 public double getPeso() {
     return peso;
 }

 public void setDestino(Estado destino) {
     this.destino = destino;
 }

 public void setPeso(double peso) {
     this.peso = peso;
 }

 @Override
 public String toString() {
     return destino + " (" + peso + ")";
 }
}