package solution;

import action.Action;
import action.ActionFactory;
import eval.HeuristicFacotry;
import model.Plain;
import model.State;
import model.Tile;

import java.util.*;

public class Puzzle {
    private State curState;
    private State goalState;
    private HeuristicFacotry.typeHeuristic heuristicType;
    private Node root;

    private Queue<Node> frontier;
    private Map<String, Node> explored;
    private List<Action> actions;

    protected Node AddExplored(Node node) {
        System.out.println(node.getState());
        return explored.put(node.getState().toString(), node);
    }

    protected boolean AddFrontier(Node node) {
        return frontier.add(node);
    }

    public Puzzle(int size, HeuristicFacotry.typeHeuristic hType, List<Integer> list) {

        if (list != null && list.size() > 0) {
            List<List<Tile>> max = new ArrayList<List<Tile>>();
            for (int i = 0; i < size; i++) {
                List<Tile> row = new ArrayList<Tile>();
                for (int j = 0; j < size; j++) {
                    row.add(new Tile(list.get(i * size + j)));
                }
                max.add(row);
            }
            curState = new Plain(max);
        } else {
            curState = new Plain(size, false);
        }

        goalState = new Plain(size, true);
        heuristicType = hType;
        root = new Node(curState, goalState, 0, heuristicType);

        frontier = new PriorityQueue<Node>();
        explored = new HashMap<String, Node>();
        actions = new ArrayList<Action>();
        for (ActionFactory.typeAction aType : ActionFactory.typeAction.values()) {
            actions.add(ActionFactory.CreateAction(aType));
        }
    }

    public Node Run() {
        return PlayIter(root);
    }

    private boolean ReachGoal(State cur) {
        return cur.equals(goalState);
    }

    private Node PlayIter(Node cur) {
        AddExplored(cur);

        if (ReachGoal(cur.getState())) {
            return cur;
        }

        for (Action action : actions) {
            Node next = new Node(action.act(cur.getState()), goalState, cur.getCost() + 1, heuristicType);
            cur.AddChild(next);
            if (!explored.containsKey(next.getState().toString())) {
                AddFrontier(next);
            }
        }
        if (frontier.isEmpty()) {
            return null;
        }

        return PlayIter(frontier.poll());
    }


}
