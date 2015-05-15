package com.lava.iso.level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.lava.iso.entity.Box;
import com.lava.iso.entity.Entity;
import com.lava.iso.entity.Player;
import com.lava.iso.entity.Vector;
import com.lava.iso.transformation.Transformation;

public class Level {

	Player player;
	int maxLength;
	int maxWidth;
	int maxHeight;

	double originX, originY;

	public List<Entity> vectors = new ArrayList<Entity>();
	public List<Entity> entities = new ArrayList<Entity>();

	public Level(int l, int w, int h, double originX, double originY) {
		maxLength = l;
		maxWidth = w;
		maxHeight = h;

		this.originX = originX;
		this.originY = originY;

		initVectors();
		initEntities();
	}

	/**
	 * Creates X, Y axis and puts into vector list
	 */
	public void initVectors() {
		vectors.add(new Vector(100, 0, 0, 1, Color.red));
		vectors.add(new Vector(0, 100, 0, 1, Color.blue));

		vectors.add(new Vector(-100, 0, 0, 1, Color.red));
		vectors.add(new Vector(0, -100, 0, 1, Color.blue));
	}

	/**
	 * Creates maps
	 */
	public void initEntities() {
		// Bank
		entities.add(new Box(50, 50, 100, 100, 50, 50, Color.cyan));

		// Coffee shops
		entities.add(new Box(50, -100, 50, 50, 50, 1, Color.magenta));
		entities.add(new Box(50, -175, 50, 50, 50, 1, Color.pink));

		// Street
		entities.add(new Box(-250, -25, 10, 500, 50, 1, Color.gray));
		entities.add(new Box(-25, -250, 10, 50, 500, 1, Color.gray));

		// Museum
		entities.add(new Box(-100, -150, 100, 50, 100, 1, Color.green));
	}

	/**
	 * Ticks the player so that we receive keyboard input
	 */
	public void tick() {
		player.tick();
		// Future implementation
	}

	/**
	 * Adds a vector object into the entities list
	 * @param vector
	 */
	public void add(Vector vector) {
		vectors.add(vector);
		// [TODO] Randomly generated level
	}

	/**
	 * Sets default player to listen for input
	 * @param p Main player
	 */
	public void setPlayer(Player p) {
		player = p;
	}

	/**
	 * Renders the buildings
	 * We need origin since coordinates are relative to origin
	 * @param g2 Graphics object to paint onto
	 * @param originX Middle of x length of canvas
	 * @param originY Middle of y length of canvas
	 */
	public void renderEntities(Graphics2D g2, int originX, int originY) {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g2, originX, originY);
		}
		System.out.println(Transformation.transMatrix[3][0]);
		System.out.println();
		System.out.println("===========");
		System.out.println();
	}

	/**
	 * Renders X and Y axis
	 * @param g2 Graphics object to paint onto
	 * @param originX Middle of x length of canvas
	 * @param originY Middle of y length of canvas
	 */
	public void renderVectors(Graphics2D g2, int originX, int originY) {
		for (int i = 0; i < vectors.size(); i++) {
			vectors.get(i).render(g2, originX, originY);
		}
	}
}
