package lab;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 * This is the class where the students should make changes. Does the double
 * buffering of the graphics.
 * 
 * @author Peter Sunnergren
 */
public class StablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Image offscreen;
	private static Graphics buffer;
	private Dimension dimension;
	private ArrayList<Horse> horses;

	public StablePanel(StableApplet a) {

		dimension = new Dimension(400, 400);
		setMinimumSize(dimension);
		offscreen = a.createImage((int) dimension.getWidth(),
				(int) dimension.getHeight());
		buffer = offscreen.getGraphics();
		horses = new ArrayList<Horse>();
	}

	/**
	 * This method draws the stable, the person and the horses.
	 */
	public void paint(Graphics g) {

		buffer.clearRect(0, 0, dimension.width, dimension.height);
		Stable.instance().paint(buffer);

		Iterator<Horse> iterator = horses.iterator();

		while (iterator.hasNext())
			iterator.next().paint(buffer);

		Person.instance().paint(buffer);

		g.drawImage(offscreen, 0, 0, this);
	}

	/**
	 * This is called when the user presses 'h' and adds another horse.
	 */
	public void addHorse(StableApplet a) {

		Horse horse1 = new Horse(a);
		horses.add(horse1);
		new Thread(horse1).start();
	}

	/**
	 * Constructs the stable using the default implementation. In this method
	 * the students can see how a small stable is constructed in a nonflexible
	 * manner.
	 */
	public void defaultConstruction() {

		Room room1 = new Room(0, 0);
		Room room2 = new Room(1, 0);
		Room room3 = new Room(0, 1);
		Room room4 = new Room(1, 1);

		Door door13 = new Door(room1, room3);
		BoxDoor boxDoor24 = new BoxDoor(room2, room4);
		BoxDoor boxDoor34 = new BoxDoor(room3, room4);

		Stable.instance().addRoom(room1);
		Stable.instance().addRoom(room4);
		Stable.instance().addRoom(room3);
		Stable.instance().addRoom(room2);

		room1.setSide(new Wall(KeyEvent.VK_UP));
		room1.setSide(new Wall(KeyEvent.VK_LEFT));

		room2.setSide(new Wall(KeyEvent.VK_UP));
		room2.setSide(new Wall(KeyEvent.VK_RIGHT));

		room3.setSide(new Wall(KeyEvent.VK_DOWN));
		room3.setSide(new Wall(KeyEvent.VK_LEFT));

		room4.setSide(new Wall(KeyEvent.VK_RIGHT));
		room4.setSide(new Wall(KeyEvent.VK_DOWN));

		room1.setSide(room2);
		room2.setSide(room1);

		room1.setSide(door13);
		room3.setSide(door13);

		room2.setSide(boxDoor24);
		room4.setSide(boxDoor24);

		room3.setSide(boxDoor34);
		room4.setSide(boxDoor34);
	}


	
	/**
	 * Construction is made using an Factory.
	 */
	public void factoryConstruction(){}

	/**
	 * Construction using the Builder pattern.
	 */
	public void builderConstruction() {}

	
	S_Creator prototype = new S_Prototype();
	
	/**
	 * Construction using prototypes.
	 */
	public void prototypeConstruction()
	{
		Stable stable = Stable.instance();
		
		// Creating the rooms for the stable
		prototype.createRoomAt(0, 0);
		prototype.createRoomAt(1, 0);
		prototype.createRoomAt(2, 0);
		prototype.createRoomAt(0, 1);
		prototype.createRoomAt(1, 1);
		prototype.createRoomAt(2, 1);
		prototype.createRoomAt(0, 2);
		prototype.createRoomAt(1, 2);
		prototype.createRoomAt(2, 2);
		
		// Creating West outerwalls
		prototype.createOuterWall(stable.getRoom(0, 0), KeyEvent.VK_LEFT);
		prototype.createOuterWall(stable.getRoom(0, 1), KeyEvent.VK_LEFT);
		prototype.createOuterWall(stable.getRoom(0, 2), KeyEvent.VK_LEFT);		
		// Creating North outerwalls
		prototype.createOuterWall(stable.getRoom(0, 0), KeyEvent.VK_UP);
		prototype.createOuterWall(stable.getRoom(1, 0), KeyEvent.VK_UP);
		prototype.createOuterWall(stable.getRoom(2, 0), KeyEvent.VK_UP);		
		// Creating East outerwalls
		prototype.createOuterWall(stable.getRoom(2, 0), KeyEvent.VK_RIGHT);
		prototype.createOuterWall(stable.getRoom(2, 1), KeyEvent.VK_RIGHT);
		prototype.createOuterWall(stable.getRoom(2, 2), KeyEvent.VK_RIGHT);		
		// Creating South outerwalls
		prototype.createOuterWall(stable.getRoom(0, 2), KeyEvent.VK_DOWN);
		prototype.createOuterWall(stable.getRoom(1, 2), KeyEvent.VK_DOWN);
		prototype.createOuterWall(stable.getRoom(2, 2), KeyEvent.VK_DOWN);

		// Creating North innerwalls
		prototype.createInnerWall(stable.getRoom(0, 0), KeyEvent.VK_RIGHT);
		prototype.createInnerWall(stable.getRoom(1, 0), KeyEvent.VK_RIGHT);
		// Creating South innerwalls
		prototype.createInnerWall(stable.getRoom(0, 2), KeyEvent.VK_RIGHT);
		prototype.createInnerWall(stable.getRoom(1, 2), KeyEvent.VK_RIGHT);	
		
		// Creating North boxDoors
		prototype.createBoxDoorBetween(stable.getRoom(0, 0), stable.getRoom(0, 1));
		
		// Creating Corridor in middle
		prototype.createCorridorBetween(stable.getRoom(0, 1), stable.getRoom(1, 1));
		prototype.createCorridorBetween(stable.getRoom(1, 1), stable.getRoom(2, 1));	
		
	}

	public void deconstruct() {

		Iterator<Horse> iterator = horses.iterator();

		while (iterator.hasNext())
			iterator.next().stop();

		horses.clear();
		Stable.instance().deconstruct();
	}

	// YOUR CODE HERE
	// May be something else is missing?
}
