package textQuantitizer;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class CharGrid {
	private PApplet parent = null;
	private PImage imageToProcess = null;
	private ArrayList<CharEntity> charList = new ArrayList<CharEntity>();
	private String text = "LOL ";
	private int currChar = 0;
	private int brightnessTreshold = 0;

	public CharGrid(PApplet parent, PImage image, int charSize, String text, int brightnessTreshold) {
		this.parent = parent;
		imageToProcess = image;
		imageToProcess.loadPixels();
		imageToProcess.resize(parent.width, parent.height);
		int[] pixels = imageToProcess.pixels;
		this.text = text;
		this.brightnessTreshold = brightnessTreshold;
		this.addPoints(pixels, charSize);
	}

	private void addPoints(int[] pixels, int charSize) {
		for (int h = charSize / 2; h < imageToProcess.height; h += charSize) {
			for (int w = charSize / 2; w < imageToProcess.width; w += charSize * 5 / 7) {
				if (parent.brightness(pixels[w + (h * imageToProcess.width)]) > this.brightnessTreshold) {
					charList.add(new CharEntity(parent, this.getNextChar(), pixels[w + (h * imageToProcess.width)],
							new PVector(w, h), charSize));
				}
			}
		}
	}

	private String getNextChar() {
		char retChar = text.charAt(currChar);
		currChar = (currChar + 1) % text.length();
		return Character.toString(retChar);
	}

	public void draw() {
		for (CharEntity charEntity : charList) {
			charEntity.draw();
		}
	}

	public void move() {
		for (CharEntity charEntity : charList) {
			charEntity.move();
		}
	}

}
