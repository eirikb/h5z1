/*
 * -----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <eirikb@eirikb.no> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Eirik Brandtzæg
 * -----------------------------------------------------------------------------
 */
package no.eirikb.h5z1.visual;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

/**
 * 
 * @author Eirik Brandtzæg <eirikb@eirikb.no>
 */
public abstract class GameBody extends Body {

	public GameBody(BodyDef bd, World world) {
		super(bd, world);
	}

	private boolean doSlide;

	public boolean doSlide() {
		return doSlide;
	}

	public void setDoSlide(boolean doSlide) {
		this.doSlide = doSlide;
	}
}
