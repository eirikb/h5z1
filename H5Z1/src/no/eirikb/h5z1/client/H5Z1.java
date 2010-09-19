package no.eirikb.h5z1.client;

import gwt.g2d.client.util.FpsTimer;

import java.util.Map;

import no.eirikb.h5z1.client.keyhack.KeyHack;
import no.eirikb.h5z1.client.keyhack.KeyHackCallback;
import no.eirikb.h5z1.client.resources.ResourcesContainer;
import no.eirikb.h5z1.client.resources.ResourcesContainer.ListenComplete;
import no.eirikb.h5z1.client.visualbody.VisualPlayer;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class H5Z1 implements EntryPoint, KeyHackCallback {

	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	private World world;
	private AABB aabb;
	private GameMap gameMap;
	private KeyHack keyHack;
	private MapBuilder mapBuilder;
	private float way = 0;
	private boolean jump = false;
	private FpsTimer fpsTimer;
	private VisualPlayer me;

	public void onModuleLoad() {

		ResourcesContainer.init(new ListenComplete() {

			@Override
			public void onComplete(Map<String, ImageElement> images) {
				start();
			}
		});
	}

	private void start() {

		aabb = new AABB();
		aabb.lowerBound = new Vec2(-200.0f, -100.0f);
		aabb.upperBound = new Vec2(200.0f, 200.0f);
		Vec2 gravity = new Vec2(0.0f, -50.0f);
		boolean doSleep = false;
		world = new World(aabb, gravity, doSleep);

		final TestSettings settings = new TestSettings();
		mapBuilder = new MapBuilder();
		mapBuilder.createMap(world);

		gameMap = new GameMap(world, WIDTH, HEIGHT);
		final float timeStep = settings.hz > 0.0f ? 1.0f / settings.hz : 0.0f;
		fpsTimer = new FpsTimer() {

			@Override
			public void update() {
				long box2DTime = System.currentTimeMillis();
				world.step(timeStep, settings.iterationCount);
				box2DTime = System.currentTimeMillis() - box2DTime;
				gameMap.draw(box2DTime, (int) getFps());
				keyHack.callback();
				if (me != null) {
					gameMap.setCamera(me.getPosition().x, 0, 20);
					float y = jump ? 15 : me.getLinearVelocity().y;
					jump = false;
					me.setLinearVelocity(new Vec2(way, y));
				}

			}
		};
		fpsTimer.start();

		keyHack = new KeyHack(this);

		gameMap.addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				event.preventDefault();
				event.stopPropagation();
				if (keyHack != null) {
					keyHack.keyDown(event);
				} else {
					keyDown(event);
				}
			}
		});

		gameMap.addKeyUpHandler(new KeyUpHandler() {

			public void onKeyUp(KeyUpEvent event) {
				if (keyHack != null) {
					keyHack.keyUp(event);
				} else {
					keyUp(event.getNativeKeyCode());
				}
			}
		});

		gameMap.addMouseMoveHandler(new MouseMoveHandler() {

			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if (me != null) {
					me.onMouse(event.getRelativeX(gameMap.getElement()),
							event.getRelativeY(gameMap.getElement()));
				}
			}
		});

		RootPanel.get().add(new Button("Stop", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fpsTimer.cancel();
			}
		}));
		RootPanel.get().add(gameMap);
		me = mapBuilder.getMe();
	}

	@Override
	public void keyDown(KeyDownEvent event) {
		if (event.isRightArrow() || event.getNativeKeyCode() == 68) {
			way = 10;
		} else if (event.isLeftArrow() || event.getNativeKeyCode() == 65) {
			way = -10;
		} else if (event.getNativeKeyCode() == 87) {
			jump = true;
		}
	}

	@Override
	public void keyUp(int keyCode) {
		GWT.log("Key up! " + keyCode);
		if (keyCode == 68 || keyCode == 65) {
			way = 0;
		}
	}
}
