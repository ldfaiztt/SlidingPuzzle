package eval;

import model.Plain;
import model.State;

public class Distance implements Heuristics {

    @Override
    public int eval(State cur, State goal) {
        int ret = ((Plain) cur).CountDist((Plain) goal);
        if (ret >= 0 ) {
            return ret;
        }
        else {
            throw new IllegalArgumentException("Heuristic evaluation must >= 0 while here is " + ret);
        }
    }

    @Override
    public String toString() {
        return "Distance";
    }
}
