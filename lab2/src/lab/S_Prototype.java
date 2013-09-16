package lab;

import java.awt.event.KeyEvent;

public class S_Prototype extends S_Creator {

	
	private Room room = new Room(0, 0);
	private Door door = new Door(room, room);
	private BoxDoor boxDoor = new BoxDoor(room, room);
	private Wall wall = new Wall();

	protected void setDoorAt(Room roomA, Room roomB, Door door) 
	{
		roomA.setSide(door);
		roomB.setSide(door);
		door.move(roomA, roomB);
	}
	
	@Override
	public void createRoomAt(int x, int y) {
		Room room = (Room) this.room.clone();
		room.move(x, y);
		Stable.instance().addRoom(room);
	}

	@Override
	public void createBoxDoorBetween(Room roomA, Room roomB) {
		setDoorAt(roomA, roomB, (Door) boxDoor.clone());
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
		
		wall = (Wall) wall.clone();
		wall.orientate(getOppositeDirection(dir));
		getRoomAdjacentTo(room.getX(), room.getY(), dir).setSide(wall);
	}

	@Override
	public void createOuterWall(Room room, int dir) {
		wall = (Wall) wall.clone();
		wall.orientate(dir);
		room.setSide(wall);
	}

}
