package no.eirikb.h5z1.visual;

import com.google.gwt.dom.client.ImageElement;


public class VisualImage {
	private ImageElement image;
	private int offX;
	private int offY;

	public VisualImage(ImageElement image, int offX, int offY) {
		super();
		this.offX = offX;
		this.offY = offY;
		this.image = image;
	}

	public int getOffX() {
		return offX;
	}

	public int getOffY() {
		return offY;
	}

	public ImageElement getImage() {
		return image;
	}
}
