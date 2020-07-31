package com.ruiznavas.pixelrunner.handlers;

public class MiInput {
	public static boolean[] teclas;
	public static boolean[] teclasPrevio;
	
	public static final int NUM_TECLAS = 2;
	public static final int BOTON1 = 0;
	public static final int BOTON2 = 1;
	
	static {
		teclas = new boolean[NUM_TECLAS];
		teclasPrevio = new boolean[NUM_TECLAS];
	}
	
	public static void update() {
		for(int i=0;i<NUM_TECLAS;i++) {
			teclasPrevio[i] = teclas[i];
		}
	}
	
	public static boolean estaPulsada(int i) {
		return teclas[i];
	}
	public static boolean seHaPulsado(int i) {
		return teclas[i] && !teclasPrevio[i];
	}

	public static void setTecla(int boton, boolean valor) {
		teclas[boton] = valor;
	}
}
