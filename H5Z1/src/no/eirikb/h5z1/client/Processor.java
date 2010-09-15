package no.eirikb.h5z1.client;

public class Processor {

	public static final float map(float value, float istart, float istop,
			float ostart, float ostop) {
		return ostart + (ostop - ostart)
				* ((value - istart) / (istop - istart));
	}

}
