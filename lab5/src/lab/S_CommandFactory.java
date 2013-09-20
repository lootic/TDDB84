package lab;

import java.awt.Color;

public class S_CommandFactory {

	private S_CommandFactory() {
		// make it impossible to instansiate the class
	}

	public static AbstractCommand createAddCommand() {
		AbstractCommand command = new AbstractCommand() {

			private LadyBird bird;

			@Override
			public void Execute() {
				bird = new LadyBird();
				bird.setColors(getRandomColor(), getRandomColor());
				bird.setSize(getRandomBodySize());
				LadyBirdManager.instance().addLadyBird(bird);
			}

			@Override
			public void Unexecute() {
				LadyBirdManager.instance().removeLadyBird(bird);
				bird = null;
			}
		};

		return command;
	}

	public static AbstractCommand createRemoveCommand() {
		AbstractCommand command = new AbstractCommand() {
			private LadyBird bird;

			@Override
			public void Unexecute() {
				LadyBirdManager.instance().addLadyBird(bird);
			}

			@Override
			public void Execute() {
				bird = LadyBirdManager.instance().getMarkedLadyBird();
				LadyBirdManager.instance().removeLadyBird(bird);
			}
		};

		return command;
	}

	public static AbstractCommand createBiggerCommand() {
		AbstractCommand command = new AbstractCommand() {

			private LadyBird ladyBird;

			@Override
			public void Unexecute() {
				ladyBird.setSize(ladyBird.getSize()-1);
			}

			@Override
			public void Execute() {
				ladyBird =  LadyBirdManager.instance().getMarkedLadyBird();
				ladyBird.setSize(ladyBird.getSize()+1);
			}
		};

		return command;
	}

	public static AbstractCommand createSmallerCommand() {
		AbstractCommand command = new AbstractCommand() {

			private LadyBird ladyBird;
			
			@Override
			public void Unexecute() {
				ladyBird.setSize(ladyBird.getSize()+1);
			}

			@Override
			public void Execute() {
				ladyBird =  LadyBirdManager.instance().getMarkedLadyBird();
				ladyBird.setSize(ladyBird.getSize()-1);
			}
		};

		return command;
	}

	public static AbstractCommand createColorCommand() {
		AbstractCommand command = new AbstractCommand() {
			private LadyBird ladyBird;
			private Color bodyColor;
			private Color dotColor;
			
			
			@Override
			public void Unexecute() {
				ladyBird.setColors(bodyColor, dotColor);
			}

			@Override
			public void Execute() {
				ladyBird =  LadyBirdManager.instance().getMarkedLadyBird();
				bodyColor = ladyBird.getColor();
				dotColor = ladyBird.getDotColor();
				ladyBird.setColors(getRandomColor(), getRandomColor());
			}
		};

		return command;
	}

	private static Color getRandomColor() {
		return new Color((int) (Math.random() * 255),
				(int) (Math.random() * 255), (int) (Math.random() * 255));
	}

	private static int getRandomBodySize() {
		return (int) (Math.random() * 20 + 5);
	}
}
