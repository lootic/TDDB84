package lab;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JApplet;

/**
 * The class managers the ladybirds on the farm.
 * 
 * @author Peter Sunnergren
 */
public class LadyBirdManager extends Thread implements S_Observer {

	private Vector<LadyBird> ladyBirds;
	private LadyBird markedLadyBird;
	private static JApplet applet;
	private static LadyBirdManager instance;
	private S_Mediator mediator;

	private LadyBirdManager() {
		mediator = new S_Mediator();
		ladyBirds = new Vector<LadyBird>();
	}

	/**
	 * Gets an instance of the manager.
	 * 
	 * @return The manager.
	 */
	public static LadyBirdManager instance() {

		if (instance != null)
			return instance;

		if (applet != null) {
			instance = new LadyBirdManager();
			return instance;
		} else {
			System.out.println("Please initialize with an applet.");
			return null;
		}
	}

	/**
	 * Sets the applet and must be called before the manager is used for the
	 * first time.
	 * 
	 * @param a
	 *            The applet.
	 */
	public static void setApplet(JApplet a) {
		applet = a;
	}

	/*
	 * Made some changes to speed up the program a little, we now only sleep
	 * when there is nothing to calculate.
	 */
	/**
	 * The thread that periodically repaints the farm.
	 */
	public void run() {
		long sleepDuration;
		while (true) {
			sleepDuration = System.currentTimeMillis(); // added
			Iterator<LadyBird> iterator = ladyBirds.iterator();

			while (iterator.hasNext()) {
				LadyBird bird = iterator.next();
				bird.nextAction();
			}

			mediator.handleCollisions();

			applet.repaint();
			sleepDuration = 100 - System.currentTimeMillis() + sleepDuration; // added
			try {
				if (sleepDuration > 0) {
					Thread.sleep(sleepDuration);
				}
			} catch (InterruptedException e) {
				System.out.println("Interrupted.");
			}
		}
	}

	/**
	 * Creates a ladybird.
	 * 
	 * @return The new ladybird so it can be saved outside the manager.
	 */
	public LadyBird createLadyBird() {

		LadyBird bird = new LadyBird();
		ladyBirds.add(bird);
		mediator.registerLadyBird(bird);

		return bird;
	}

	/**
	 * Removes a ladybird.
	 */
	public void removeLadyBird(LadyBird bird) {

		ladyBirds.removeElement(bird);

		if (markedLadyBird == bird)
			markedLadyBird = null;
	}

	/**
	 * Adds a ladybird to the farm.
	 */
	public void addLadyBird(LadyBird bird) {
		ladyBirds.add(bird);
	}

	/**
	 * Marks the ladybird at the position described by x and y. If there is not
	 * any ladybird at position or the ladybird is the same as the old marked
	 * ladybird the marked ladybird variable is set to null.
	 * 
	 * @param x
	 *            The X coordinate.
	 * @param y
	 *            The Y coordinate.
	 */
	public void markLadyBirdAt(int x, int y) {

		Iterator<LadyBird> iter = ladyBirds.iterator();
		while (iter.hasNext()) {
			LadyBird bird = iter.next();

			if (Point2D.distance(bird.getX(), bird.getY(), x, y) > bird
					.getSize())
				continue;

			if (markedLadyBird != bird) {
				markedLadyBird = bird;
				return;
			}
		}

		markedLadyBird = null;
	}

	/**
	 * Checks if there is a ladybird at the position specified by x and y.
	 * 
	 * @param x
	 *            The X coordinate.
	 * @param y
	 *            The Y coordinate.
	 * @return True if it is one there.
	 */
	public boolean isLadyBirdAt(int x, int y) {

		Iterator<LadyBird> iter = ladyBirds.iterator();
		while (iter.hasNext()) {
			LadyBird bird = iter.next();
			if (Point2D.distance(bird.getX(), bird.getY(), x, y) < bird
					.getSize())
				return true;
		}

		return false;
	}

	/**
	 * Gets the marked ladybird.
	 */
	public LadyBird getMarkedLadyBird() {

		return markedLadyBird;
	}

	/**
	 * Draws the ladybirds and a white border around the marked ladybird.
	 */
	public void paint(Graphics g) {
		Iterator<LadyBird> iter = ladyBirds.iterator();
		while (iter.hasNext())
			iter.next().paint(g);

		if (markedLadyBird == null)
			return;

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(2));
		g2.drawOval(markedLadyBird.getX() - markedLadyBird.getSize() + 1,
				markedLadyBird.getY() - markedLadyBird.getSize() + 1,
				2 * markedLadyBird.getSize() - 1,
				2 * markedLadyBird.getSize() - 1);
	}

	@Override
	public void settingsChanged(LadyBirdSettings newSettings) {
		for (LadyBird ladyBird : ladyBirds) {
			ladyBird.setSettings(newSettings);
		}
	}
}
