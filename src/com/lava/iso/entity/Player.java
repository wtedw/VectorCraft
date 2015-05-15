package com.lava.iso.entity;

import com.lava.iso.Game;
import com.lava.iso.InputHandler;
import com.lava.iso.transformation.Transformation;

public class Player extends Entity {

	// For future implementation
	private InputHandler input;
	public Game game;

	private double moveSpeed = .000050;

	public Player(Game game, InputHandler input) {
		this.game = game;
		this.input = input;
	}

	/**
	 * Right arrow = Move to right on x axis 
	 * Left arrow = Move to left on x axis
	 * 
	 * Up arrow = Move higher on the y axis 
	 * Down arrow = Move lower on the y
	 * axis
	 * 
	 * A = Zoom in Z = Zoom out
	 * 
	 * Getting close to extremities (axis) 
	 * will cause the matrix to flip signs and camera view will not be intuitive
	 */
	public void tick() {
		if (input.right.down) {
			if (input.shift.down)
				Transformation.incP(-moveSpeed * 2);
			else
				Transformation.incP(-moveSpeed);
		}
		if (input.left.down) {
			if (input.shift.down)
				Transformation.incP(moveSpeed * 2);
			else
				Transformation.incP(moveSpeed);
		}
		if (input.up.down) {
			if (input.shift.down)
				Transformation.incQ(-moveSpeed * 2);
			else
				Transformation.incQ(-moveSpeed);
		}
		if (input.down.down) {
			if (input.shift.down)
				Transformation.incQ(moveSpeed * 2);
			else
				Transformation.incQ(moveSpeed);
		}
		if (input.zIn.down) {
			if (input.shift.down)
				Transformation.incR(-moveSpeed * 2);
			else
				Transformation.incR(-moveSpeed);
		}
		if (input.zOut.down) {
			if (input.shift.down)
				Transformation.incR(moveSpeed * 2);
			else
				Transformation.incR(moveSpeed);
		}
		if (input.reset.down) {
			Transformation.reset();
		}
	}

	// Future implementation
	public void render() {
	}
}
