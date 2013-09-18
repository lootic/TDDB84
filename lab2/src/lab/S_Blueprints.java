package lab;

import java.awt.event.KeyEvent;

public class S_Blueprints {
	
	public static final Blueprint STABLE_3x3 = new Blueprint() {
		@Override
		public void construct(S_Creator creator) {
			// Creating the rooms for the stable
			Stable stable = Stable.instance();
			Room[][] rooms = new Room[3][3];
			Door door;

			rooms[0][0] = creator.createRoom(0, 0);
			rooms[1][0] = creator.createRoom(1, 0);
			rooms[2][0] = creator.createRoom(2, 0);
			rooms[0][1] = creator.createRoom(0, 1);
			rooms[1][1] = creator.createRoom(1, 1);
			rooms[2][1] = creator.createRoom(2, 1);
			rooms[0][2] = creator.createRoom(0, 2);
			rooms[1][2] = creator.createRoom(1, 2);
			rooms[2][2] = creator.createRoom(2, 2);

			// Creating West outerwalls
			rooms[0][0].setSide(creator.createWall(KeyEvent.VK_LEFT));
			rooms[0][1].setSide(creator.createWall(KeyEvent.VK_LEFT));
			rooms[0][2].setSide(creator.createWall(KeyEvent.VK_LEFT));

			// Creating North outerwalls
			rooms[0][0].setSide(creator.createWall(KeyEvent.VK_UP));
			rooms[1][0].setSide(creator.createWall(KeyEvent.VK_UP));
			rooms[2][0].setSide(creator.createWall(KeyEvent.VK_UP));

			// Creating East outerwalls
			rooms[2][0].setSide(creator.createWall(KeyEvent.VK_RIGHT));
			rooms[2][1].setSide(creator.createWall(KeyEvent.VK_RIGHT));
			rooms[2][2].setSide(creator.createWall(KeyEvent.VK_RIGHT));

			// Creating South outerwalls
			rooms[0][2].setSide(creator.createWall(KeyEvent.VK_DOWN));
			rooms[1][2].setSide(creator.createWall(KeyEvent.VK_DOWN));
			rooms[2][2].setSide(creator.createWall(KeyEvent.VK_DOWN));

			// Creating North innerwalls
			rooms[0][0].setSide(creator.createWall(KeyEvent.VK_RIGHT));
			rooms[1][0].setSide(creator.createWall(KeyEvent.VK_LEFT));

			rooms[1][0].setSide(creator.createWall(KeyEvent.VK_RIGHT));
			rooms[2][0].setSide(creator.createWall(KeyEvent.VK_LEFT));

			// Creating South innerwalls
			rooms[0][2].setSide(creator.createWall(KeyEvent.VK_RIGHT));
			rooms[1][2].setSide(creator.createWall(KeyEvent.VK_LEFT));

			rooms[1][2].setSide(creator.createWall(KeyEvent.VK_RIGHT));
			rooms[2][2].setSide(creator.createWall(KeyEvent.VK_LEFT));

			// Creating North Doors
			door = creator.createBoxDoor(rooms[0][0], rooms[0][1]);
			rooms[0][0].setSide(door);
			rooms[0][1].setSide(door);

			door = creator.createBoxDoor(rooms[1][0], rooms[1][1]);
			rooms[1][0].setSide(door);
			rooms[1][1].setSide(door);

			door = creator.createDoor(rooms[2][0], rooms[2][1]);
			rooms[2][0].setSide(door);
			rooms[2][1].setSide(door);

			// Creating Soth Doors
			door = creator.createBoxDoor(rooms[0][2], rooms[0][1]);
			rooms[0][2].setSide(door);
			rooms[0][1].setSide(door);

			door = creator.createBoxDoor(rooms[1][2], rooms[1][1]);
			rooms[1][2].setSide(door);
			rooms[1][1].setSide(door);

			door = creator.createBoxDoor(rooms[2][2], rooms[2][1]);
			rooms[2][2].setSide(door);
			rooms[2][1].setSide(door);

			// Create corridor
			rooms[0][1].setSide(rooms[1][1]);
			rooms[1][1].setSide(rooms[0][1]);

			rooms[2][1].setSide(rooms[1][1]);
			rooms[1][1].setSide(rooms[2][1]);

			// Add rooms
			stable.addRoom(rooms[0][0]);
			stable.addRoom(rooms[0][1]);
			stable.addRoom(rooms[0][2]);
			stable.addRoom(rooms[1][0]);
			stable.addRoom(rooms[1][1]);
			stable.addRoom(rooms[1][2]);
			stable.addRoom(rooms[2][0]);
			stable.addRoom(rooms[2][1]);
			stable.addRoom(rooms[2][2]);
		}
	};

	public interface Blueprint {
		public void construct(S_Creator creator);
	}
}
