package com.ruiznavas.pixelrunner.estados;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ruiznavas.pixelrunner.PixelRunnerGame;
import com.ruiznavas.pixelrunner.handlers.ManagerEstadoJuego;

public abstract class EstadoJuego {
	protected ManagerEstadoJuego managerEstadoJuego;
	protected PixelRunnerGame juego;
	
	protected SpriteBatch sb;
	protected OrthographicCamera camara;
	protected OrthographicCamera camaraHud;
	
	protected EstadoJuego(ManagerEstadoJuego manager) {
		this.managerEstadoJuego = manager;
		juego = manager.getJuego();
		sb = juego.getBatch();
		camara = juego.getCamara();
		camaraHud = juego.getCamaraHud();
	}
	
	public abstract void handleEntrada();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
}

