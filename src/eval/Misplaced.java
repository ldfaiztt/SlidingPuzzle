package eval;

import model.Plain;
import model.State;


public class Misplaced implements Heuristics {
    @Override
    public int eval(State cur, State goal) {
        return ((Plain) cur).CountDiff((Plain) goal);
    }
}
