package lab;

public class S_Prototype implements S_Creator{
	
	private BoxDoor boxDoor;

	@Override
	public void createBoxDoorAt(int x, int y, int dir) {
		if(boxDoor == null){
			boxDoor = new BoxDoor();
		}
		boxDoor = (BoxDoor) boxDoor.clone();
		getRoom(x, y, dir);
	}

	@Override
	public void createCorridoreAt(int x, int y, int dir, int length) {
	}

	@Override
	public void createDoorAt(int x, int y, int dir) {
	}

	@Override
	public void createRoomAt(int x, int y) {
	}

	@Override
	public void createWallAt(int x, int y, int dir) {		
	}
}
