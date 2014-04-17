package action;

import model.State;

public interface Action {
    public State act(State cur);
}
