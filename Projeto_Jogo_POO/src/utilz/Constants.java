package utilz;

import main.Game;

public class Constants {
	
	public static class EnemyConstants {
		public static final int CAVEIRA = 0;
		public static final int SNAKE = 10;
		public static final int BIXOV = 20;
		public static final int BIXOR = 30;

		public static final int IDLE = 0;
		public static final int STATE1 = 1;

		public static final int ENEMY_WIDTH_DEFAULT = 16;
		public static final int ENEMY_HEIGHT_DEFAULT = 16;

		public static final int ENEMY_WIDTH = (int) (ENEMY_WIDTH_DEFAULT * Game.SCALE);
		public static final int ENEMY_HEIGHT = (int) (ENEMY_HEIGHT_DEFAULT * Game.SCALE);

		public static int GetSpriteAmount(int enemy_type, int enemy_state) {

			switch (enemy_type) {
			case CAVEIRA:
				switch (enemy_state) {
				case IDLE:
					return 1;
				case STATE1:
					return 3;
				}
			case SNAKE:
				case 2:
					return 4;
			case BIXOR:
				switch (enemy_state) {
				case IDLE:
					return 1;
				case STATE1:
					return 3;
				}
			case BIXOV:
				switch (enemy_state) {
				case IDLE:
					return 1;
				case STATE1:
					return 3;
				}
					
			}

			return 0;
			
			

		}

	}
	
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