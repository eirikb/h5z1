package no.eirikb.h5z1.client.visualbody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import no.eirikb.h5z1.client.resources.ResourcesContainer;
import no.eirikb.h5z1.client.resources.ResourcesContainer.ListenComplete;
import no.eirikb.h5z1.visual.VisualBody;
import no.eirikb.h5z1.visual.VisualImage;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import com.google.gwt.dom.client.ImageElement;

public class VisualDynamic extends Body implements VisualBody {
	private List<VisualImage> images;

	public VisualDynamic(BodyDef bd, World world, final String image,
			final int offsetX, final int offsetY) {
		super(bd, world);
		images = new ArrayList<VisualImage>();
		ResourcesContainer.init(new ListenComplete() {

			@Override
			public void onComplete(Map<String, ImageElement> images2) {
				images.add(new VisualImage(images2.get(image), offsetX, offsetY));
			}
		});
	}

	@Override
	public List<VisualImage> getImages() {
		return images;
	}

	@Override
	public void setVisualX(int visualX) {
	}

	@Override
	public void setVisualY(int visualY) {
	}
}
