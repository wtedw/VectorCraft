package com.lava.iso.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Box extends Entity {
	double xLength;
	double yLength;
	double zLength;

	private Vector lowerBottomLeft;
	private Vector lowerTopLeft;
	private Vector lowerTopRight;
	private Vector lowerBottomRight;

	private Vector higherBottomLeft;
	private Vector higherTopLeft;
	private Vector higherTopRight;
	private Vector higherBottomRight;

	/**
	 * Draws a box at the (x, y, z) coordinate
	 * Coordinate is the bottom left corner of the box
	 * Only draws from x, y plane to the z coordinate
	 * @param x X Coordinate relative to origin
	 * @param y Y Coordinate relative to the origin
	 * @param z Z Coordinate relative to the origin (height)
	 * @param xLength How long the box is in the X direction
	 * @param yLength How long the box is in the Y direction
	 * @param zLength How long the box is in the Z direction [TODO]
	 * @param color Color of the box
	 */
	public Box(double x, double y, double z, double xLength, double yLength,
			double zLength, Color color) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 1;

		this.color = color;

		this.xLength = xLength;
		this.yLength = yLength;
		this.zLength = zLength;

		lowerBottomLeft = new Vector(x, y, 0, 1, color);
		lowerTopLeft = new Vector(x, y + yLength, 0, 1, color);
		lowerTopRight = new Vector(x + xLength, y + yLength, 0, 1, color);
		lowerBottomRight = new Vector(x + xLength, y, 0, 1, color);

		higherBottomLeft = new Vector(x, y, z, 1, color);
		higherTopLeft = new Vector(x, y + yLength, z, 1, color);
		higherTopRight = new Vector(x + xLength, y + yLength, z, 1, color);
		higherBottomRight = new Vector(x + xLength, y, z, 1, color);
	}

	/**
	 * Renders faces in this order:
	 * Bottom
	 * Left
	 * Back
	 * Right
	 * Front
	 * Top, face gets filled in with color
	 * @param g2 Graphics object to paint to
	 * @param originX Middle of x length of canvas
	 * @param originY Middle of y length of canvas
	 */
	public void render(Graphics2D g2, double originX, double originY) {
		double lowerBottomLeftDraw[] = lowerBottomLeft.getDrawProjection(
				originX, originY);
		double lowerTopLeftDraw[] = lowerTopLeft.getDrawProjection(originX,
				originY);
		double lowerTopRightDraw[] = lowerTopRight.getDrawProjection(originX,
				originY);
		double lowerBottomRightDraw[] = lowerBottomRight.getDrawProjection(
				originX, originY);

		double higherBottomLeftDraw[] = higherBottomLeft.getDrawProjection(
				originX, originY);
		double higherTopLeftDraw[] = higherTopLeft.getDrawProjection(originX,
				originY);
		double higherTopRightDraw[] = higherTopRight.getDrawProjection(originX,
				originY);
		double higherBottomRightDraw[] = higherBottomRight.getDrawProjection(
				originX, originY);

		g2.setColor(color);

		Path2D bottomFace = new Path2D.Double();
		bottomFace.moveTo(lowerBottomLeftDraw[0], lowerBottomLeftDraw[1]);
		bottomFace.lineTo(lowerTopLeftDraw[0], lowerTopLeftDraw[1]);
		bottomFace.lineTo(lowerTopRightDraw[0], lowerTopRightDraw[1]);
		bottomFace.lineTo(lowerBottomRightDraw[0], lowerBottomRightDraw[1]);
		bottomFace.closePath();
		g2.draw(bottomFace);

		Path2D leftFace = new Path2D.Double();
		leftFace.moveTo(lowerBottomLeftDraw[0], lowerBottomLeftDraw[1]);
		leftFace.lineTo(higherBottomLeftDraw[0], higherBottomLeftDraw[1]);
		leftFace.lineTo(higherTopLeftDraw[0], higherTopLeftDraw[1]);
		leftFace.lineTo(lowerTopLeftDraw[0], lowerTopLeftDraw[1]);
		leftFace.closePath();
		g2.draw(leftFace);

		Path2D backFace = new Path2D.Double();
		backFace.moveTo(lowerTopLeftDraw[0], lowerTopLeftDraw[1]);
		backFace.lineTo(higherTopLeftDraw[0], higherTopLeftDraw[1]);
		backFace.lineTo(higherTopRightDraw[0], higherTopRightDraw[1]);
		backFace.lineTo(lowerTopRightDraw[0], lowerTopRightDraw[1]);
		backFace.closePath();
		g2.draw(backFace);

		Path2D rightFace = new Path2D.Double();
		rightFace.moveTo(lowerTopRightDraw[0], lowerTopRightDraw[1]);
		rightFace.lineTo(lowerBottomRightDraw[0], lowerBottomRightDraw[1]);
		rightFace.lineTo(higherBottomRightDraw[0], higherBottomRightDraw[1]);
		rightFace.lineTo(higherTopRightDraw[0], higherTopRightDraw[1]);
		rightFace.closePath();
		g2.draw(rightFace);

		Path2D frontFace = new Path2D.Double();
		frontFace.moveTo(lowerBottomLeftDraw[0], lowerBottomLeftDraw[1]);
		frontFace.lineTo(higherBottomLeftDraw[0], higherBottomLeftDraw[1]);
		frontFace.lineTo(higherBottomRightDraw[0], higherBottomRightDraw[1]);
		frontFace.lineTo(lowerBottomRightDraw[0], lowerBottomRightDraw[1]);
		frontFace.closePath();
		g2.draw(frontFace);

		Path2D topFace = new Path2D.Double();
		topFace.moveTo(higherBottomLeftDraw[0], higherBottomLeftDraw[1]);
		topFace.lineTo(higherTopLeftDraw[0], higherTopLeftDraw[1]);
		topFace.lineTo(higherTopRightDraw[0], higherTopRightDraw[1]);
		topFace.lineTo(higherBottomRightDraw[0], higherBottomRightDraw[1]);
		topFace.closePath();
		g2.fill(topFace);
	}
}
