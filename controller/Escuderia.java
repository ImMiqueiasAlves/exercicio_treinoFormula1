package controller;

import java.util.concurrent.Semaphore;

public class Escuderia extends Thread {
	private int num_carros = 2;
	private Semaphore semaforo_escuderia = new Semaphore(1);
	private Semaphore semaforo_pista;
	private Carro carros[] = new Carro[num_carros];
	private String nome;
	private String string_melhor_tempo;
	
	public Escuderia(String _nome, Semaphore _semaforo_pista) {
		nome = _nome;
		semaforo_pista = _semaforo_pista;
		criarCarros();
	}

	public void run() {
		try {
			semaforo_pista.acquire();
			for(int i=0; i < num_carros; i++)
				carros[i].start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo_pista.release();
		}
		salvarTempos();
	}
	
	private void salvarTempos() {
		for(int i=0; i < num_carros; i++)
			string_melhor_tempo += carros[i].getTempoString() + "\n";
	}
	
	private void criarCarros() {
		for(int i=0;i < num_carros; i++)
			carros[i] = new Carro(nome+" Carro: " + (i+1), semaforo_escuderia);
	}
	
}
