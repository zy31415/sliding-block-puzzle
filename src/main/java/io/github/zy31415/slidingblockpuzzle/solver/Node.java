package io.github.zy31415.slidingblockpuzzle.solver;

import java.util.List;

public interface Node {
    public boolean isGoal();
    public List<Node> next();
}
