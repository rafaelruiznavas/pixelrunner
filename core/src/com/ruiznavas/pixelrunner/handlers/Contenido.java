package com.ruiznavas.pixelrunner.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Contenido {
	private HashMap<String, Texture> texturas;
	
	public Contenido() {
		texturas = new HashMap<String, Texture>();
	}
	
	public void cargarTextura(String path, String clave) {
		Texture tex = new Texture(Gdx.files.internal(path));
		texturas.put(clave, tex);
	}
	
	public Texture getTextura(String clave) {
		return texturas.get(clave);
	}
	public void disposeTextura(String clave) {
		Texture tex = texturas.get(clave);
		if(tex != null)
			tex.dispose();
	}
}
