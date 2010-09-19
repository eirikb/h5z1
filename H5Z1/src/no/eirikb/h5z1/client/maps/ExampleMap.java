package no.eirikb.h5z1.client.maps;

import java.util.ArrayList;

import no.eirikb.h5z1.client.resources.Resources;
import no.eirikb.h5z1.client.visualbody.VisualDynamic;
import no.eirikb.h5z1.client.visualbody.VisualPlayer;
import no.eirikb.h5z1.client.visualbody.VisualStatic;

import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

public class ExampleMap extends GameMap {

	public ExampleMap(World world, int width, int height) {
		super(world, width, height);
		background1Image = Resources.INSTANCE.bg2();
		cameraY = 4;
		scale = 20;
		Body b;
		visualBodies = new ArrayList<Body>();
		bodies = new ArrayList<Body>();
		PolygonDef pd = new PolygonDef();
		pd.setAsBox(12.3f, 0.2f);
		BodyDef bd = new BodyDef();
		bd.position.set(-2.3f, 0.0f);
		VisualStatic visualStatic = new VisualStatic(bd, world,
				Resources.INSTANCE.bg1(), true);
		world.createBody(visualStatic);
		visualStatic.createShape(pd);
		Body ground = visualStatic;

		pd = new PolygonDef();
		pd.setAsBox(10.0f, 0.2f);
		bd = new BodyDef();
		bd.position.set(30.0f, 0.0f);
		bd.allowSleep = true;
		visualStatic = new VisualStatic(bd, world, Resources.INSTANCE.bg1(),
				true);
		world.createBody(visualStatic);
		visualStatic.createShape(pd);
		Body ground2 = visualStatic;

		pd = new PolygonDef();
		pd.setAsBox(10.0f, 0.2f);
		bd = new BodyDef();
		bd.position.set(70.0f, 0.0f);
		bd.allowSleep = true;
		visualStatic = new VisualStatic(bd, world, Resources.INSTANCE.bg1(),
				true);
		world.createBody(visualStatic);
		visualStatic.createShape(pd);

		pd = new PolygonDef();
		pd.setAsBox(0.5f, 0.8f);
		pd.density = 1;
		pd.friction = 0;
		bd = new BodyDef();
		bd.position.set(-2, 1.5f);
		bd.fixedRotation = true;
		me = new VisualPlayer(bd, world);
		me.allowSleeping(false);
		world.createBody(me);
		me.createShape(pd);
		me.setMassFromShapes();
		visualBodies.add(me);

		Body prevBody = ground;
		RevoluteJointDef jd = new RevoluteJointDef();
		for (int i = 0; i < 10; i++) {
			pd = new PolygonDef();
			pd.setAsBox(0.5f, 0.1f);
			pd.density = 1;
			pd.friction = 0;
			bd.allowSleep = true;
			bd = new BodyDef();
			float x = 10.5f + i;
			bd.position.set(x, 0);
			VisualDynamic log = new VisualDynamic(bd, world, "log", -10, 0);
			world.createBody(log);
			log.createShape(pd);
			log.setMassFromShapes();

			Vec2 anchor = new Vec2(x - 0.5f, 0);
			jd.initialize(prevBody, log, anchor);
			world.createJoint(jd);
			prevBody = log;
			visualBodies.add(log);
		}
		Vec2 anchor = new Vec2(20.0f, 0);
		jd.initialize(prevBody, ground2, anchor);
		world.createJoint(jd);

		pd = new PolygonDef();
		pd.setAsBox(0.2f, 1.5f);
		pd.density = 1;
		pd.friction = 10;
		bd = new BodyDef();
		bd.position.set(30, 1.5f);
		bd.allowSleep = true;
		b = world.createBody(bd);
		b.createShape(pd);
		b.setMassFromShapes();
		bodies.add(b);

		pd = new PolygonDef();
		pd.setAsBox(0.2f, 1.5f);
		pd.density = 1;
		pd.friction = 10;
		bd = new BodyDef();
		bd.position.set(32, 1.5f);
		bd.allowSleep = true;
		b = world.createBody(bd);
		b.createShape(pd);
		b.setMassFromShapes();
		bodies.add(b);

		pd = new PolygonDef();
		pd.setAsBox(1.5f, 0.2f);
		pd.density = 1;
		pd.friction = 10;
		bd = new BodyDef();
		bd.position.set(31, 4);
		bd.allowSleep = true;
		b = world.createBody(bd);
		b.createShape(pd);
		b.setMassFromShapes();
		bodies.add(b);

		pd = new PolygonDef();
		pd.setAsBox(0.8f, 0.8f);
		pd.density = 1;
		pd.friction = 10;
		bd = new BodyDef();
		bd.position.set(1.5f, 1.5f);
		VisualDynamic crate = new VisualDynamic(bd, world, "crate", -16, -16);
		world.createBody(crate);
		crate.createShape(pd);
		crate.setMassFromShapes();
		visualBodies.add(crate);

		for (int i = 1; i < 10; i++) {

			pd = new PolygonDef();
			pd.setAsBox(0.05f, 0.1f);
			bd = new BodyDef();
			bd.position.set(39.95f + 2 * i, 0f);
			bd.allowSleep = true;
			b = world.createBody(bd);
			b.createShape(pd);
			b.setMassFromShapes();

			pd = new PolygonDef();
			pd.setAsBox(0.2f, 1.5f);
			pd.density = 1;
			pd.friction = 10;
			bd = new BodyDef();
			bd.position.set(40.0f + 2 * i, 1.6f);
			bd.allowSleep = true;
			b = world.createBody(bd);
			b.createShape(pd);
			b.setMassFromShapes();
			bodies.add(b);
		}
	}

}