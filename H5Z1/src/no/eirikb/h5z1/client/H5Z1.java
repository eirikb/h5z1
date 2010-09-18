package no.eirikb.h5z1.client;

import gwt.g2d.client.util.FpsTimer;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class H5Z1 implements EntryPoint {

	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	private World world;
	private AABB aabb;

	public void onModuleLoad() {

		aabb = new AABB();
		aabb.lowerBound = new Vec2(-200.0f, -100.0f);
		aabb.upperBound = new Vec2(200.0f, 200.0f);
		Vec2 gravity = new Vec2(0.0f, -10.0f);
		boolean doSleep = false;
		world = new World(aabb, gravity, doSleep);
		
		final Label label = new Label();

		final TestSettings settings = new TestSettings();
		createBridge();

		final float timeStep = settings.hz > 0.0f ? 1.0f / settings.hz : 0.0f;
		FpsTimer fpsTimer = new FpsTimer() {

			@Override
			public void update() {
				world.step(timeStep, settings.iterationCount);
				label.setText("Thank you f1sh, FPS: " + getFps());
			}
		};

		RootPanel.get().add(label);
		fpsTimer.start();
	}

	private void createBridge() {
		Body ground = null;
		{
			PolygonDef sd = new PolygonDef();
			sd.setAsBox(50.0f, 0.2f);

			BodyDef bd = new BodyDef();
			bd.position.set(0.0f, 0.0f);
			ground = world.createBody(bd);
			ground.createShape(sd);
		}

		{
			PolygonDef sd = new PolygonDef();
			sd.setAsBox(0.65f, 0.125f);
			sd.density = 20.0f;
			sd.friction = 0.2f;

			RevoluteJointDef jd = new RevoluteJointDef();
			int numPlanks = 30;

			Body prevBody = ground;
			for (int i = 0; i < numPlanks; ++i) {
				BodyDef bd = new BodyDef();
				bd.position.set(-14.5f + 1.0f * i, 5.0f);
				Body body = world.createBody(bd);
				body.createShape(sd);
				body.setMassFromShapes();

				Vec2 anchor = new Vec2(-15.0f + 1.0f * i, 5.0f);
				jd.initialize(prevBody, body, anchor);
				world.createJoint(jd);

				prevBody = body;
			}

			Vec2 anchor = new Vec2(-15.0f + 1.0f * numPlanks, 5.0f);
			jd.initialize(prevBody, ground, anchor);
			world.createJoint(jd);

			PolygonDef pd2 = new PolygonDef();
			pd2.setAsBox(1.0f, 1.0f);
			pd2.density = 5.0f;
			pd2.friction = 0.2f;
			pd2.restitution = 0.1f;
			BodyDef bd2 = new BodyDef();
			bd2.position.set(0.0f, 10.0f);
			Body body2 = world.createBody(bd2);
			body2.createShape(pd2);
			body2.setMassFromShapes();

			CircleDef cd = new CircleDef();
			cd.radius = 0.9f;
			cd.density = 5.0f;
			cd.friction = 0.2f;
			BodyDef bd3 = new BodyDef();
			bd3.position.set(0.0f, 12.0f);
			Body body3 = world.createBody(bd3);
			body3.createShape(cd);
			cd.localPosition.set(0.0f, 1.0f);
			body3.createShape(cd);
			body3.setMassFromShapes();
		}
	}
}
