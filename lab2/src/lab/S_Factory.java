package lab;

/**
 * This is a factory that implements the S_Creator(AbstractFactory)
 */
public class S_Factory implements S_Creator {

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
