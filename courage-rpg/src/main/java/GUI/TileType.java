package GUI;

import java.util.List;

/**
 * @author Xin Lu
 */
public enum TileType {
        GRASS ("grass_1.jpg"),
        GROUND_1("ground_1.jpg"), GROUND_2("ground_2.jpg"), GROUND_3("ground_3.jpg"), GROUND_4("ground_4.jpg"), GROUND_5("ground_5.jpg"), GROUND_6("ground_6.jpg"),
        BRICK_1("brick_1.jpg"), BRICK_2("brick_2.jpg"), BRICK_3("brick_3.jpg"),
        STAIR_UP("stairs_up.png"), STAIR_DOWN("stairs_down.png"),
        METAL_1("metal_1.jpg"), METAL_2("metal_2.jpg"), METAL_3("metal_3.jpg"),
        SPECIAL_1("special_1.jpg"), SPECIAL_2("special_2.jpg"),
        HERO("hero.png"),
        NPC_2("npc_2.png"), NPC_3("npc_3.png"), NPC_4("npc_4.png"), NPC_5("npc_5.png"),
        ENEMY_1("enemy_1.png"), ENEMY_2("enemy_2.png"), ENEMY_3("enemy_3.png"), ENEMY_4("enemy_4.png"), ENEMY_5("enemy_5.png"), ENEMY_6("enemy_6.png"), ENEMY_7("enemy_7.png"), ENEMY_8("enemy_8.png"), ENEMY_9("enemy_9.png"), ENEMY_10("enemy_10.png"),
        WATER("water.png"), KEY("key.png"), MONEY("money.png");

        private final String fileName;

        public static boolean isTexture(TileType tileType) {
                return tileType == GRASS || tileType == GROUND_1 || tileType == GROUND_2 || tileType == GROUND_3 || tileType == GROUND_4 || tileType == GROUND_5 || tileType == GROUND_6 || tileType == BRICK_1 || tileType == BRICK_2 || tileType == BRICK_3 || tileType == METAL_1 || tileType == METAL_2 || tileType == METAL_3 || tileType == SPECIAL_1 || tileType == SPECIAL_2;
        }

        public static boolean isCharacter(TileType tileType) {
                return tileType == HERO || tileType == NPC_2 || tileType == NPC_3 || tileType == NPC_4 || tileType == NPC_5 || tileType == ENEMY_1 || tileType == ENEMY_2 || tileType == ENEMY_3 || tileType == ENEMY_4 || tileType == ENEMY_5 || tileType == ENEMY_6 || tileType == ENEMY_7 || tileType == ENEMY_8 || tileType == ENEMY_9 || tileType == ENEMY_10;
        }

        public static boolean isItem(TileType tileType) {
                return tileType == WATER || tileType == KEY || tileType == MONEY;
        }

        public static  boolean isMonster(TileType tileType) {
                return tileType == ENEMY_1 || tileType == ENEMY_2 || tileType == ENEMY_3 || tileType == ENEMY_4 || tileType == ENEMY_5 || tileType == ENEMY_6 || tileType == ENEMY_7 || tileType == ENEMY_8 || tileType == ENEMY_9 || tileType == ENEMY_10;
        }

        public static TileType getRandomBackground() {
                int random = (int) (Math.random() * 6);
                return switch (random) {
                        case 1 -> GROUND_2;
                        case 2 -> GROUND_3;
                        case 3 -> GROUND_4;
                        case 4 -> GROUND_5;
                        case 5 -> GROUND_6;
                        default -> GROUND_1;
                };
        }

        public static TileType getRandomTileType() {
//                List<TileType> pool = List.of(GROUND_1, GROUND_2, GROUND_3, GROUND_4, GROUND_5, GROUND_6,
//                        BRICK_1, BRICK_2, BRICK_3,
//                        METAL_1, METAL_2, METAL_3,
//                        SPECIAL_1, SPECIAL_2,
//                        NPC_2, NPC_3, NPC_4, NPC_5,
//                        ENEMY_1, ENEMY_2, ENEMY_3, ENEMY_4, ENEMY_5, ENEMY_6, ENEMY_7, ENEMY_8, ENEMY_9, ENEMY_10,
//                        WATER, KEY, MONEY);
                return values()[(int) (Math.random() * values().length)];
        }

        TileType(String fileName) {
                this.fileName = fileName;
        }

        public String toString() {
            return fileName;
        }
}
