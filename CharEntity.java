package textQuantitizer;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class CharEntity {
	private PApplet parent = null;
	private String character = " ";
	private int color = 0;
	private PVector positon = null;
	private PVector velocity = null;
	private int size = 15;

	public CharEntity(PApplet parent, String character, int color, PVector position, int size) {
		this.parent = parent;
		this.character = character;
		this.color = color;
		this.positon = position;
		this.size = size;
		this.velocity = PVector.random2D();
	}

	public void draw() {
		parent.pushMatrix();
		parent.fill(color);
		parent.textAlign(PConstants.CENTER, PConstants.CENTER);
		parent.textSize(size);
		parent.text(character, positon.x, positon.y);
		parent.popMatrix();
	}

	public void setPosition(float x, float y) {
		positon.set(x, y);
	}

	public void move() {
		this.positon.add(this.velocity);
		this.positon.x = this.positon.x % parent.width;
		this.positon.y = this.positon.y % parent.height;
		if (this.positon.x < 0) {
			this.positon.x += parent.width;
		}
		if (this.positon.y < 0) {
			this.positon.y += parent.height;
		}
	}

}
