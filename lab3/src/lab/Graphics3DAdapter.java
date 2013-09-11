package lab;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.AbstractList;

// YOUR CODE HERE
// Extends? Implements?
class Graphics3DAdapter extends AbstractGraphics implements Renderer{
	
	private Graphics3D g3d = new Graphics3D();

	@Override
	public void putBackground() {
		g3d.printBase(Color.DARK_GRAY);
		
	}

	@Override
	public void putBody(AbstractList<Rectangle> body) {
		for(Rectangle tempRect : body)
		{
			g3d.printBox(new Box(tempRect.x, tempRect.y, tempRect.x + tempRect.width, tempRect.y + tempRect.height), snakeColor);
		}
	}

	@Override
	public void putBonus(AbstractList<Rectangle> bonus)
	{		
		for(Rectangle tempRect : bonus)
		{
			g3d.printBox(new Box(tempRect.x, tempRect.y, tempRect.x + tempRect.width, tempRect.y + tempRect.height), bonusColor);
		}	
	}

	@Override
	public void outside() {
		super.outside();
		
	}

	@Override
	public void stop()
	{
	}

	// YOUR CODE HERE
	// Overwrite some of the inherited/implemented methods.
}
