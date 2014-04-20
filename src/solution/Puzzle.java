package solution;

import action.Action;
import action.ActionFactory;
import eval.HeuristicFacotry;
import model.Node;
import model.Plain;
import model.State;

import java.util.*;

public class Puzzle {
    private State curState;
    private State goalState;
    private HeuristicFacotry.typeHeuristic heuristicType;
    private Node root;

    private Queue<Node> frontier;
    private Set<Node> explored;
    private List<Action> actions;

    public Puzzle(int size, HeuristicFacotry.typeHeuristic hType) {
        curState = new Plain(size, true);
        goalState = new Plain(size, false);
        heuristicType = hType;
        root = new Node(curState, goalState, 0, heuristicType);

        frontier = new PriorityQueue<Node>();
        explored = new HashSet<Node>();

        for (ActionFactory.typeAction aType : ActionFactory.typeAction.values()) {
            actions.add(ActionFactory.CreateAction(aType));
        }
    }

    public void Run() {
        Node sln = PlayIter(root);
    }

    private boolean ReachGoal(State cur) {
        return cur.equals(goalState);
    }

    public Node PlayIter(Node cur) {
        if (ReachGoal(cur.getState())) {
            return cur;
        }
        explored.add(cur);
        for (Action action : actions) {
            Node next = new Node(action.act(cur.getState()), goalState, cur.getCost() + 1, heuristicType);
            cur.AddChild(next);
            if (!explored.contains(next)) {
                frontier.add(next);
            }
        }
        if (frontier.isEmpty()) {
            return null;
        }

        return PlayIter(frontier.poll());
    }


}
