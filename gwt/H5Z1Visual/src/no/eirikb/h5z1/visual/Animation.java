/*
 * -----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <eirikb@eirikb.no> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Eirik Brandtzæg
 * -----------------------------------------------------------------------------
 */
package no.eirikb.h5z1.visual;

import java.util.List;

/**
 * 
 * @author Eirik Brandtzæg <eirikb@eirikb.no>
 */
public class Animation {
	private List<VisualImage> images;
	private int animationTime = 1;
	private int animationTimeCurrent = 0;
	private int currentImagePos;
	private VisualImage currentImage;

	public Animation(List<VisualImage> images, int animationTime) {
		this.images = images;
		this.animationTime = animationTime;
		currentImage = images.get(0);
	}

	public VisualImage animate() {
		if (++animationTimeCurrent == animationTime) {
			currentImagePos = ++currentImagePos < images.size() ? currentImagePos
					: 0;
			animationTimeCurrent = 0;
			currentImage = images.get(currentImagePos);
		}
		return currentImage;
	}
}
