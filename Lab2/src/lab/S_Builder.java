package lab;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

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

	public void createCorridor(int x, int y, int dir) {
		final int xLocal = x;
		final int yLocal = y;
		final int dirLocal = dir;

		sides.add(new Runnable() {
			@Override
			public void run() {

				Room roomA = rooms.get(
						Integer.toString(xLocal) + Integer.toString(yLocal));

				Room roomB = rooms.get(
						Integer.toString(xLocal) + Integer.toString(yLocal));
				switch (dirLocal) {

				case KeyEvent.VK_UP:
					break;
				case KeyEvent.VK_DOWN:

					break;
				case KeyEvent.VK_LEFT:

					break;
				case KeyEvent.VK_RIGHT:

					break;
				default:

					break;
				}
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
				System.out.println(Integer.toString(xLocal) + " "
						+ Integer.toString(yLocal));
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
