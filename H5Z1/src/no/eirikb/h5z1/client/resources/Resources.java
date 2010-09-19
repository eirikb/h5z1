/*
 * -----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <eirikb@eirikb.no> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Eirik Brandtzæg
 * -----------------------------------------------------------------------------
 */
package no.eirikb.h5z1.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

import gwt.g2d.resources.client.ExternalImageResource;

/**
 * 
 * @author Eirik Brandtzæg <eirikb@eirikb.no>
 */
public interface Resources extends ClientBundle {

	public Resources INSTANCE = GWT.create(Resources.class);

	@Source("gun1.png")
	ImageResource gun1();

	@Source("bullet1.png")
	ImageResource bullet1();

	@Source("ptl1.png")
	ExternalImageResource ptl1();

	@Source("ptl2.png")
	ExternalImageResource ptl2();

	@Source("ptl3.png")
	ExternalImageResource ptl3();

	@Source("ptl4.png")
	ExternalImageResource ptl4();

	@Source("ptr1.png")
	ExternalImageResource ptr1();

	@Source("ptr2.png")
	ExternalImageResource ptr2();

	@Source("ptr3.png")
	ExternalImageResource ptr3();

	@Source("ptr4.png")
	ExternalImageResource ptr4();

	@Source("pbl1.png")
	ExternalImageResource pbl1();

	@Source("pbl2.png")
	ExternalImageResource pbl2();

	@Source("pbl3.png")
	ExternalImageResource pbl3();

	@Source("pbl4.png")
	ExternalImageResource pbl4();

	@Source("pbl5.png")
	ExternalImageResource pbl5();

	@Source("pbl6.png")
	ExternalImageResource pbl6();

	@Source("pbl7.png")
	ExternalImageResource pbl7();

	@Source("pbr1.png")
	ExternalImageResource pbr1();

	@Source("pbr2.png")
	ExternalImageResource pbr2();

	@Source("pbr3.png")
	ExternalImageResource pbr3();

	@Source("pbr4.png")
	ExternalImageResource pbr4();

	@Source("pbr5.png")
	ExternalImageResource pbr5();

	@Source("pbr6.png")
	ExternalImageResource pbr6();

	@Source("pbr7.png")
	ExternalImageResource pbr7();

	@Source("bg1.png")
	ImageResource bg1();

	@Source("pj1.png")
	ExternalImageResource pj1();

	@Source("pj2.png")
	ExternalImageResource pj2();

	@Source("pj3.png")
	ExternalImageResource pj3();

	@Source("pj4.png")
	ExternalImageResource pj4();

	@Source("zl1.png")
	ExternalImageResource zl1();

	@Source("zl2.png")
	ExternalImageResource zl2();

	@Source("zl3.png")
	ExternalImageResource zl3();

	@Source("zr1.png")
	ExternalImageResource zr1();

	@Source("zr2.png")
	ExternalImageResource zr2();

	@Source("zr3.png")
	ExternalImageResource zr3();

	@Source("crate.png")
	ExternalImageResource crate();

	@Source("fr.png")
	ExternalImageResource fr();

	@Source("fl.png")
	ExternalImageResource fl();
	
	@Source("bg2.png")
	ImageResource bg2();
}
