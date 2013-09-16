package lab;

import java.awt.event.KeyEvent;

public class S_Prototype extends S_Creator {

	private BoxDoor boxDoor = new BoxDoor();
	private Door door = new Door();
	private Room room = new Room();
	private Wall wall = new Wall();

	protected void setDoorAt(Room roomA, Room roomB, Door door) 
	{
		roomA.setSide(door);
		roomB.setSide(door);
		door.move(roomA, roomB);
	}
	
	@Override
	public void createRoomAt(int x, int y) {
		room = (Room) room.clone();
		room.move(x, y);
		Stable.instance().addRoom(room);
	}

	@Override
	public void createBoxDoorBetween(Room roomA, Room roomB) {
		setDoorAt(roomA, roomB, (Door) boxDoor.clone());
	}

	private void connectRoomsAndSetNext(Room from, Room to, int dir) {
		from.setSide(to);
		to.setSide(from);
		from = to;
		to = getRoomAdjacentTo(from.getX(), to.getX(), dir);
	}

	@Override
	public void createCorridoreAt(Room from, Room to) {
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

	@Override
	public void createDoorBetween(Room roomA, Room roomB) {
		setDoorAt(roomA, roomB, (Door) door.clone());
	}

	@Override
	public void createInnerWall(Room room, int dir) {
		wall = (Wall) wall.clone();
		wall.orientate(dir);
		room.setSide(wall);
	}

	@Override
	public void createOuterWall(Room room, int dir) {
		wall = (Wall) wall.clone();
		wall.orientate(dir);
		room.setSide(wall);
	}

}
