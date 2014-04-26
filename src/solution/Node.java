package solution;

import eval.HeuristicFacotry;
import eval.Heuristics;
import model.State;

/**
 * User: Ding
 * Date: 4/19/2014
 * Time: 6:03 PM
 */
public class Node implements Comparable{
    private State curState;
    private State goalState;
    private Heuristics huris;
    private Node parent;

    public Node(State st, State goal, Node parent, HeuristicFacotry.typeHeuristic hType) {
        this.curState = st;
        this.goalState = goal;
        this.huris = HeuristicFacotry.getHeuristicInstance(hType);
        this.parent = parent;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public State getState() {
        return curState;
    }

    public int getCost()
    {
        if (parent == null) {
            return  0;
        }

        return parent.getCost() + 1;
    }

    public Node getParent() {
        return parent;
    }

    public int CostEval() {
        return getCost() + huris.eval(curState,goalState);
    }

    public Heuristics getHuris() {
        return huris;
    }

    @Override
    public int compareTo(Object o) {
        int myCost = CostEval();
        int othCost = ((Node)o).CostEval();

        return myCost - othCost;
    }

    @Override
    public int hashCode() {
        return curState.hashCode();
    }

    @Override
    public String toString() {
        String[] lines = curState.toString().split(System.lineSeparator());

        int mid = lines.length / 2;

        int actualCost = getCost();
        int heuristicCost = huris.eval(curState,goalState);
        int sumCost = actualCost + heuristicCost;
        String ret = "";
        for (int i = 0; i < lines.length; i++) {
            if (mid == i){
                ret += lines[i] + " cost = " + actualCost + " + " + heuristicCost + " = " + sumCost + /*" hash = " + curState.hashCode() +*/ System.lineSeparator();
            }
            else {
                ret += lines[i] + System.lineSeparator();
            }
        }

        return ret;
    }
}
