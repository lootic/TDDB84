package lab;

import java.util.ArrayList;

public class S_Mediator{
	
	private ArrayList<LadyBird> ladyBirds = new ArrayList<LadyBird>();
	
	public S_Mediator() {
	}
	
	public void registerLadyBird(LadyBird ladyBird) {
		ladyBirds.add(ladyBird);
		ladyBird.setMediator(this);
	}
	
	public void unregisterLadyBird(LadyBird ladyBird) {
		ladyBirds.remove(ladyBird);
		ladyBird.setMediator(null);
	}
	
	/**
	 * Time complexity: always O((|ladyBirds|^2+|ladyBirds|)/2)
	 */
	public synchronized void handleCollisions() {
		for (int i = 0; i < ladyBirds.size(); ++i) {
			for (int j = i + 1; j < ladyBirds.size(); ++j) {
				ladyBirds.get(i).collide(ladyBirds.get(j));
			}
		}
	}
}
