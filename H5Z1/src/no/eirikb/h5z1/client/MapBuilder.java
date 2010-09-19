package no.eirikb.h5z1.client;

import no.eirikb.h5z1.client.visualbody.VisualCrate;
import no.eirikb.h5z1.client.visualbody.VisualPlayer;

import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

public class MapBuilder {
	private VisualPlayer me;

	public void createMap(World world) {

		PolygonDef pd = new PolygonDef();
		pd.setAsBox(10.0f, 0.2f);
		BodyDef bd = new BodyDef();
		bd.position.set(0.0f, 0.0f);
		Body b = world.createBody(bd);
		b.createShape(pd);
		Body ground = b;

		pd = new PolygonDef();
		pd.setAsBox(30.0f, 0.2f);
		bd = new BodyDef();
		bd.position.set(50.0f, 0.0f);
		bd.allowSleep = true;
		b = world.createBody(bd);
		b.createShape(pd);
		Body ground2 = b;

		pd = new PolygonDef();
		pd.setAsBox(1, 0.2f);
		pd.friction = 0;
		bd = new BodyDef();
		bd.position.set(1, 0.5f);
		bd.fixedRotation = true;
		b = world.createBody(bd);
		b.createShape(pd);
		b.setMassFromShapes();

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
			b = world.createBody(bd);
			b.createShape(pd);
			b.setMassFromShapes();

			Vec2 anchor = new Vec2(x - 0.5f, 0);
			jd.initialize(prevBody, b, anchor);
			world.createJoint(jd);
			prevBody = b;
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

		pd = new PolygonDef();
		pd.setAsBox(0.8f, 0.8f);
		pd.density = 1;
		pd.friction = 10;
		bd = new BodyDef();
		bd.position.set(1.5f, 1.5f);
		VisualCrate crate = new VisualCrate(bd, world);
		world.createBody(crate);
		crate.createShape(pd);
		crate.setMassFromShapes();

		for (int i = 0; i < 10; i++) {
			pd = new PolygonDef();
			pd.setAsBox(0.2f, 1.5f);
			pd.density = 1;
			pd.friction = 10;
			bd = new BodyDef();
			bd.position.set(40 + 2 * i, 1.5f);
			bd.allowSleep = true;
			b = world.createBody(bd);
			b.createShape(pd);
			b.setMassFromShapes();
		}
	}

	// sd = new PolygonDef();
	// sd.setAsBox(1f, 1f);
	// sd.density = 29.0f;
	// sd.friction = 0.2f;
	// sd.restitution = 0.1f;

	// RevoluteJointDef jd = new RevoluteJointDef();
	// int numPlanks = 21;
	// Body prevBody = ground;
	// long y = 0;
	// for (int i = 0; i < numPlanks; ++i) {
	// BodyDef bd = new BodyDef();
	// y += i <= numPlanks / 2 ? 1 : -1;
	// bd.position.set(-15f + 1.0f * i, y);
	// Body body = world.createBody(bd);
	// body.createShape(sd);
	// body.setMassFromShapes();
	//
	// Vec2 anchor = new Vec2(-15.0f + 1.0f * i, y);
	// jd.initialize(prevBody, body, anchor);
	// world.createJoint(jd);
	//
	// prevBody = body;
	// }
	//
	// Vec2 anchor = new Vec2(-15.0f + 1.0f * numPlanks, 0);
	// jd.initialize(prevBody, ground, anchor);
	// world.createJoint(jd);

	// PolygonDef pd2 = new PolygonDef();
	// pd2.setAsBox(1.0f, 1.0f);
	// pd2.density = 5.0f;
	// pd2.friction = 0.2f;
	// pd2.restitution = 0.1f;
	// BodyDef bd2 = new BodyDef();
	// bd2.position.set(0.0f, 10.0f);
	// Body body2 = world.createBody(bd2);
	// body2.createShape(pd2);
	// body2.setMassFromShapes();

	// CircleDef cd = new CircleDef();
	// cd.radius = 0.9f;
	// cd.density = 5.0f;
	// cd.friction = 0.2f;
	// BodyDef bd3 = new BodyDef();
	// bd3.position.set(0.0f, 12.0f);
	// Body body3 = world.createBody(bd3);
	// body3.createShape(cd);
	// cd.localPosition.set(0.0f, 1.0f);
	// body3.createShape(cd);
	// body3.setMassFromShapes();
	// }

	public VisualPlayer getMe() {
		return me;
	}
}
