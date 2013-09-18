package lab;

import java.awt.Graphics;

/**
 * An abstract class describing how a visitor looks like.
 * 
 * @author Peter Sunnergren
 */
public abstract class AbstractVisitor
{

	protected int numberOfVisits = 0;
	protected Graphics g;

	/**
	 * Visits a square.
	 */
	public abstract void visit(Square s);

	/**
	 * Visits a rectangle.
	 */
	public abstract void visit(Rectangle r);

	/**
	 * Visits a circle.
	 */
	public abstract void visit(Circle c);

	/**
	 * Visits a triangle.
	 */
	public abstract void visit(Triangle t);
	

	public abstract void visit(SquareProxy s);

	/**
	 * Sets the number of visits to 0
	 */
	public void resetVisitCount() {
		numberOfVisits = 0;
	}

	/**
	 * Find out how many shapes that has been visited since last
	 * resetVisitCount()
	 * 
	 * @return the number of shapes visited
	 */
	public int numberOfVisits() {
		return numberOfVisits;
	}

	public void setGraphics(Graphics g) {
		this.g = g;
	}
}
