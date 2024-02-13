package br.com.ifsp.projeto.edda;
import java.util.List;
import java.util.Random;
public class Celula {
	 private Estado estado;
	   public Celula(Estado estado) {
	       this.estado = estado;
	   }
	   public Estado getEstado() {
	       return estado;
	   }
	   public void setEstado(Estado estado) {
	       this.estado = estado;
	   }

	}