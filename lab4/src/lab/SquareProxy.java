package lab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.AbstractList;
import java.util.NoSuchElementException;

/**
 * This is the class where the students implements the proxy for the square.
 * 
 * @author Peter Sunnergren
 */
public class SquareProxy extends AbstractSquare {

	private boolean open = true;
	private Square realSquare;

	// YOUR CODE HERE
	// Any missing methods?
	// END OF YOUR CODE

	public SquareProxy() {
	}

	/**
	 * Gets the marked shape. Checks this shape if it is open.
	 * 
	 * @return This if it is marked and null otherwise.
	 */
	public AbstractShape getMarkedShape(int cx, int cy) {

		if ((getX() > cx) || (getX() + getWidth() < cx) || (getY() > cy)
				|| (getY() + getHeight() < cy))
			return null;

		AbstractShape shape = null;

		if (!open) {
			open = true;
			shape = this;
		} else
		{
			// YOUR CODE HERE
			// Any additions?
			// END OF YOUR CODE

			if (shape == null)
			{
				open = false;
				shape = this;
			}
		}

		return shape;
	}

	/**
	 * Draws the proxy.
	 */
	public void paint(Graphics g) {

		if (open) {
			// YOUR CODE HERE
			// Any changes?
			g.setColor(Color.orange);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			// END OF YOUR CODE
		} else {
			g.setColor(Color.black);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
		}
	}

	/**
	 * Draws the children of the proxy.
	 */
	public void paintChildren(Graphics g) {

		// YOUR CODE HERE
	}

	/**
	 * Adds the proxy to the list of shapes. Only adds the children if the proxy
	 * is open.
	 */
	public AbstractList<AbstractShape> getListOfShapes(
			AbstractList<AbstractShape> list) {
		list.add(this);
		return list;
	}

	/**
	 * Accepts a Visitor.
	 */
	public void accept(AbstractVisitor v) {
		v.visit(this);
	}

	/**
	 * Adds a child.
	 */
	public void addChild(AbstractShape child) {

	}

	/**
	 * Used to check if there are any children.
	 */
	public boolean hasChildren() {
		return false;
		//return open ? realSquare.hasChildren() : false;
	}

	/**
	 * Gets the last child in the list of children.
	 */
	public AbstractShape getLastChild() {
		if (!open) {
			throw new NoSuchElementException();
		}
		return realSquare.getLastChild();
		// END OF YOUR CODE
	}
}
