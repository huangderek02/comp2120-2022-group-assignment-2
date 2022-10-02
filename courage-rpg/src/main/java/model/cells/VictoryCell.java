package model.cells;

import engineV2.Cell;
import model.GameState;

import java.util.List;

public class VictoryCell extends ActionCell {
    @Override
    public Cell build(List<String> arguments) {
        return this;
    }

    @Override
    public List<String> export() {
        return List.of();
    }

    /**
     * Win the game
     *
     * @author Xianghao Wang
     * */
    @Override
    public void act(GameState state) {
        state.offerDialog("[Princess] Ohhhhhh, I cannot thank you enough. You have saved my life! I was seconds away from dying! PHEW!");
        state.offerDialog("You win the game!!!!!!!!!!");
    }
}
