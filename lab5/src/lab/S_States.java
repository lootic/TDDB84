package lab;

public class S_States {

	private S_States() {
		// make sure we can't call constructor and instanciate this object
	}

	public static final AbstractState GO_STATE = new AbstractState() {
		@Override
		public void nextAction(LadyBird bird) {
			if (bird.go()) {
				bird.setState(NULL_STATE);
			}
		}
	};

	public static final AbstractState TURN_STATE = new AbstractState() {
		@Override
		public void nextAction(LadyBird bird) {
			if (bird.turn()) {
				bird.setState(GO_STATE);
			}
		}
	};

	public static final AbstractState NULL_STATE = new NullState();
}
