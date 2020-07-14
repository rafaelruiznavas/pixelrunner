package com.ruiznavas.pixelrunner.handlers;

import java.util.Stack;

import com.ruiznavas.pixelrunner.PixelRunnerGame;
import com.ruiznavas.pixelrunner.estados.EstadoJuego;
import com.ruiznavas.pixelrunner.estados.Play;

public class ManagerEstadoJuego {
	private PixelRunnerGame juego;
	private Stack<EstadoJuego> estadosJuego;
	public static final int PLAY = 912837;
	public ManagerEstadoJuego(PixelRunnerGame juego) {
		this.juego = juego;
		estadosJuego = new Stack<EstadoJuego>();
		pushEstado(PLAY);
	}
	
	public void update(float delta) {
		estadosJuego.peek().update(delta);
	}
	
	public void render() {
		estadosJuego.peek().render();
	}

	public PixelRunnerGame getJuego() {
		return juego;
	}
	
	private EstadoJuego getEstado(int estado) {
		if(estado == PLAY) return new Play(this);
		return null;
	}

	public void setEstado(int estado) {
		popEstado();
		pushEstado(estado);
	}

	private void popEstado() {
		EstadoJuego g = estadosJuego.pop();
		g.dispose();
	}

	public void pushEstado(int estado) {
		estadosJuego.push(getEstado(estado));
	}
	
}



