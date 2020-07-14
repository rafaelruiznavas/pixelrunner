package com.ruiznavas.pixelrunner.estados;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.ruiznavas.pixelrunner.handlers.ManagerEstadoJuego;

public class Play extends EstadoJuego {
	
	private BitmapFont fuente = new BitmapFont();
	
	public Play(ManagerEstadoJuego manager) {
		super(manager);
	}

	@Override
	public void handleEntrada() {
	}

	@Override
	public void update(float dt) {
	}

	@Override
	public void render() {
		sb.setProjectionMatrix(camara.combined);
		sb.begin();
		fuente.draw(sb, "Estado Juego", 100, 100);
		sb.end();
	}

	@Override
	public void dispose() {
	}

}
