package lab;


/**
 * This interface is a Abstract_Factory that
 * our Prototype and factory will implementww
 */
public interface S_Creator {
	public Room createRoom(int x, int y);

	public Door createDoor(Room roomA, Room roomB);

	public BoxDoor createBoxDoor(Room roomA, Room roomB);

	public Wall createWall(int dir);
}
