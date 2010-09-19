package no.eirikb.h5z1.client;

import gwt.g2d.client.graphics.Color;
import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.graphics.shapes.ShapeBuilder;
import no.eirikb.h5z1.visual.VisualBody;
import no.eirikb.h5z1.visual.VisualImage;

import org.jbox2d.collision.PolygonShape;
import org.jbox2d.collision.Shape;
import org.jbox2d.collision.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.XForm;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import com.google.gwt.dom.client.ImageElement;

public class GameMap extends Surface {
	private World world;
	private int width;
	private int height;
	private float transX = 640.0f;
	private float transY = 480.0f;
	private float scaleFactor = 20.0f;
	private float yFlip = -1.0f; // flip y coordinate

	private final float map(float value, float istart, float istop,
			float ostart, float ostop) {
		return ostart + (ostop - ostart)
				* ((value - istart) / (istop - istart));
	}

	public void setCamera(float x, float y, float scale) {
		transX = map(x, 0.0f, -1.0f, width * .5f, width * .5f + scale);
		transY = map(y, 0.0f, yFlip * 1.0f, height * .5f, height * .5f + scale);
		scaleFactor = scale;
	}

	public Vec2 worldToScreen(Vec2 world) {
		float x = map(world.x, 0f, 1f, transX, transX + scaleFactor);
		float y = map(world.y, 0f, 1f, transY, transY + scaleFactor);
		if (yFlip == -1.0f)
			y = map(y, 0f, height, height, 0f);
		return new Vec2(x, y);
	}

	public Vec2 worldToScreen(float x, float y) {
		return worldToScreen(new Vec2(x, y));
	}

	public Vec2 screenToWorld(Vec2 screen) {
		float x = map(screen.x, transX, transX + scaleFactor, 0f, 1f);
		float y = 0;
		if (yFlip == -1.0f)
			y = map(y, height, 0f, 0f, height);
		y = map(y, transY, transY + scaleFactor, 0f, 1f);
		return new Vec2(x, y);
	}

	public Vec2 screenToWorld(float x, float y) {
		return screenToWorld(new Vec2(x, y));
	}

	public GameMap(World world, int width, int height) {
		super(width, height);
		this.world = world;
		this.width = width;
		this.height = height;
	}

	public void draw(long box2DTime, int fps) {
		long canvasTime = System.currentTimeMillis();
		clear();
		for (Body b = world.getBodyList(); b != null; b = b.m_next) {
			XForm xf = b.getXForm();

			if (b instanceof VisualBody) {
				VisualBody visualBody = (VisualBody) b;

				for (VisualImage image : visualBody.getImages()) {
					Vec2 v = worldToScreen(b.getPosition());
					visualBody.setVisualX((int) v.x);
					visualBody.setVisualY((int) v.y);
					ImageElement ie = image.getImage();
					if (ie != null) {
						if (b.getAngle() != 0) {
							translate(v.x, v.y);
							rotate(-b.getAngle());
							drawImage(ie, 0 + image.getOffX(),
									0 + image.getOffY());
							rotate(b.getAngle());
							translate(-v.x, -v.y);
						} else {
							drawImage(ie, v.x + image.getOffX(),
									v.y + image.getOffY());
						}
					}
				}
			}

			for (Shape s = b.m_shapeList; s != null; s = s.m_next) {
				setStrokeStyle(new Color(0, 0, 0));
				if (b.m_invMass == 0.0f) {
					setStrokeStyle(new Color(100, 100, 100));
				} else if (b.isSleeping()) {
					setStrokeStyle(new Color(200, 200, 200));
				} else {
					setStrokeStyle(new Color(30, 30, 30));
				}
				if (s.getType() == ShapeType.CIRCLE_SHAPE) {
					// CircleShape circle = (CircleShape) s;
					// Vec2 center = XForm.mul(xf, circle.getLocalPosition());
					// float radius = circle.getRadius() * scaleFactor;
					// center = worldToScreen(center);
					// strokeShape(new ShapeBuilder().drawCircle(center.x,
					// center.y, radius).build());
				} else if (s.getType() == ShapeType.POLYGON_SHAPE) {
					PolygonShape poly = (PolygonShape) s;
					int vertexCount = poly.getVertexCount();
					Vec2[] localVertices = poly.getVertices();
					ShapeBuilder shapeBuilder = new ShapeBuilder();
					for (int i = 0; i < vertexCount; ++i) {
						int ind = (i + 1 < vertexCount) ? i + 1
								: (i + 1 - vertexCount);
						Vec2 v1 = worldToScreen(XForm.mul(xf, localVertices[i]));
						Vec2 v2 = worldToScreen(XForm.mul(xf,
								localVertices[ind]));
						shapeBuilder.drawLineSegment(v1.x, v1.y, v2.x, v2.y);
					}
					strokeShape(shapeBuilder.build());
				}
			}
		}
		strokeText("FPS: " + fps, 0, 10);
		strokeText("Box2D: " + box2DTime, 0, 20);
		canvasTime = System.currentTimeMillis() - canvasTime;
		strokeText("Canvas: " + canvasTime, 0, 30);
	}
}
