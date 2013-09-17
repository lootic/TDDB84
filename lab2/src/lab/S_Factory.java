package lab;

//Not really a factory, but with new instructions we need to do it this way to be coherent with other creators
public class S_Factory implements S_Creating {

	@Override
	public BoxDoor createBoxDoor(Room roomA, Room roomB) {
		return new BoxDoor(roomA, roomB);
	}

	@Override
	public Door createDoor(Room roomA, Room roomB) {
		return new Door(roomA, roomB);
	}

	@Override
	public Room createRoom(int x, int y) {
		return new Room(x, y);
	}

	@Override
	public Wall createWall(int dir) {
		return new Wall(dir);
	}

}
