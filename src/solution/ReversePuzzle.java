package solution;

import action.Action;
import action.ActionFactory;
import model.State;

import java.util.Random;

/**
 * User: Ding
 * Date: 4/25/2014
 * Time: 8:59 PM
 * Description: Receive goal state, return a state that is [step] away from goal.
 */
public class ReversePuzzle {
    public static State generateState(State goal, int step) {
        if (step <= 0) {
            return goal;
        }

        State cur = goal;
        for (int i = 0; i < step; i++) {
            cur = IteratorState(cur);
        }

        return cur;
    }

    private static State IteratorState(State cur){
        Random generator = new Random();
        int index = generator.nextInt(ActionFactory.typeAction.values().length);
        Action action = ActionFactory.CreateAction(ActionFactory.typeAction.values()[index]);
        return action.act(cur);
    }
}
