package utilz;

import main.Game;

public class Constants {
	
	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 719;
			public static final int B_HEIGHT_DEFAULT = 262;
			public static final int B_WIDTH = (int) (96 * Game.SCALE);
			public static final int B_HEIGHT = (int) (48 * Game.SCALE);
		}
	}

	public static class Directions {
		public static final int LEFT = 1;
		public static final int UP = 2;
		public static final int RIGHT = 3;
		public static final int DOWN = 0;
	}
}