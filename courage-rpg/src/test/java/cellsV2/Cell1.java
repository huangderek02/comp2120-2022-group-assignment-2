package cellsV2;

import engineV2.Cell;

import java.util.List;

public class Cell1 extends Cell {
    @Override
    public Cell build(List<String> arguments) {
        return new Cell1();
    }

    @Override
    public List<String> export() {
        return List.of();
    }
}
