package action;

import model.Plain;
import model.State;

public class BlankLeft extends Action {
    @Override
    public State act(State cur) {
        Plain next = new Plain((Plain) cur);
        next.BlankLeft();

        printAction(cur,next);

        return next;
    }
}
