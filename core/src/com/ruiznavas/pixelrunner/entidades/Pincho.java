package com.ruiznavas.pixelrunner.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.ruiznavas.pixelrunner.PixelRunnerGame;

public class Pincho extends B2DSprite {
	public Pincho(Body body) {
		super(body);
		
		Texture tex = PixelRunnerGame.recursos.getTextura("pinchos");
		TextureRegion[] sprites = TextureRegion.split(tex, 16, 16)[0];
		animacion.setFrames(sprites, 1/12f);
		
		ancho = sprites[0].getRegionWidth();
		alto = sprites[0].getRegionHeight();
	}
}
