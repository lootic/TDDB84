package lab;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * 
 * This is the builder class that has the methods
 * that you can use for populating a instance of a stable.
 * 
 * To make the builder fully declarative and not dependant
 * on the creation order of components it stores rooms and
 * sides internally and connects everything first when
 * createResult() is called. We do this by using the run
 * as a way to save code as variables, in a way similar
 * to functional programming.
 * 
 */
public class S_Builder {
	private Stable stable;
	private HashMap<String, Room> rooms = new HashMap<String, Room>();
	private ArrayList<Runnable> sides = new ArrayList<Runnable>();

	public S_Builder(Stable stable) {
		this.stable = stable;
	}

	public void addRoom(int x, int y) {
		Room room = new Room(x, y);
		rooms.put(Integer.toString(x) + Integer.toString(y), room);
		stable.addRoom(room);
	}

	public void addDoor(final int x, final int y, final int dir) {
		addAnyDoor(x, y, dir, false);
	}

	private void addAnyDoor(final int x, final int y, final int dir,
			final boolean isBoxDoor) {
		switch (dir) {
		case KeyEvent.VK_DOWN:
			sides.add(new Runnable() {
				@Override
				public void run() {
					Room roomA = rooms.get(Integer.toString(x)
							+ Integer.toString(y));
					Room roomB = rooms.get(Integer.toString(x)
							+ Integer.toString(y + 1));
					Door door = isBoxDoor ? new BoxDoor(roomA, roomB)
							: new Door(roomA, roomB);
					roomA.setSide(door);
					roomB.setSide(door);
				}
			});
			break;
		case KeyEvent.VK_UP:
			sides.add(new Runnable() {
				@Override
				public void run() {
					Room roomA = rooms.get(Integer.toString(x)
							+ Integer.toString(y));
					Room roomB = rooms.get(Integer.toString(x)
							+ Integer.toString(y - 1));
					Door door = isBoxDoor ? new BoxDoor(roomA, roomB)
							: new Door(roomA, roomB);
					roomA.setSide(door);
					roomB.setSide(door);
				}
			});
			break;
		case KeyEvent.VK_LEFT:
			sides.add(new Runnable() {
				@Override
				public void run() {
					Room roomA = rooms.get(Integer.toString(x)
							+ Integer.toString(y));
					Room roomB = rooms.get(Integer.toString(x - 1)
							+ Integer.toString(y));
					Door door = isBoxDoor ? new BoxDoor(roomA, roomB)
							: new Door(roomA, roomB);
					roomA.setSide(door);
					roomB.setSide(door);
				}
			});
			break;
		case KeyEvent.VK_RIGHT:
			sides.add(new Runnable() {
				@Override
				public void run() {
					Room roomA = rooms.get(Integer.toString(x)
							+ Integer.toString(y));
					Room roomB = rooms.get(Integer.toString(x + 1)
							+ Integer.toString(y));
					Door door = isBoxDoor ? new BoxDoor(roomA, roomB)
							: new Door(roomA, roomB);
					roomA.setSide(door);
					roomB.setSide(door);
				}
			});
			break;
		default:
			break;
		}
	}

	public void addBoxDoor(int x, int y, int dir) {
		addAnyDoor(x, y, dir, true);
	}

	public void setCorridor(int x, int y, int dir) {
		final int xLocal = x;
		final int yLocal = y;
		final int dirLocal = dir;

		sides.add(new Runnable() {
			@Override
			public void run() {

				Room roomA = rooms.get(Integer.toString(xLocal)
						+ Integer.toString(yLocal));
				Room roomB = null;

				switch (dirLocal) {

				case KeyEvent.VK_UP:
					roomB = rooms.get(Integer.toString(xLocal)
							+ Integer.toString(yLocal-1));
					break;
				case KeyEvent.VK_DOWN:
					roomB = rooms.get(Integer.toString(xLocal)
							+ Integer.toString(yLocal+1));

					break;
				case KeyEvent.VK_LEFT:
					roomB = rooms.get(Integer.toString(xLocal-1)
							+ Integer.toString(yLocal));

					break;
				case KeyEvent.VK_RIGHT:
					roomB = rooms.get(Integer.toString(xLocal+1)
							+ Integer.toString(yLocal));

					break;
				default:
					break;
				}
				roomA.setSide(roomB);
				roomB.setSide(roomA);
			}
		});
	}

	public void addWall(int x, int y, int dir) {
		final int xLocal = x;
		final int yLocal = y;
		final int dirLocal = dir;

		sides.add(new Runnable() {
			@Override
			public void run() {
				rooms.get(Integer.toString(xLocal) + Integer.toString(yLocal))
						.setSide(new Wall(dirLocal));
				;
			}
		});
	}

	public void createResult() {
		for (Runnable side : sides) {
			side.run();
		}
	}
}
