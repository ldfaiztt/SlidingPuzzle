package eval;

/**
 * User: Ding
 * Date: 4/19/2014
 * Time: 7:12 PM
 */
public class HeuristicFacotry {
    public enum typeHeuristic {
        Distance,
        Misplaced,
    }

    private static Heuristics Huris_ = null;
    private static typeHeuristic curType = null;
    public static Heuristics getHeuristicInstance(typeHeuristic hType) {
        if (null == Huris_ || null == curType || curType != hType) {
            switch (hType) {
                case Distance:
                    Huris_ = new Distance();
                    curType = typeHeuristic.Distance;
                    break;

                 case Misplaced:
                     Huris_ = new Misplaced();
                     curType = typeHeuristic.Misplaced;
                     break;
            }
        }

        return Huris_;
    }
}
