/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <eirikdb@gmail.com> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Eirik Brandtzæg
 * ----------------------------------------------------------------------------
 */
package no.eirikb.h5z1.client.keyhack;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;

/**
 * 
 * @author Eirik Brandtzæg <eirikdb@gmail.com>
 */
public class KeyHack {

	private KeyHackCallback keyHackCallback;
	private Long lastKeyPress;
	private Integer nativeKeyCode;
	private boolean anotherKeyPresses;
	private int keyCode;

	public KeyHack(KeyHackCallback keyHackCallback) {
		this.keyHackCallback = keyHackCallback;
	}

	public void setAnotherKeyPresses(boolean anotherKeyPresses) {
		this.anotherKeyPresses = anotherKeyPresses;
		lastKeyPress = null;
	}

	public void keyDown(KeyDownEvent event) {
		if (nativeKeyCode != null && event.getNativeKeyCode() != nativeKeyCode) {
			lastKeyPress = null;
		}
		if (lastKeyPress == null) {
			nativeKeyCode = event.getNativeKeyCode();
			keyHackCallback.keyDown(event);
		}
	}

	public void keyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == nativeKeyCode) {
			if (anotherKeyPresses) {
				anotherKeyPresses = false;
				keyHackCallback.keyUp(nativeKeyCode);
			} else {
				lastKeyPress = System.currentTimeMillis();
				keyCode = event.getNativeKeyCode();
			}
		}
	}

	public void callback() {
		if (lastKeyPress != null
				&& System.currentTimeMillis() - lastKeyPress > 100) {
			lastKeyPress = null;
			keyHackCallback.keyUp(keyCode);
		}
	}
}