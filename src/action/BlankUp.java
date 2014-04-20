package action;

import model.Plain;
import model.State;

public class BlankUp extends Action {
    @Override
    public State act(State cur) {
        Plain next = new Plain((Plain) cur);
        next.BlankUp();

        printAction(cur, next);

        return next;
    }
}
