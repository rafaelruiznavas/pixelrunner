package com.ruiznavas.pixelrunner.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CamaraConLimites extends OrthographicCamera{
	private float xMin;
	private float xMax;
	private float yMin;
	private float yMax;
	
	public CamaraConLimites() {
		this(0,0,0,0);
	}

	public CamaraConLimites(float xMin, float xMax, float yMin, float yMax) {
		super();
		setLimites(xMin,xMax,yMin,yMax);
	}
	
	public void setLimites(float xMin, float xMax, float yMin, float yMax) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}
	
	public void setPosicion(float x, float y) {
		setPosicion(x,y,0);
	}
}
