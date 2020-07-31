package com.ruiznavas.pixelrunner.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animacion {
	private TextureRegion [] frames;
	private float tiempo;
	private float retraso;
	private int frameActual;
	private int vecesEjecutado;
	
	public Animacion() {
		
	}
	
	public Animacion(TextureRegion [] frames) {
		this(frames, 1/12f);
	}
	
	public Animacion(TextureRegion [] frames, float retraso) {
		
	}
	
	public void setFrames(TextureRegion [] frames, float retraso) {
		this.frames = frames;
		this.retraso = retraso;
		tiempo = 0;
		frameActual = 0;
		vecesEjecutado = 0;
	}

	public void update(float dt) {
		if(retraso <= 0) return;
		tiempo += dt;
		while(tiempo >= retraso) {
			paso();
		}
	}
	
	private void paso() {
		tiempo -= retraso;
		frameActual++;
		if(frameActual == frames.length) {
			frameActual = 0;
			vecesEjecutado++;
		}
	}
	
	public TextureRegion getFrame() {
		return frames[frameActual];
	}

	public int getVecesEjecutado() {
		return vecesEjecutado;
	}
	
	
}




