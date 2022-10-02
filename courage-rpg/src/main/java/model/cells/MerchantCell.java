package model.cells;

import engineV2.Cell;
import model.GameState;

import java.util.List;
import java.util.Random;

public class MerchantCell extends ActionCell {
    @Override
    public Cell build(List<String> arguments) {
        return this;
    }

    @Override
    public List<String> export() {
        return null;
    }

    @Override
    public void act(GameState state) {
        Random random = new Random();

        int luck = state.money / 1000;

        int n = random.nextInt(0, 1000);
        if (luck < n) {
            if (random.nextBoolean()) {
                state.hp = 1;
            }
            if (random.nextBoolean()) {
                state.money = 0;
            }
            if (random.nextBoolean()) {
                state.useItem(GameState.Item.KEY);
                state.useItem(GameState.Item.KEY);
            }
            state.offerDialog("hahahahahhah");
        } else {
            if (random.nextBoolean()) {
                state.hp = 100;
            }
            if (random.nextBoolean()) {
                state.money += 100;
            }
            if (random.nextBoolean()) {
                state.addItem(GameState.Item.KEY);
            }
            state.offerDialog("You deserve it!");
        }
    }
}
