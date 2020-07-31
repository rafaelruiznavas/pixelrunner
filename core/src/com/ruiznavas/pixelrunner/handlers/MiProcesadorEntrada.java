package com.ruiznavas.pixelrunner.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class MiProcesadorEntrada extends InputAdapter{
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.Z) {
			MiInput.setTecla(MiInput.BOTON1, true);
		}
		if(keycode == Keys.X) {
			MiInput.setTecla(MiInput.BOTON2, true);
		}
		
		return true;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.Z) {
			MiInput.setTecla(MiInput.BOTON1, false);
		}
		if(keycode == Keys.X) {
			MiInput.setTecla(MiInput.BOTON2, false);
		}
		return true;
	}
}
