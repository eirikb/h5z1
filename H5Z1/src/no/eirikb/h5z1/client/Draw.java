package no.eirikb.h5z1.client;

import gwt.g2d.client.graphics.Surface;
import gwt.g2d.client.graphics.shapes.ShapeBuilder;

import org.jbox2d.common.Color3f;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.XForm;
import org.jbox2d.dynamics.DebugDraw;

import com.google.gwt.core.client.GWT;

public class Draw extends DebugDraw {

	private Surface surface;

	public Draw(Surface surface) {
		this.surface = surface;
	}

	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	public float fontHeight;
	public float transX = 640.0f;
	public float transY = 480.0f;
	public float scaleFactor = 20.0f;
	public float yFlip = -1.0f; // flip y coordinate

	public void setCamera(float x, float y, float scale) {
		transX = Processor
				.map(x, 0.0f, -1.0f, WIDTH * .5f, WIDTH * .5f + scale);
		transY = Processor.map(y, 0.0f, yFlip * 1.0f, HEIGHT * .5f, HEIGHT
				* .5f + scale);
		scaleFactor = scale;
	}

	public Vec2 worldToScreen(Vec2 world) {
		float x = Processor.map(world.x, 0f, 1f, transX, transX + scaleFactor);
		float y = Processor.map(world.y, 0f, 1f, transY, transY + scaleFactor);
		if (yFlip == -1.0f)
			y = Processor.map(y, 0f, HEIGHT, HEIGHT, 0f);
		return new Vec2(x, y);
	}

	public Vec2 worldToScreen(float x, float y) {
		return worldToScreen(new Vec2(x, y));
	}

	public Vec2 screenToWorld(Vec2 screen) {
		float x = Processor.map(screen.x, transX, transX + scaleFactor, 0f, 1f);
		float y = 0;
		if (yFlip == -1.0f)
			y = Processor.map(y, HEIGHT, 0f, 0f, HEIGHT);
		y = Processor.map(y, transY, transY + scaleFactor, 0f, 1f);
		return new Vec2(x, y);
	}

	public Vec2 screenToWorld(float x, float y) {
		return screenToWorld(new Vec2(x, y));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jbox2d.dynamics.DebugDraw#drawCircle(org.jbox2d.common.Vec2,
	 * float, javax.vecmath.Color3f)
	 */
	@Override
	public void drawCircle(Vec2 center, float radius, Color3f color) {
		center = worldToScreen(center);
		radius *= scaleFactor;
		surface.strokeShape(new ShapeBuilder().drawCircle(center.x, center.y,
				radius).build());
		GWT.log("circle");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jbox2d.dynamics.DebugDraw#drawSolidCircle(org.jbox2d.common.Vec2,
	 * float, org.jbox2d.common.Vec2, javax.vecmath.Color3f)
	 */
	@Override
	public void drawSolidCircle(Vec2 center, float radius, Vec2 axis,
			Color3f color) {
		center = worldToScreen(center);
		radius = radius * scaleFactor;
		axis = new Vec2(axis.x, axis.y * yFlip);
		surface.strokeShape(new ShapeBuilder().drawCircle(center.x, center.y,
				radius).build());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jbox2d.dynamics.DebugDraw#drawPolygon(org.jbox2d.common.Vec2[],
	 * int, javax.vecmath.Color3f)
	 */
	@Override
	public void drawPolygon(Vec2[] vertices, int vertexCount, Color3f color) {
		for (int i = 0; i < vertexCount; ++i) {
			int ind = (i + 1 < vertexCount) ? i + 1 : (i + 1 - vertexCount);
			Vec2 v1 = worldToScreen(vertices[i]);
			Vec2 v2 = worldToScreen(vertices[ind]);
			surface.strokeShape(new ShapeBuilder().drawLineSegment(v1.x, v1.y,
					v2.x, v2.y).build());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jbox2d.dynamics.DebugDraw#drawSolidPolygon(org.jbox2d.common.Vec2[],
	 * int, javax.vecmath.Color3f)
	 */
	@Override
	public void drawSolidPolygon(Vec2[] vertices, int vertexCount, Color3f color) {
		// g.noStroke();
		// g.fill(0.5f * color.x, 0.5f * color.y, 0.5f * color.z, 0.5f *
		// 255.0f);
		// g.beginShape(PApplet.POLYGON);
		// for (int i = 0; i < vertexCount; ++i) {
		// Vec2 v = worldToScreen(vertices[i]);
		// g.vertex(v.x, v.y);
		// }
		// g.endShape();
		//
		// g.stroke(color.x, color.y, color.z, 255.0f);
		// for (int i = 0; i < vertexCount; ++i) {
		// int ind = (i + 1 < vertexCount) ? i + 1 : (i + 1 - vertexCount);
		// Vec2 v1 = worldToScreen(vertices[i]);
		// Vec2 v2 = worldToScreen(vertices[ind]);
		// g.line(v1.x, v1.y, v2.x, v2.y);
		// }
		// GWT.log("solidPolygon");

		for (int i = 0; i < vertexCount; ++i) {
			int ind = (i + 1 < vertexCount) ? i + 1 : (i + 1 - vertexCount);
			Vec2 v1 = worldToScreen(vertices[i]);
			Vec2 v2 = worldToScreen(vertices[ind]);
			surface.strokeShape(new ShapeBuilder().drawLineSegment(v1.x, v1.y,
					v2.x, v2.y).build());
		}
	}

	@Override
	public void drawSegment(Vec2 p1, Vec2 p2, Color3f color) {
		p1 = worldToScreen(p1);
		p2 = worldToScreen(p2);
		surface.strokeShape(new ShapeBuilder().drawLineSegment(p1.x, p1.y,
				p2.x, p2.y).build());
		// g.stroke(color.x, color.y, color.z);
		// g.beginShape(PApplet.LINES);
		// g.vertex(p1.x, p1.y);
		// g.vertex(p2.x, p2.y);
		// g.endShape();
		// GWT.log("segment");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jbox2d.dynamics.DebugDraw#drawXForm(org.jbox2d.common.XForm)
	 */
	@Override
	public void drawXForm(XForm xf) {
		// Vec2 p1 = xf.position.clone(), p2 = new Vec2();
		// float k_axisScale = 0.4f;
		// g.beginShape(PApplet.LINES);
		// Vec2 p1world = worldToScreen(p1);
		// g.stroke(255.0f, 0.0f, 0.0f);
		// g.vertex(p1world.x, p1world.y);
		// p2.x = p1.x + k_axisScale * xf.R.col1.x;
		// p2.y = p1.y + k_axisScale * xf.R.col1.y;
		// Vec2 p2world = worldToScreen(p2);
		// g.vertex(p2world.x, p2world.y);
		//
		// g.stroke(0.0f, 255.0f, 0.0f);
		// g.vertex(p1world.x, p1world.y);
		// p2.x = p1.x + k_axisScale * xf.R.col2.x;
		// p2.y = p1.y + k_axisScale * xf.R.col2.y;
		// p2world = worldToScreen(p2);
		// g.vertex(p2world.x, p2world.y);
		//
		// g.endShape();
		GWT.log("xForm");

	}

	@Override
	public void drawString(float x, float y, String s, Color3f color) {
		// // g.textFont(m_font, 36);
		// // if (true) return;
		// if (firstTime) {
		// g.textFont(m_font);
		// if (g.g instanceof PGraphics3D)
		// g.textMode(PApplet.SCREEN);
		// firstTime = false;
		// }
		// g.fill(color.x, color.y, color.z);
		// // g.fill(255.0f);
		// g.text(s, x, y);
		GWT.log("string");
	}

	@Override
	public void drawPoint(Vec2 position, float f, Color3f color) {
		// position = worldToScreen(position);
		// float k_segments = 5.0f;
		// float k_increment = 2.0f * (float) Math.PI / k_segments;
		// float k_radius = 3.0f;
		// float theta = 0.0f;
		// g.fill(color.x, color.y, color.z);
		// g.noStroke();
		// g.beginShape(PApplet.POLYGON);
		// for (int i = 0; i < k_segments; ++i) {
		// float vx = position.x + k_radius * (float) Math.cos(theta);
		// float vy = position.y + k_radius * (float) Math.sin(theta);
		// g.vertex(vx, vy);
		// theta += k_increment;
		// }
		// g.endShape();
		GWT.log("point");
	}

	/**
	 * First image is centered on position, then localScale is applied, then
	 * localOffset, and lastly rotation. <BR>
	 * <BR>
	 * Thus localOffset should be specified in world units before scaling is
	 * applied. For instance, if you want a MxN image to have its corner at body
	 * center and be scaled by S, use a localOffset of (M*S/2, N*S/2) and a
	 * localScale of S. <BR>
	 * <BR>
	 * 
	 */
	// public void drawImage(PImage image, Vec2 position, float rotation,
	// float localScale, Vec2 localOffset, float halfImageWidth,
	// float halfImageHeight) {
	// // position = worldToScreen(position);
	// // localOffset = worldToScreenVector(localOffset);
	// // localScale *= scaleFactor;
	// // g.pushMatrix();
	// // g.translate(position.x, position.y);
	// // g.rotate(-rotation);
	// // g.translate(localOffset.x, localOffset.y);
	// // g.scale(localScale);
	// // g.image(image, -halfImageWidth, -halfImageHeight);
	// // g.popMatrix();
	// GWT.log("image");
	// }

	public Vec2 worldToScreenVector(Vec2 world) {
		return world.mul(scaleFactor);
	}

	public Vec2 worldToScreenVector(float x, float y) {
		return worldToScreenVector(new Vec2(x, y));
	}

}
