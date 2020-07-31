package com.ruiznavas.pixelrunner.handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

public class MiListenerContactos implements ContactListener{

	private int numPiesContacto;
	private Array<Body> cuerposParaEliminar;
	
	public MiListenerContactos() {
		cuerposParaEliminar = new Array<Body>();
	}
	
	// Se llama cuando dos fixtures comienzan a colisionar
	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa.getUserData() != null && fa.getUserData().equals("pie")) {
			numPiesContacto++;
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("pie")) {
			numPiesContacto++;
		}
	}

	// Se llama cuando dos fixtures ya no colisionan
	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa == null || fb==null) return;
		
		if(fa.getUserData() != null && fa.getUserData().equals("pie")) {
			numPiesContacto--;
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("pie")) {
			numPiesContacto--;
		}
		
		if(fa.getUserData() != null && fa.getUserData().equals("orbe")) {
			cuerposParaEliminar.add(fa.getBody());
		}
		if(fb.getUserData() != null && fb.getUserData().equals("orbe")) {
			cuerposParaEliminar.add(fb.getBody());
		}
	}
	
	public boolean estaJugadorEnSuelo() {
		return numPiesContacto > 0;
	}

	// Detecta la colsion
	// presolve
	// Maneja la colision
	// postsolve
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

	public Array<Body> getCuerposParaEliminar() {
		return cuerposParaEliminar;
	}
	
	

}
