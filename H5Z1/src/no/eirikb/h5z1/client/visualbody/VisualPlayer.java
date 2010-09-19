package no.eirikb.h5z1.client.visualbody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import no.eirikb.h5z1.client.resources.ResourcesContainer;
import no.eirikb.h5z1.client.resources.ResourcesContainer.ListenComplete;
import no.eirikb.h5z1.visual.Animation;
import no.eirikb.h5z1.visual.VisualBody;
import no.eirikb.h5z1.visual.VisualImage;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import com.google.gwt.dom.client.ImageElement;

public class VisualPlayer extends Body implements VisualBody {
	private Animation animation;
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;
	private Animation jumpAnimation;
	private boolean jumping;
	private List<VisualImage> topImages;
	private List<VisualImage> images;
	private List<VisualImage> jumpImages;
	private VisualImage standRightImage;
	private VisualImage standLeftImage;
	private int visualX;
	private int visualY;

	public VisualPlayer(BodyDef bd, World world) {
		super(bd, world);

		images = new ArrayList<VisualImage>(2);
		jumpImages = new ArrayList<VisualImage>();
		ResourcesContainer.init(new ListenComplete() {

			@Override
			public void onComplete(Map<String, ImageElement> images2) {
				int bottomOffsetX = -12;
				int bottomOffsetY = 0;
				topImages = getImages(images2, "tr", 0, 4, -7, -26);
				topImages.addAll(getImages(images2, "tl", 0, 4, -16, -26));
				walkLeftAnimation = new Animation(getImages(images2, "bl", 1,
						7, bottomOffsetX, bottomOffsetY), 7);
				walkRightAnimation = new Animation(getImages(images2, "br", 1,
						7, bottomOffsetX, bottomOffsetY), 7);
				jumpAnimation = new Animation(getImages(images2, "j", 0, 4, 0,
						0), 2);
				standRightImage = new VisualImage(images2.get("pbr1"),
						bottomOffsetX, bottomOffsetY);
				standLeftImage = new VisualImage(images2.get("pbl1"),
						bottomOffsetX, bottomOffsetY);
				images.add(standRightImage);
				images.add(topImages.get(0));
				jumpImages.add(jumpAnimation.animate());
			}
		});
	}

	private List<VisualImage> getImages(Map<String, ImageElement> images,
			String way, int offset, int count, int offX, int offY) {
		List<VisualImage> images2 = new ArrayList<VisualImage>();
		for (int i = 0; i < count; i++) {
			images2.add(new VisualImage(images.get("p" + way + (i + 1)), offX,
					offY));
		}
		return images2;
	}

	public double[] cosin(double x1, double y1, double x2, double y2) {
		int a = (int) (y2 - y1);
		int b = (int) (x2 - x1);
		double h = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		double sine = a / h;
		double cosine = b / h;
		return new double[] { cosine, sine };
	}

	public void onMouse(int x, int y) {

		double[] cosin = cosin(visualX, visualY, x, y);
		int way = cosin[0] > 0 ? 0 : 4;
		int number = 0;
		double sin = cosin[1];
		if (sin > -0.4) {
			if (sin > 0.5) {
				number = 2;
			}
		} else {
			if (sin > -0.9) {
				number = 1;
			} else {
				number = 3;
			}
		}
		images.set(1, topImages.get(number + way));
	}

	@Override
	public List<VisualImage> getImages() {
		if (jumping) {
			jumpImages.set(0, jumpAnimation.animate());
			return jumpImages;
		} else {
			if (getLinearVelocity().x != 0 && animation != null) {
				images.set(0, animation.animate());
			}
			return images;
		}
	}

	@Override
	public void setLinearVelocity(Vec2 v) {
		super.setLinearVelocity(v);
		if (v.x > 2) {
			animation = walkRightAnimation;
		} else if (v.x < -2) {
			animation = walkLeftAnimation;
		} else {
			if (images.size() > 0) {
				images.set(0, standRightImage);
			}
		}
	}

	@Override
	public void setVisualX(int visualX) {
		this.visualX = visualX;
	}

	@Override
	public void setVisualY(int visualY) {
		this.visualY = visualY;
	}

}