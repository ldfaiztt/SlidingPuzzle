package action;

import model.State;

public abstract class Action {
    public abstract State act(State cur);

    private String strAction(State cur, State next) {
        String[] curlines = cur.toString().split(System.lineSeparator());
        String[] nextlines = next.toString().split(System.lineSeparator());

        int mid = curlines.length / 2;

        String ret = "";
        for (int i = 0; i < curlines.length; i++) {
            if (mid == i) {
                ret += curlines[i] + "     ==>    " + nextlines[i] + System.lineSeparator();
            } else {
                ret += curlines[i] + "            " + nextlines[i] + System.lineSeparator();
            }
        }

        return ret;
    }

    protected void printAction(State cur, State next) {
        //System.out.println(strAction(cur, next));
    }
}
