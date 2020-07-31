package com.ruiznavas.pixelrunner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ruiznavas.pixelrunner.handlers.Contenido;
import com.ruiznavas.pixelrunner.handlers.ManagerEstadoJuego;
import com.ruiznavas.pixelrunner.handlers.MiInput;
import com.ruiznavas.pixelrunner.handlers.MiProcesadorEntrada;

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
	public static Contenido recursos;
	
	@Override
	public void create () {
		
		Gdx.input.setInputProcessor(new MiProcesadorEntrada());
		recursos = new Contenido();
		recursos.cargarTextura("herochar.png", "heroe");
		recursos.cargarTextura("orb.png", "orbe");
		recursos.cargarTextura("hud.png", "hud");
		
		
		batch = new SpriteBatch();
		camara = new OrthographicCamera();
		camara.setToOrtho(false, V_ANCHO, V_ALTO);
		camaraHud = new OrthographicCamera();
		camaraHud.setToOrtho(false, V_ANCHO, V_ALTO);
		
		managerEstados = new ManagerEstadoJuego(this);
	}

	@Override
	public void render () {
		acumulado += Gdx.graphics.getDeltaTime();
		while(acumulado >= STEP) {
			acumulado -= STEP;
			managerEstados.update(STEP);
			managerEstados.render();
			MiInput.update();
		}
		
		batch.setProjectionMatrix(camaraHud.combined);
		batch.begin();
		batch.draw(recursos.getTextura("heroe"), 0, 0);
		batch.end();
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

