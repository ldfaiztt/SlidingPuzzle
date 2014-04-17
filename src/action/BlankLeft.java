package action;

import model.Plain;
import model.State;

public class BlankLeft implements Action {
    @Override
    public State act(State cur) {
        Plain next = new Plain((Plain) cur);
        next.BlankLeft();

        return next;
    }
}
