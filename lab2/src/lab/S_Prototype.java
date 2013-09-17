package lab;

<<<<<<< HEAD
import java.awt.event.KeyEvent;

public class S_Prototype extends S_Creator
{
	
=======
public class S_Prototype implements S_Creating {
>>>>>>> 8a1f2b35f441d725cbe1067b0d6f72b07dd01bbc
	private Room room = new Room(0, 0);
	private Door door = new Door(room, room);
	private BoxDoor boxDoor = new BoxDoor(room, room);
	private Wall wall = new Wall();
	
	@Override
<<<<<<< HEAD
	public void createRoomAt(int x, int y)
	{
		Room room = (Room) this.room.clone();
		room.move(x, y);
		Stable.instance().addRoom(room);
=======
	public BoxDoor createBoxDoor(Room roomA, Room roomB) {
		boxDoor = (BoxDoor) boxDoor.clone();
		boxDoor.move(roomA, roomB);
		return boxDoor;
>>>>>>> 8a1f2b35f441d725cbe1067b0d6f72b07dd01bbc
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
