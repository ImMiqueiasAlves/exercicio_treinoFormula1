package controller;

import java.util.concurrent.Semaphore;

public class Pista {
	private int qtd_escuderias = 2;
	private String nomes_escuderias[] = {"Red Bull", "Ferrari", "McLaren", "Aston Martin",
										 "Alpine", "Mercedes", "AlphaTauri"};
	private Semaphore semaforo_pista = new Semaphore(5);
	private Escuderia escuderias[] = new Escuderia[qtd_escuderias];
	
	public Pista() {
		main();
	}
	
	private void main() {
		for(int i=0; i < qtd_escuderias; i++) {
			escuderias[i] = new Escuderia(nomes_escuderias[i], semaforo_pista);
			escuderias[i].start();
		}
	}
	
}
