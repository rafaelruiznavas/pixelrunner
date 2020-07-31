package com.ruiznavas.pixelrunner.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.ruiznavas.pixelrunner.PixelRunnerGame;

public class Orbe extends B2DSprite{

	public Orbe(Body body) {
		super(body);
		
		Texture tex = PixelRunnerGame.recursos.getTextura("orbe");
		TextureRegion[] sprites = TextureRegion.split(tex, 8, 8)[0];
		
		setAnimacion(sprites, 1/12f);
	}
}
