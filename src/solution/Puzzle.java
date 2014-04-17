package solution;

import model.Plain;
import model.State;

public class Puzzle {
    State cur;
    State goal;

    public Puzzle(int size) {
        cur = new Plain(size, true);
        goal = new Plain(size, false);
    }

    private boolean ReachGoal(Plain cur) {
        return cur.equals(goal);
    }


}
