package com.ruiznavas.pixelrunner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ruiznavas.pixelrunner.handlers.ManagerEstadoJuego;

public class PixelRunnerGame extends ApplicationAdapter {
	public static final String TITULO = "PixelRunner";
	public static final int V_ANCHO = 320;
	public static final int V_ALTO = 240;
	public static final int ESCALA = 2; 
	
	public static final float STEP = 1 / 60f;
	private float acumulado;
	
	private SpriteBatch batch;
	private OrthographicCamera camara;
	private OrthographicCamera camaraHud;
	
	private ManagerEstadoJuego managerEstados;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camara = new OrthographicCamera();
		camara.setToOrtho(false, V_ANCHO, V_ALTO);
		camaraHud = new OrthographicCamera();
		camaraHud.setToOrtho(false, V_ANCHO, V_ALTO);
		
		managerEstados = new ManagerEstadoJuego(this);
	}

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		acumulado += Gdx.graphics.getDeltaTime();
		while(acumulado >= STEP) {
			acumulado -= STEP;
			managerEstados.update(STEP);
			managerEstados.render();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public OrthographicCamera getCamara() {
		return camara;
	}

	public void setCamara(OrthographicCamera camara) {
		this.camara = camara;
	}

	public OrthographicCamera getCamaraHud() {
		return camaraHud;
	}

	public void setCamaraHud(OrthographicCamera camaraHud) {
		this.camaraHud = camaraHud;
	}
	
	
}

