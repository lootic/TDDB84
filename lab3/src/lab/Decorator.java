package lab;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.AbstractList;

// YOUR CODE HERE
// extends? implements?
class Decorator extends AbstractGraphics implements Renderer {

	private Renderer decorated;

	public Decorator(Renderer r) {
		decorated = r;
	}

	private void decorateRectangle(Rectangle r) {
		java.awt.Graphics g = GamePanel.getBuffer();
		g.setColor(Color.BLACK);
		g.drawLine(r.x + 2, r.y - 2, r.x + r.width + 2, r.y - 2);
		g.drawLine(r.x + r.width + 2, r.y - 2, r.x + r.width + 2, r.y - 2
				+ r.height);
	}

	@Override
	public void putBackground() {
		decorated.putBackground();
	}

	@Override
	public void putBody(AbstractList<Rectangle> body) {
		decorated.putBody(body);
		for (Rectangle tempRect : body) {
			decorateRectangle(tempRect);
		}
	}

	@Override
	public void setSnakeColor(Color color) {
		decorated.setSnakeColor(color);
	}

	@Override
	public void putBonus(AbstractList<Rectangle> bonus) {
		decorated.putBonus(bonus);
		for (Rectangle tempRect : bonus) {
			decorateRectangle(tempRect);
		}
	}

	@Override
	public void outside() {
		decorated.outside();

	}

	@Override
	public void stop() {
		decorated.stop();
	}

	// YOUR CODE HERE
	// Overwrite some of the inherited/implemented methods.
}
