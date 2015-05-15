package com.lava.iso.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.Arrays;

import com.lava.iso.transformation.Transformation;

public class Vector extends Entity {

	double originX, originY;

	/**
	 * Constructs a 3D vector
	 * @param x Specified X coordinate
	 * @param y Specified Y coordinate
	 * @param z Specified Z coordinate
	 * @param w Specified W coordinate (homogeneous)
	 * @param color Color of the vector
	 */
	public Vector(double x, double y, double z, double w, Color color) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.color = color;
	}

	/**
	 * Constructs a 3D vector with respect to some point
	 * @param x Specified X Coordinate
	 * @param y Specified Y Coordinate
	 * @param z Specified Z Coordinate
	 * @param w Specified W Coordinate (homogeneous)
	 * @param originX Specified X Coordinate of origin point
	 * @param originY Specified Y Coordinate of origin point
	 * @param color Color of the vector
	 */
	public Vector(double x, double y, double z, double w, double originX,
			double originY, Color color) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.originX = originX;
		this.originY = originY;
		this.color = color;
	}

	/**
	 * Gets vector projected onto the x, y plane
	 * Renders vector as a line with color
	 * @param g2 Graphics2D object to paint onto
	 */
	public void render(Graphics2D g2) {
		double drawVector[] = this.getProjection();
		Shape l = new Line2D.Double(originX, originY, originX + drawVector[0],
				originY - drawVector[1]);
		g2.setColor(this.color);
		g2.draw(l);
	}

	/**
	 * Gets vector projected onto the x, y plane
	 * Renders vector as a line with color 
	 * Drawn from some point (originX, originY)
	 * @param g2 Graphics2D object to paint onto
	 * @param originX X Coordinate of origin point to draw from
	 * @param originY Y Coordinate of origin point to draw from
	 */
	public void render(Graphics2D g2, double originX, double originY) {
		double drawVector[] = this.getDrawProjection(originX, originY);
		Shape l = new Line2D.Double(originX, originY, drawVector[0],
				drawVector[1]);
		g2.setColor(this.color);
		g2.draw(l);
	}

	/**
	 * Returns vector that has transformation matrix applied to it
	 * @return Projected vector
	 */
	public double[] getProjection() {
		double projection[] = Transformation.applyProjection(this.getVector());
		projection[0] /= projection[3];
		projection[1] /= projection[3];
		projection[2] /= projection[3];
		projection[3] /= projection[3];

		return projection;
	}

	/**
	 * Returns vector whose coordinates can be chugged into the drawLine method or Shape object
	 * @param originX X Coordinate of origin point to draw from
	 * @param originY Y Coordinate of origin point to draw from
	 * @return
	 */
	public double[] getDrawProjection(double originX, double originY) {
		double projection[] = this.getProjection();
		double drawVector[] = { (originX + projection[0]), (originY - projection[1]) };

		return drawVector;
	}

}
