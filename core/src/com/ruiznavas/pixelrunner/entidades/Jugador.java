package com.ruiznavas.pixelrunner.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.ruiznavas.pixelrunner.PixelRunnerGame;

public class Jugador extends B2DSprite {
	private int numCristales;
	private int totalCristales;

	public Jugador(Body body) {
		super(body);
		
		Texture textura = PixelRunnerGame.recursos.getTextura("heroe");
		TextureRegion[] sprites = TextureRegion.split(textura, 16,16)[0];
		
		setAnimacion(sprites, 1/12f);
		
		ancho = sprites[0].getRegionWidth();
	}
	
	public void recogerCristal() {
		numCristales++;
	}
	
	public int getNumCristales() {
		return numCristales;
	}

	public int getTotalCristales() {
		return totalCristales;
	}

	public void setTotalCristales(int totalCristales) {
		this.totalCristales = totalCristales;
	}
	
	

}
