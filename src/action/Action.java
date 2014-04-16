package action;

import model.State;

/**
 * Created by WHPM-1031 on 4/16/2014.
 */
public interface Action {
    State act(State cur);
}
