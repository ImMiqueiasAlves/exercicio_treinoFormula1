package controller;

import java.util.concurrent.Semaphore;

public class Carro extends Thread {
	private String identificacao;
	private Semaphore semaforo;
	private int menor_tempo;
	private int num_voltas = 3;
	
	public Carro(String _identificacao, Semaphore semaforo_escuderia) {
		identificacao = _identificacao;
		semaforo = semaforo_escuderia;
	}
	
	public void run() {
		try {
			semaforo.acquire();
			correr();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}
	
	private void correr() {
		for(int i=0; i < num_voltas; i++) {
			System.out.println(identificacao + " dando a " + (i+1) + "a. volta");
			// 1s - 18s
			int tempo_volta = (int) ((Math.random() * 17001) + 1000);
			
			if(tempo_volta < menor_tempo)
				menor_tempo = tempo_volta;
			System.out.println(identificacao + ": " + (i+1) + "a. volta finalizada. Tempo: " + (tempo_volta/1000) + " segundos.");
		}
	}
	
	public String getTempoString() {
		return identificacao + " menor tempo: " + menor_tempo;
	}
}
