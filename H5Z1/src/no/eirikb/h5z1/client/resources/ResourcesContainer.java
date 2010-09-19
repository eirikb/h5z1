package no.eirikb.h5z1.client.resources;

import gwt.g2d.resources.client.ExternalImageResource;
import gwt.g2d.resources.client.ImageElementResource;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;

public class ResourcesContainer {

	private static Map<String, ImageElement> images;
	private static ExternalImageResource[] imageResources = {
			Resources.INSTANCE.ptl1(), Resources.INSTANCE.ptl2(),
			Resources.INSTANCE.ptl3(), Resources.INSTANCE.ptl4(),
			Resources.INSTANCE.ptr2(), Resources.INSTANCE.ptr1(),
			Resources.INSTANCE.ptr3(), Resources.INSTANCE.ptr4(),
			Resources.INSTANCE.pbl1(), Resources.INSTANCE.pbl2(),
			Resources.INSTANCE.pbl3(), Resources.INSTANCE.pbl4(),
			Resources.INSTANCE.pbl5(), Resources.INSTANCE.pbl6(),
			Resources.INSTANCE.pbl7(), Resources.INSTANCE.pbr1(),
			Resources.INSTANCE.pbr2(), Resources.INSTANCE.pbr3(),
			Resources.INSTANCE.pbr4(), Resources.INSTANCE.pbr5(),
			Resources.INSTANCE.pbr6(), Resources.INSTANCE.pbr7(),
			Resources.INSTANCE.bg1(), Resources.INSTANCE.pj1(),
			Resources.INSTANCE.pj2(), Resources.INSTANCE.pj3(),
			Resources.INSTANCE.pj4(), Resources.INSTANCE.zl1(),
			Resources.INSTANCE.zl2(), Resources.INSTANCE.zl3(),
			Resources.INSTANCE.zr1(), Resources.INSTANCE.zr2(),
			Resources.INSTANCE.zr3(), Resources.INSTANCE.crate() };
	private static int count;

	private ResourcesContainer() {
	}

	public interface ListenComplete {

		void onComplete(Map<String, ImageElement> images);
	}

	public static void init(final ListenComplete listenComplete) {
		if (images == null) {
			count = 0;
			images = new HashMap<String, ImageElement>();
			ResourceCallback<ImageElementResource> resourceCallback = new ResourceCallback<ImageElementResource>() {

				@Override
				public void onError(ResourceException e) {
				}

				@Override
				public void onSuccess(ImageElementResource resource) {
					images.put(resource.getName(), resource.getImage());
					if (++count == imageResources.length) {
						listenComplete.onComplete(images);
					}
				}
			};
			for (ExternalImageResource imageResource : imageResources) {
				imageResource.getImage(resourceCallback);
			}
		} else {
			listenComplete.onComplete(images);
		}
	}
}
