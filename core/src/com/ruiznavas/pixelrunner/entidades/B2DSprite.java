package com.ruiznavas.pixelrunner.entidades;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ruiznavas.pixelrunner.handlers.Animacion;
import com.ruiznavas.pixelrunner.handlers.VarsBD2;

public class B2DSprite {
	protected Body body;
	protected Animacion animacion;
	protected float ancho;
	protected float alto;

	public B2DSprite(Body body) {
		this.body = body;
		animacion = new Animacion();
	}

	public void setAnimacion(TextureRegion[] reg, float retraso) {
		animacion.setFrames(reg, retraso);
		ancho = reg[0].getRegionWidth();
		alto = reg[0].getRegionHeight();
	}

	public void update(float delta) {
		animacion.update(delta);
	}

	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(animacion.getFrame(), body.getPosition().x * VarsBD2.PPM - ancho / 2,
				body.getPosition().y * VarsBD2.PPM - alto / 2);
		sb.end();
	}

	public Body getBody() {
		return body;
	}

	public Vector2 getPosicion() {
		return body.getPosition();
	}

	public float getAncho() {
		return ancho;
	}

	public float getAlto() {
		return alto;
	}

}
