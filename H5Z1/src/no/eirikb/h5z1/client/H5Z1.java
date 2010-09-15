package no.eirikb.h5z1.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class H5Z1 implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.get().add(new Label("Hello world"));
	}
}
