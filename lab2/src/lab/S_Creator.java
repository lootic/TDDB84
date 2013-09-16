package lab;

import java.awt.event.KeyEvent;

public abstract class S_Creator {
	public abstract void createRoomAt(int x, int y);

	public abstract void createOuterWall(Room room, int dir);

	public abstract void createInnerWall(Room room, int dir);

	public abstract void createDoorBetween(Room roomA, Room roomB);

	public abstract void createBoxDoorBetween(Room roomA, Room roomB);
	
	public  void createCorridorBetween(Room roomA, Room roomB){
		roomA.setSide(roomB);
		roomB.setSide(roomA);
	}
	
	public int getOppositeDirection(int dirIn) {
		switch (dirIn) {
		case KeyEvent.VK_RIGHT:
			return KeyEvent.VK_LEFT;
		case KeyEvent.VK_LEFT:
			return KeyEvent.VK_RIGHT;
		case KeyEvent.VK_UP:
			return KeyEvent.VK_DOWN;
		case KeyEvent.VK_DOWN:
			return KeyEvent.VK_UP;
		default:
			return dirIn;
		}
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
