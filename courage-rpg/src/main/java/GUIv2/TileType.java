package GUIv2;

/**
 * All kinds of tiles. Each tile corresponds to a cell in the game engine.
 * @author Xin Lu
 */
public enum TileType {
        GRASS ("grass_1.jpg"),
        GROUND_1("ground_1.jpg"), GROUND_2("ground_2.jpg"), GROUND_3("ground_3.jpg"), GROUND_4("ground_4.jpg"), GROUND_5("ground_5.jpg"), GROUND_6("ground_6.jpg"),
        BRICK_1("brick_1.jpg"), BRICK_2("brick_2.jpg"), BRICK_3("brick_3.jpg"),
        STAIR_UP("stairs_up.png"), STAIR_DOWN("stairs_down.png"),
        METAL_1("metal_1.jpg"), METAL_2("metal_2.jpg"), METAL_3("metal_3.jpg"),
        SPECIAL_1("door.png"), SPECIAL_2("door.png"),
        HERO("hero.png"),
        NPC_2("npc_2.png"), NPC_3("npc_3.png"), NPC_4("npc_4.png"), NPC_5("npc_5.png"),
        ENEMY_1("enemy_1.png"), ENEMY_2("enemy_2.png"), ENEMY_3("enemy_3.png"), ENEMY_4("enemy_4.png"), ENEMY_5("enemy_5.png"), ENEMY_6("enemy_6.png"), ENEMY_7("enemy_7.png"), ENEMY_8("enemy_8.png"), ENEMY_9("enemy_9.png"), ENEMY_10("enemy_10.png"),
        WATER("water.png"), KEY("key.png"), MONEY("money.png");

        private final String fileName;

        TileType(String fileName) {
                this.fileName = fileName;
        }

        public String toString() {
            return fileName;
        }
}
