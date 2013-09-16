package lab;

public class S_Prototype extends S_Creator{
	
	private BoxDoor boxDoor = new BoxDoor();
	private Door door = new Door();
	private Room room = new Room();
	private Wall wall = new Wall();
	
	protected void createDoorAt(int x, int y, int dir, Door door){
		door = (Door) door.clone();
		Stable.instance().getRoom(x, y).setSide(door);
		getRoomAdjacentTo(x, y, dir).setSide(door);
		door.move(Stable.instance().getRoom(x, y), getRoomAdjacentTo(x, y, dir));
	}

	@Override
	public void createBoxDoorAt(int x, int y, int dir) {
		createDoorAt(x, y, dir, boxDoor);
	}

	@Override
	public void createCorridoreAt(int x, int y, int dir, int length) {
		for()
	}

	@Override
	public void createDoorAt(int x, int y, int dir) {
		createDoorAt(x, y, dir, door);
	}

	@Override
	public void createRoomAt(int x, int y) {
		room = (Room)room.clone();
		room.move(x, y);
		Stable.instance().addRoom(room);
	}

	@Override
	public void createWallAt(int x, int y, int dir) {
		
	}
}
