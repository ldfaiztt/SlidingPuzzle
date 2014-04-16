package solution;

import model.Plain;
import model.State;

/**
 * Created by WHPM-1031 on 4/16/2014.
 */
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
