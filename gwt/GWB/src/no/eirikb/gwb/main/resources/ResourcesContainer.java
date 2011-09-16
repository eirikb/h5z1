package no.eirikb.gwb.main.resources;

import gwt.g2d.resources.client.ExternalImageResource;
import gwt.g2d.resources.client.ImageElementResource;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;

public class ResourcesContainer {

	private static Map<String, ImageElement> images;
	private static int count;

	private ResourcesContainer() {
	}

	public interface ListenComplete {

		void onComplete(Map<String, ImageElement> images);
	}

	public static void init(final ExternalImageResource[] imageResources,
			final ListenComplete listenComplete) {
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
