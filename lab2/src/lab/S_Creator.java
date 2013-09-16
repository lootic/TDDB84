package lab;

import java.awt.event.KeyEvent;

public abstract class S_Creator {
	public abstract void createRoomAt(int x, int y);

	public abstract void createWallAt(int x, int y, int dir);

	public abstract void createDoorAt(int x, int y, int dir);

	public abstract void createCorridoreAt(int x, int y, int dir);

	public abstract void createBoxDoorAt(int x, int y, int dir);

	private Room getRoomAdjacentTo(int x, int y, int dir) {
		switch(dir){
		case KeyEvent.VK_DOWN:break;
		case KeyEvent.VK_UP:break;
		case KeyEvent.VK_LEFT:break;
		case KeyEvent.VK_RIGHT:break;
		}
		return null;
	}
}
