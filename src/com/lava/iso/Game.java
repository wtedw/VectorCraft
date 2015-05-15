/* Thanks to Notch's Minicraft submission to Ludum Dare 22, Game and InputHandler class
 * 
 * Order:
 * Creates a JFrame for game to render in
 * Creates an instance of this class which automatically calls run()
 * run() handles updating / tick() calls of other entities
 * render() renders the entities
 * 
 */

package com.lava.iso;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.lava.iso.InputHandler;
import com.lava.iso.entity.Player;
import com.lava.iso.level.Level;
import com.lava.iso.transformation.Transformation;

public class Game extends Canvas implements Runnable {

	private InputHandler input = new InputHandler(this);

	public static final String NAME = "VectorCraft";
	public static final int HEIGHT = 720;	// 360, for image purposes
	public static final int WIDTH = 960;	// 480, for image purposes
	private int tickCount = 0; 				// For future

	private boolean running = false;

	private Level level;
	public Player player;

	public static void main(String[] args) {
		Game game = new Game();
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();

	}

	public void start() {
		running = true;
		new Thread(this).start();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();

		init();

		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			frames++;
			render();

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void init() {
		resetGame();
		// [TODO] Future, Load sprites
	}

	public void resetGame() {
		level = new Level(3, 3, 3, WIDTH / 2, HEIGHT / 2);
		player = new Player(this, input);
		level.setPlayer(player);
	}

	public void tick() {
		tickCount++;
		if (!hasFocus()) {
			input.releaseAll();
		} else {
			input.tick();
			level.tick();
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;					// Need to be able to render with double values
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);		// Smooth out graphics
		g2.setStroke(new BasicStroke(3));				// Thicker lines

		// Render background
		g2.setColor(Color.black);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		// Render structures, first buildings, then x, y axis
		level.renderEntities(g2, WIDTH / 2, HEIGHT / 2);
		level.renderVectors(g2, WIDTH / 2, HEIGHT / 2);

		// Coordinates
		g.setColor(Color.white);
		g.drawString("X Coord: " + Transformation.getX(), 20, 20);
		g.drawString("Y Coord: " + Transformation.getY(), 20, 40);
		g.drawString("Z Coord: " + Transformation.getZ(), 20, 60);

		g.dispose();
		bs.show();
	}
}
