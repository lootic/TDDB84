package lab;

//Not really a factory, but with new instructions we need to do it this way to be coherent with other creators
public class S_Factory extends S_Creator{

	@Override
	public void createBoxDoorBetween(Room roomA, Room roomB) {		
		BoxDoor door = new BoxDoor(roomA, roomB);
		roomA.setSide(door);
		roomB.setSide(door);
	}

	@Override
	public void createDoorBetween(Room roomA, Room roomB) {
		Door door = new Door(roomA, roomB);
		roomA.setSide(door);
		roomB.setSide(door);
	}

	@Override
	public void createInnerWall(Room room, int dir) {
		Wall wall = new Wall(dir);
		room.setSide(wall);
		
		wall = new Wall(getOppositeDirection(dir));
		room.setSide(wall);
	}

	@Override
	public void createOuterWall(Room room, int dir) {
		Wall wall = new Wall(dir);
		room.setSide(wall);
	}

	@Override
	public void createRoomAt(int x, int y) {
		Room room = new Room(x, y);
		Stable.instance().addRoom(room);
	}

}
