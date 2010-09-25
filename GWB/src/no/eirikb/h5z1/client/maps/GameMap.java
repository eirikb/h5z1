package no.eirikb.h5z1.client.maps;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import com.google.gwt.resources.client.ImageResource;

public abstract class GameMap {
	protected List<Body> visualBodies;
	protected List<Body> bodies;
	protected int width;
	protected int height;
	protected ImageResource background1Image;
	protected ImageResource background2Image;
	protected int cameraY;
	protected int scale;

	public GameMap(World world, int width, int height) {
		this.width = width;
		this.height = height;
		visualBodies = new ArrayList<Body>();
		bodies = new ArrayList<Body>();
	}

	public List<Body> getVisualBodies() {
		return visualBodies;
	}

	public List<Body> getBodies() {
		return bodies;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ImageResource getBackground1Image() {
		return background1Image;
	}

	public ImageResource getBackground2Image() {
		return background2Image;
	}

	public int getCameraY() {
		return cameraY;
	}

	public int getScale() {
		return scale;
	}

}
