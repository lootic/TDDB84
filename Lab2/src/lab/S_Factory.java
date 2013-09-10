package lab;

public class S_Factory {
	public S_Factory() {
		// empty
	}

	public Door createDoor()
	{
		return new Door();
	}

	public Door createDoor(Room roomOneIn, Room roomTwoIn)
	{
		return new Door(roomOneIn, roomTwoIn);
	}

	public Door createDoor(Door door)
	{
		return new Door(door);
	}

	public BoxDoor createBoxDoor()
	{
		return new BoxDoor();
	}
	
	public BoxDoor createBoxDoor(Room roomOneIn, Room roomTwoIn)
	{
		return new BoxDoor(roomOneIn, roomTwoIn);
	}

	public Wall createWall()
	{
		return new Wall();
	}
	
	public Wall createWall(int dir)
	{
		return new Wall(dir);
	}
	
	public Wall createWall(Wall wall)
	{
		return new Wall(wall);
	}

	public Room createRoom()
	{
		return new Room();
	}

	public Room createRoom(int x, int y)
	{
		return new Room(x, y);
	}
	
	public Room createRoom(Room room)
	{
		return new Room(room);
	}
}
