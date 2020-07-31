package com.ruiznavas.pixelrunner.estados;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.ruiznavas.pixelrunner.PixelRunnerGame;
import com.ruiznavas.pixelrunner.entidades.HUD;
import com.ruiznavas.pixelrunner.entidades.Jugador;
import com.ruiznavas.pixelrunner.entidades.Orbe;
import com.ruiznavas.pixelrunner.handlers.ManagerEstadoJuego;
import com.ruiznavas.pixelrunner.handlers.MiInput;
import com.ruiznavas.pixelrunner.handlers.MiListenerContactos;
import com.ruiznavas.pixelrunner.handlers.VarsBD2;

public class Play extends EstadoJuego {
	
	private boolean debug = false;
	
	private World boxWorld;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera b2dCamara;

	private MiListenerContactos listenerContacto;
	private TiledMap tileMap;
	private OrthogonalTiledMapRenderer tmr;
	private float tamTile;
	
	private HUD hud;
	private Jugador jugador;
	private Array<Orbe> orbes;
	
	public Play(ManagerEstadoJuego manager) {
		super(manager);
		
		// Configuramos el mundo de box2d
		listenerContacto = new MiListenerContactos();
		boxWorld = new World(new Vector2(0,-9.8f), true);
		boxWorld.setContactListener(listenerContacto);
		debugRenderer = new Box2DDebugRenderer();
		
		// Creamos al jugador
		crearJugador();
		
		// Creamos los tiles
		crearTiles();
		
		// Creamos los orbes
		crearOrbes();
		
		// COnfiguramos la cmaara 
		b2dCamara = new OrthographicCamera();
		b2dCamara.setToOrtho(false, PixelRunnerGame.V_ANCHO/VarsBD2.PPM, PixelRunnerGame.V_ALTO/VarsBD2.PPM);
		
		// Configuramos el HUD
		hud = new HUD(jugador);
	}

	private void crearTiles() {
		// Cargamos el mapa
		tileMap = new TmxMapLoader().load("maps/mapa01.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		//TiledMapTileLayer capa = (TiledMapTileLayer)tileMap.getLayers().get("rojo");
		tamTile = (int)tileMap.getProperties().get("tilewidth");
		TiledMapTileLayer capa;
		capa = (TiledMapTileLayer)tileMap.getLayers().get("rojo");
		crearCapa(capa, VarsBD2.BIT_ROJO);
		capa = (TiledMapTileLayer)tileMap.getLayers().get("verde");
		crearCapa(capa, VarsBD2.BIT_VERDE);
		capa = (TiledMapTileLayer)tileMap.getLayers().get("azul");
		crearCapa(capa, VarsBD2.BIT_AZUL);
		
	}
	
	private void crearCapa(TiledMapTileLayer capa, short bits) {
		BodyDef bDef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		// Recorremos todas las celdas de la capa
		for(int fila = 0;fila<capa.getHeight();fila++) {
			for(int col = 0;col < capa.getWidth();col++) {
				// Obtenemos la celda
				Cell celda = capa.getCell(col, fila);
				
				// comprobamos is la celda existe
				if(celda == null) continue;
				if(celda.getTile() == null) continue;
				// Creamos un cuerpo y un fixture para cada celda
				bDef.type = BodyType.StaticBody;
				bDef.position.set(
						(col + 0.5f) * tamTile / VarsBD2.PPM,
						(fila + 0.5f)* tamTile / VarsBD2.PPM);
				ChainShape cs = new ChainShape();
				Vector2[] v = new Vector2[3];
				v[0] = new Vector2(-tamTile / 2 /VarsBD2.PPM, -tamTile/2/VarsBD2.PPM);
				v[1] = new Vector2(-tamTile / 2 /VarsBD2.PPM,  tamTile/2/VarsBD2.PPM);
				v[2] = new Vector2( tamTile / 2 /VarsBD2.PPM,  tamTile/2/VarsBD2.PPM);
				cs.createChain(v);
				fdef.friction = 0;
				fdef.shape = cs;
				fdef.filter.categoryBits = bits;
				fdef.filter.maskBits = VarsBD2.BIT_JUGADOR;
				fdef.isSensor = false;
				boxWorld.createBody(bDef).createFixture(fdef);
				cs.dispose();
			}
		}		
	}

	private void crearJugador() {
		BodyDef bDef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		bDef.position.set(50/VarsBD2.PPM,200/VarsBD2.PPM);
		bDef.type = BodyType.DynamicBody;
		bDef.fixedRotation = true;
		bDef.linearVelocity.set(.5f,0);
		
		
		Body bodyJugador = boxWorld.createBody(bDef);
		shape.setAsBox(5/VarsBD2.PPM, 5/VarsBD2.PPM);
		
		fdef.shape = shape;
		fdef.density = 1;
		fdef.friction = 0;
		fdef.filter.categoryBits = VarsBD2.BIT_JUGADOR;
		fdef.filter.maskBits = VarsBD2.BIT_ROJO | VarsBD2.BIT_ORBE ;
		bodyJugador.createFixture(fdef).setUserData("jugador");
		shape.dispose();
		
		// Creamos el sensor de los pies
		shape = new PolygonShape();
		shape.setAsBox(2/VarsBD2.PPM, 2/VarsBD2.PPM, new Vector2(0,-5/VarsBD2.PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = VarsBD2.BIT_JUGADOR;
		fdef.filter.maskBits = VarsBD2.BIT_ROJO;
		fdef.isSensor = true;
		bodyJugador.createFixture(fdef).setUserData("pie");
		shape.dispose();
		
		// Creamos el jugador
		jugador = new Jugador(bodyJugador);		
		bodyJugador.setUserData(jugador);
		
		// Configuramos la masa del jugador a 1
		MassData md = bodyJugador.getMassData();
		md.mass = 1;
		bodyJugador.setMassData(md);
	}
	
	private void crearOrbes() {
		orbes = new Array<Orbe>();
		MapLayer capa = tileMap.getLayers().get("orbes");
		BodyDef bDef = new BodyDef();
		FixtureDef fDef = new FixtureDef();
		
		for(MapObject mo : capa.getObjects()) {
			bDef.type = BodyType.StaticBody;
			float x = (float)mo.getProperties().get("x") / VarsBD2.PPM;
			float y = (float)mo.getProperties().get("y") / VarsBD2.PPM;
			bDef.position.set(x,y);
			
			CircleShape cShape = new CircleShape();
			cShape.setRadius(4 / VarsBD2.PPM);
			
			fDef.shape = cShape;
			fDef.isSensor = true;
			fDef.filter.categoryBits = VarsBD2.BIT_ORBE;
			fDef.filter.maskBits = VarsBD2.BIT_JUGADOR;
			
			Body cuerpo = boxWorld.createBody(bDef);
			cuerpo.createFixture(fDef).setUserData("orbe");;
			Orbe o = new Orbe(cuerpo);
			orbes.add(o);
			
			cuerpo.setUserData(o);			
		}
	}

	@Override
	public void handleEntrada() {
		// Salto del jugador
		if(MiInput.seHaPulsado(MiInput.BOTON1)) {
			if(listenerContacto.estaJugadorEnSuelo()) {
				jugador.getBody().applyForceToCenter(0, 200, true);
			}
		}
		
		// Cambiamos color del bloque
		if(MiInput.seHaPulsado(MiInput.BOTON2)) {
			cambiarBloques();
		}
		
		// Para dispositivios moviles
		/*if(MiInput.seHaPulsado()) {
			if((MiInput.x < Gdx.graphics.getWidth()/2)) {
				cambiarBloques();
			}else {
				jugador.getBody().applyForceToCenter(0, 200, true);
			}
		}*/
	}

	private void cambiarBloques() {
		Filter filtro = jugador.getBody().getFixtureList().first().getFilterData();
		short bits = filtro.maskBits;
		// Cambiamos al siguiente color
		if((bits & VarsBD2.BIT_ROJO)!=0) {
			bits &= ~VarsBD2.BIT_ROJO;
			bits |= VarsBD2.BIT_VERDE;
		} else if((bits & VarsBD2.BIT_VERDE)!=0) {
			bits &= ~VarsBD2.BIT_VERDE;
			bits |= VarsBD2.BIT_AZUL;
		} else if((bits & VarsBD2.BIT_AZUL)!=0) {
			bits &= ~VarsBD2.BIT_AZUL;
			bits |= VarsBD2.BIT_ROJO;
		}
		// Configuramlos la nueva mascara de bits
		filtro.maskBits = bits;
		jugador.getBody().getFixtureList().first().setFilterData(filtro);
		
		// configuramls el nuevo mascara de bits para el pie
		filtro = jugador.getBody().getFixtureList().get(1).getFilterData();
		bits &= ~VarsBD2.BIT_ORBE;
		filtro.maskBits = bits;
		jugador.getBody().getFixtureList().get(1).setFilterData(filtro);
	}

	@Override
	public void update(float dt) {
		// comprobamos la entrada
		handleEntrada();
		
		// Actualizamos box2d
		boxWorld.step(dt, 6, 2);
		
		// Eliminamos los orbes
		Array<Body> cuerpos = listenerContacto.getCuerposParaEliminar();
		for(Body cuerpo : cuerpos) {
			orbes.removeValue((Orbe)cuerpo.getUserData(), true);
			boxWorld.destroyBody(cuerpo);
			jugador.recogerCristal();
		}
		cuerpos.clear();
		
		jugador.update(dt);
		for(Orbe orbe : orbes) {
			orbe.update(dt);
		}
	}

	@Override
	public void render() {
		// Limpiamos la pantalla
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// configuramos la camara para que siga al jugador
		camara.position.set(jugador.getPosicion().x * VarsBD2.PPM + PixelRunnerGame.V_ANCHO / 4,
				PixelRunnerGame.V_ALTO / 2, 0);
		camara.update();
		
		// dibujamos el mapa de tiles
		tmr.setView(camara);
		tmr.render();
		
		for(Orbe orbe : orbes) {
			orbe.render(sb);
		}
		
		// Dibujamos al jugador
		sb.setProjectionMatrix(camara.combined);
		jugador.render(sb);
		
		// Dibujamos el hud
		sb.setProjectionMatrix(camaraHud.combined);
		hud.render(sb);

		
		if(debug)
			debugRenderer.render(boxWorld, b2dCamara.combined);
	}

	@Override
	public void dispose() {
	}

}


