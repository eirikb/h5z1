package no.eirikb.h5z1.client.visualbody;

import java.util.List;

import no.eirikb.h5z1.visual.VisualBody;
import no.eirikb.h5z1.visual.VisualImage;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import com.google.gwt.resources.client.ImageResource;

public class VisualStatic extends Body implements VisualBody {
	private ImageResource image;
	private boolean repeat;

	public VisualStatic(BodyDef bd, World world, ImageResource image,
			boolean repeat) {
		super(bd, world);
		this.image = image;
		this.repeat = repeat;
	}

	@Override
	public List<VisualImage> getImages() {
		return null;
	}

	public ImageResource getImage() {
		return image;
	}

	@Override
	public void setVisualX(int visualX) {
	}

	@Override
	public void setVisualY(int visualY) {
	}

	public boolean getRepeat() {
		return repeat;
	}
}
