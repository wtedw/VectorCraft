package com.lava.iso.entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class Entity {
	public double x;
	public double y;
	public double z;
	public double w; // Homogeneous coordinates

	public Color color;

	public void render(Graphics2D g2) {
	}

	public void render(Graphics2D g2, double originX, double originY) {
	}

	public double[] getVector() {
		return new double[] { x, y, z, w };
	}
}
