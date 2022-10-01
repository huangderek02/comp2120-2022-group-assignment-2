package engineV2;

import java.util.List;

public abstract class Cell {
    public abstract Cell build(List<String> arguments);
    public abstract List<String> export();
}
