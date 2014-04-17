package eval;

import model.State;

public interface Heuristics {
    public int eval(State cur, State goal);
}
