package lab;


import java.awt.event.KeyEvent;	


public class S_Prototype implements S_Creator {
	private Room room = new Room(0, 0);
	private Door door = new Door(room, room);
	private BoxDoor boxDoor = new BoxDoor(room, room);
	private Wall wall = new Wall();
	
	@Override
	public BoxDoor createBoxDoor(Room roomA, Room roomB) {
		boxDoor = (BoxDoor) boxDoor.clone();
		boxDoor.move(roomA, roomB);
		return boxDoor;

	}
	@Override
	public Door createDoor(Room roomA, Room roomB) {
		door = (Door) door.clone();
		door.move(roomA, roomB);
		return door;
	}
	@Override
	public Room createRoom(int x, int y) {
		room = (Room) room.clone();
		room.move(x, y);
		return room;
	}
	@Override
	public Wall createWall(int dir) {
		wall = (Wall) wall.clone();
		wall.orientate(dir);
		return wall;
	}
}
