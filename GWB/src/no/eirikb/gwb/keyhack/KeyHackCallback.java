/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <eirikdb@gmail.com> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Eirik Brandtzæg
 * ----------------------------------------------------------------------------
 */
package no.eirikb.gwb.keyhack;

import com.google.gwt.event.dom.client.KeyDownEvent;

/**
 * 
 * @author Eirik Brandtzæg <eirikdb@gmail.com>
 */
public interface KeyHackCallback {

	public void keyDown(KeyDownEvent event);

	public void keyUp(int keyCode);
}