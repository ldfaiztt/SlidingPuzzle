package eval;

import model.Plain;
import model.State;

public class Distance implements Heuristics {

    @Override
    public int eval(State cur, State goal) {
        return ((Plain) cur).CountDist((Plain) goal);
    }
}
