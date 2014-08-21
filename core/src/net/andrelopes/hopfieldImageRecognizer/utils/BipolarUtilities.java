package net.andrelopes.hopfieldImageRecognizer.utils;

import cookie.Matrixes.DoubleMatrix;

/**
 *
 * @author André Vinícius Lopes
 */
public class BipolarUtilities {

	//    public static Matrix createRowMatrix(final double input[]) {
	//		final double d[][] = new double[1][input.length];
	//		System.arraycopy(input, 0, d[0], 0, input.length);
	//		return new Matrix(d);
	//	}

	public DoubleMatrix bipolarToDouble(final boolean b[]) {
		DoubleMatrix result = new DoubleMatrix(1, b.length);
		for(int i = 0; i < b.length; i++) {
			result.setValueAt(0, i, bipolar2double(b[i]));

		}
		return result;
	}

	public static DoubleMatrix bipolar2double(final boolean b[]) {
		DoubleMatrix result = new DoubleMatrix(1, b.length);
		for(int i = 0; i < b.length; i++) {
			result.setValueAt(0, i, bipolar2double(b[i]));
		}
		return result;
	}

	public static short bipolar2double(final boolean value) {
		if(value) {
			return 1;
		} else {
			return -1;
		}
	}

}
