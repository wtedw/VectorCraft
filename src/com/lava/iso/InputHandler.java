/* 
 * Thanks to Notch's Minicraft submission to Ludum Dare 22, Game and InputHandler class
 */

package com.lava.iso;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {
	public class Key {
		public int presses, absorbs;
		public boolean down, clicked;

		public Key() {
			keys.add(this);
		}

		public void toggle(boolean pressed) {
			if (pressed != down) {
				down = pressed;
			}
			if (pressed) {
				presses++;
			}
		}

		public void tick() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}

	public List<Key> keys = new ArrayList<Key>();

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key zIn = new Key();
	public Key zOut = new Key();
	public Key shift = new Key();
	public Key reset = new Key();

	public void releaseAll() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).down = false;
		}
	}

	public void tick() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).tick();
		}
	}

	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	public void keyPressed(KeyEvent ke) {
		toggle(ke, true);
	}

	public void keyReleased(KeyEvent ke) {
		toggle(ke, false);
	}

	private void toggle(KeyEvent ke, boolean pressed) {
		if (ke.getKeyCode() == KeyEvent.VK_UP)
			up.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_DOWN)
			down.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_LEFT)
			left.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
			right.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_A)
			zIn.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_Z)
			zOut.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_SHIFT)
			shift.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_R)
			reset.toggle(pressed);
	}

	public void keyTyped(KeyEvent ke) {
	}
}
