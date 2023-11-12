package utilz;

import main.Game;

public class Constants {
	
    public static final int ANI_SPEED = 15;

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
	
    public static class EnemyConstants {
        
        public static final int SNAKE = 0;
        public static final int CAVEIRA = 10;
        public static final int BIXO_VERDE = 20;
        public static final int BIXO_ROSA = 30;

        public static final int IDLE = 0;
        public static final int MOVING = 1;

        public static int GetSpriteAmount(int enemy_type, int enemy_state) {

            switch (enemy_type) {

            case CAVEIRA:
                switch (enemy_state) {

                    case IDLE -> {
                        return 1;
                    }

                    case MOVING -> {
                        return 3;
                    }
                }


            case BIXO_VERDE:
                switch(enemy_state) {

                    case IDLE -> {
                        return 4;
                    }

                    case MOVING -> {
                        return 2;
                    }
                }
            }

            return 0;
        }
    }
        
    public static class ObjectConstants {

        public static final int CORACAO = 0;
        public static final int CAIXA = 10;
        public static final int AGUA = 20;
        public static final int BAU = 30;
        public static final int PORTA = 40;
        public static final int TIRO = 50;
        public static final int BOLA = 60;
        public static final int FOGO = 70;
        

        public static int GetSpriteAmount(int object_type) {

            switch(object_type){
                case AGUA -> {
                    return 6;
                }               
            }
            return 0;      
        }
    }
}