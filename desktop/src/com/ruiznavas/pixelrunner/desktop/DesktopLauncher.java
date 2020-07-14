package com.ruiznavas.pixelrunner.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ruiznavas.pixelrunner.PixelRunnerGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = PixelRunnerGame.TITULO;
		config.width = PixelRunnerGame.V_ANCHO * PixelRunnerGame.ESCALA;
		config.height = PixelRunnerGame.V_ALTO * PixelRunnerGame.ESCALA;
		new LwjglApplication(new PixelRunnerGame(), config);
	}
}
