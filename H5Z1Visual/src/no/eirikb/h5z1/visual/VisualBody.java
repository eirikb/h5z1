/*
 * -----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <eirikb@eirikb.no> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Eirik Brandtzæg
 * -----------------------------------------------------------------------------
 */
package no.eirikb.h5z1.visual;

import java.util.List;

/**
 * 
 * @author Eirik Brandtzæg <eirikb@eirikb.no>
 */
public interface VisualBody {

	List<VisualImage> getImages();

	void setVisualX(int x);

	void setVisualY(int y);
}
