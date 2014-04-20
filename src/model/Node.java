package model;

import eval.HeuristicFacotry;
import eval.Heuristics;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Ding
 * Date: 4/19/2014
 * Time: 6:03 PM
 */
public class Node implements Comparable{
    private State curState;
    private State goalState;
    private int cost;
    private Heuristics huris;
    private List<Node> childern;

    public Node(State st, State goal, int cost, HeuristicFacotry.typeHeuristic hType) {
        this.curState = st;
        this.goalState = goal;
        this.cost = cost;
        this.huris = HeuristicFacotry.CreateHeuristic(hType);
        this.childern = new ArrayList<Node>();
    }

    public State getState() {
        return curState;
    }

    public int getCost() {
        return cost;
    }

    public int CostEval() {
        return cost + huris.eval(curState,goalState);
    }

    public boolean AddChild(Node node) {
        return childern.add(node);
    }

    @Override
    public int compareTo(Object o) {
        int myCost = CostEval();
        int othCost = ((Node)o).CostEval();

        return myCost - othCost;
    }
}
