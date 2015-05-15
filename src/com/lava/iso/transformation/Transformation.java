package com.lava.iso.transformation;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class Transformation {

	// 3 Point Perspective Projection Matrix
	public static double transMatrix[][] = 
		{ 	{ 1, 0, 0, 0 }, 
			{ 0, 1, 0, 0 },
			{ 0, 0, 0, 0 }, 
			{ 0, 0, 0, 1 } };

	public static RealMatrix transformationMatrix = MatrixUtils.createRealMatrix(transMatrix);

	/**
	 * Gets transformed vector when transformation vector is applied onto it
	 * @param original Original vector coordinates relative to origin
	 * @return Vector that has transformation matrix applied onto it
	 */
	public static double[] applyProjection(double original[]) {
		double projection[] = transformationMatrix.operate(original);
		return projection;
	}

	/**
	 * Adjusts the p value by some value
	 * P corresponds to the X-Axis
	 * @param p Arbitrary value to add/sub onto p element of transformation matrix
	 */
	public static void incP(double p) {
		transMatrix[3][0] += p;
		transformationMatrix = MatrixUtils.createRealMatrix(transMatrix);
	}
	
	/**
	 * Adjusts the q value by some value
	 * Q corresponds to the Y-Axis
	 * @param q Arbitrary value to add/sub onto q element of transformation matrix
	 */
	public static void incQ(double q) {
		transMatrix[3][1] += q;
		transformationMatrix = MatrixUtils.createRealMatrix(transMatrix);
	}

	/**
	 * Adjusts the q value by some value
	 * R corresponds to the Z-Axis
	 * @param r Arbitrary value to add/sub onto q element of transformation matrix
	 */
	public static void incR(double r) {
		transMatrix[3][2] += r;
		transformationMatrix = MatrixUtils.createRealMatrix(transMatrix);
	}

	/**
	 * Get X value in the transformation matrix (negative inverse of last row, first column)
	 * @return X value of camera
	 */
	public static double getX() {
		return (-1 / transMatrix[3][0]);
	}

	/**
	 * Get Y value in the transformation matrix (negative inverse of last row, second column)
	 * @return Y value of camera
	 */
	public static double getY() {
		return (-1 / transMatrix[3][1]);
	}

	/**
	 * Get Z value in the transformation matrix (negative inverse of last row, third column)
	 * @return Y value of camera
	 */
	public static double getZ() {
		return (-1 / transMatrix[3][2]);
	}

	/**
	 * Re-adjust the camera position to p = 0, q = 0, r = 0
	 */
	public static void reset() {
		transMatrix[3][0] = 0;
		transMatrix[3][1] = 0;
		transMatrix[3][2] = 0;
		transformationMatrix = MatrixUtils.createRealMatrix(transMatrix);
	}

}
