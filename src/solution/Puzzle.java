package solution;

import action.Action;
import action.ActionFactory;
import eval.HeuristicFacotry;
import model.Node;
import model.Plain;
import model.State;
import model.Tile;

import java.util.*;

public class Puzzle {
    private State goalState;
    private HeuristicFacotry.typeHeuristic heuristicType;
    private Node root;

    private Queue<Node> frontier;
    private Map<Integer, Node> explored;
    private List<Action> actions;

    public Puzzle(int size, HeuristicFacotry.typeHeuristic hType, List<Integer> list) {
        State initState;
        if (list != null && list.size() > 0) {
            List<List<Tile>> max = new ArrayList<List<Tile>>();
            for (int i = 0; i < size; i++) {
                List<Tile> row = new ArrayList<Tile>();
                for (int j = 0; j < size; j++) {
                    row.add(new Tile(list.get(i * size + j)));
                }
                max.add(row);
            }
            initState = new Plain(max);
        } else {
            initState = new Plain(size, false);
        }

        InitPuzzle(size, hType,initState);
    }

    public Puzzle(HeuristicFacotry.typeHeuristic hType, State initState) {
        int defaultSize = 3;
        if (initState == null) {
            initState = new Plain(defaultSize, false);
        }

        InitPuzzle(defaultSize, hType, initState);
    }

    private void InitPuzzle(int size, HeuristicFacotry.typeHeuristic hType, State initialState) {
        goalState = new Plain(size, true);
        heuristicType = hType;
        root = new Node(initialState, goalState, null, heuristicType);

        frontier = new PriorityQueue<Node>();
        explored = new HashMap<Integer, Node>();
        actions = new ArrayList<Action>();
        for (ActionFactory.typeAction aType : ActionFactory.typeAction.values()) {
            actions.add(ActionFactory.CreateAction(aType));
        }
    }

    private void PrintNode(String action, Node node) {
        String[] lines = node.toString().split(System.lineSeparator());

        int mid = lines.length / 2;

        String blank = "";
        for (int i = 0; i < action.length(); i++) {
            blank += " ";
        }

        String ret = "";
        for (int i = 0; i < lines.length; i++) {
            if (mid == i){
                ret += action + ": " + lines[i] + System.lineSeparator();
            }
            else {
                ret += blank + ": " + lines[i] + System.lineSeparator();
            }
        }

        System.out.println(ret);
    }

    public int getNodesNumber() {
        return explored.size() + frontier.size();
    }

    protected Node AddExplored(Node node) {
        //PrintNode("Explored", node);
        return explored.put(node.hashCode(), node);
    }

    protected boolean AddFrontier(Node node) {
        //PrintNode("Frontier", node);
        return frontier.add(node);
    }

    public Node Run() {
        AddFrontier(root);

        while (!frontier.isEmpty()){
            Node curNode = frontier.poll();
            AddExplored(curNode);

            if (ReachGoal(curNode)){
                return curNode;
            }

            for (Action action : actions) {
                Node next = new Node(action.act(curNode.getState()), goalState, curNode, heuristicType);
                if (!explored.containsKey(next.hashCode())) {
                    AddFrontier(next);
                }
            }
        }

        return null;
    }

//    public Node Run() {
//        return PlayIter(root);
//    }

    private boolean ReachGoal(Node cur) {
        return cur.getState().equals(goalState);
    }

    private Node PlayIter(Node cur) {
        AddExplored(cur);

        if (ReachGoal(cur)) {
            return cur;
        }

        for (Action action : actions) {
            Node next = new Node(action.act(cur.getState()), goalState, cur, heuristicType);
            if (!explored.containsKey(next.hashCode())) {
                AddFrontier(next);
            }
        }
        if (frontier.isEmpty()) {
            return null;
        }

        return PlayIter(frontier.poll());
    }


}
