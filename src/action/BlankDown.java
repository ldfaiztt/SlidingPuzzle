package action;

import model.Plain;
import model.State;

public class BlankDown extends Action {
    @Override
    public State act(State cur) {
        Plain next = new Plain((Plain) cur);
        next.BlankDown();

        printAction(cur,next);

        return next;
    }
}
