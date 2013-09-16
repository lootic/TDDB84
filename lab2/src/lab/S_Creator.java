package lab;

import java.awt.event.KeyEvent;

public abstract class S_Creator {
	public abstract void createRoomAt(int x, int y);

	public abstract void createOuterWall(Room room, int dir);
	
	public abstract void createInnerWall(Room room, int dir);

	public abstract void createDoorBetween(Room roomA, Room roomB);

	public abstract void createBoxDoorBetween(Room roomA, Room roomB);
	
	
	private void connectRoomsAndSetNext(Room from, Room to, int dir)
	{
		from.setSide(to);
		to.setSide(from);
		from = to;
		to = getRoomAdjacentTo(from.getX(), to.getX(), dir);
	}	
	
	public void createCorridoreAt(Room from, Room to)
	{
		if (from.getX() == to.getX()) {
			Room smallestRoom = from.getY() < to.getY() ? from : to;
			Room nextRoom = getRoomAdjacentTo(smallestRoom.getX(), smallestRoom
					.getY(), KeyEvent.VK_DOWN);
			for (int i = 0; i < Math.abs(from.getY() - to.getY()); ++i) {
				connectRoomsAndSetNext(smallestRoom, nextRoom,
						KeyEvent.VK_RIGHT);
			}
		} else if (from.getY() == to.getY()) {
			Room smallestRoom = from.getX() < to.getX() ? from : to;
			Room nextRoom = getRoomAdjacentTo(smallestRoom.getX(), smallestRoom
					.getX(), KeyEvent.VK_RIGHT);
			for (int i = 0; i < Math.abs(from.getX() - to.getX()); ++i) {
				connectRoomsAndSetNext(smallestRoom, nextRoom,
						KeyEvent.VK_RIGHT);
			}
		}
		// else error input, ignore
	}

	protected Room getRoomAdjacentTo(int x, int y, int dir) {
		switch (dir) {
		case KeyEvent.VK_DOWN:
			return Stable.instance().getRoom(x, y + 1);
		case KeyEvent.VK_UP:
			return Stable.instance().getRoom(x, y - 1);
		case KeyEvent.VK_LEFT:
			return Stable.instance().getRoom(x - 1, y);
		case KeyEvent.VK_RIGHT:
			return Stable.instance().getRoom(x + 1, y);
		default:
			return null;
		}
	}
}
