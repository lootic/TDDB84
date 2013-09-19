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

	private void createRealSquare() {
		realSquare = new Square();
	}

	public SquareProxy() {
		createRealSquare();
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
		} else {
			if (shape == null) {
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
			g.setColor(Color.orange);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			paintChildren(g);
		} else {
			g.setColor(Color.black);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
		}
	}

	/**
	 * Draws the children of the proxy.
	 */
	public void paintChildren(Graphics g) {
		realSquare.paintChildren(g);
	}

	/**
	 * Adds the proxy to the list of shapes. Only adds the children if the proxy
	 * is open.
	 */
	public AbstractList<AbstractShape> getListOfShapes(
			AbstractList<AbstractShape> list) {
		if (open) { // added
			list.add(this);
			realSquare.getListOfShapes(list); // added
			list.remove(realSquare);
			return list;
		} else { // added
			list.add(this); // added
			return list; // added
		}
	}

	/**
	 * Accepts a Visitor.
	 */
	public void accept(AbstractVisitor v) {
		// added
		v.visit(this);
		if (hasChildren()) {
			AbstractShape shape = realSquare.getLastChild();
			while (shape != null) {
				shape.accept(v);
				shape = shape.getSibling();
			}
		}
	}

	/**
	 * Adds a child.
	 */
	public void addChild(AbstractShape child) {
		realSquare.addChild(child);
	}

	/**
	 * Used to check if there are any children.
	 */
	public boolean hasChildren() {
		return open ? realSquare.hasChildren() : false;
	}

	/**
	 * Gets the last child in the list of children.
	 */
	public AbstractShape getLastChild() {
		if (!open) {
			throw new NoSuchElementException();
		}
		return realSquare.getLastChild();
	}
}
