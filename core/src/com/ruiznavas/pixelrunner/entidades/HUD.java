package com.ruiznavas.pixelrunner.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ruiznavas.pixelrunner.PixelRunnerGame;
import com.ruiznavas.pixelrunner.handlers.VarsBD2;

public class HUD {
	private Jugador jugador;
	private TextureRegion [] bloques;
	
	public HUD(Jugador jugador) {
		this.jugador = jugador;
		
		Texture tex = PixelRunnerGame.recursos.getTextura("hud");
		bloques = new TextureRegion[3];
		for(int i=0;i<bloques.length;i++) {
			bloques[i] = new TextureRegion(tex, 25 + i * 10, 18, 10,10);
		}
	}
	
	public void render(SpriteBatch sb) {
		short bits = jugador.getBody().getFixtureList().first().getFilterData().maskBits;
		sb.begin();
		if((bits & VarsBD2.BIT_ROJO) != 0) {
			sb.draw(bloques[0], 40,200);
		}
		if((bits & VarsBD2.BIT_VERDE) != 0) {
			sb.draw(bloques[1], 40,200);
		}
		if((bits & VarsBD2.BIT_AZUL) != 0) {
			sb.draw(bloques[2], 40,200);
		}
		sb.end();
	}
}
