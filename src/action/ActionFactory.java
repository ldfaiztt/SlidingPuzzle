package action;

/**
 * User: Ding
 * Date: 4/19/2014
 * Time: 8:04 PM
 */
public class ActionFactory {
    public enum typeAction {
        down,
        up,
        left,
        right
    }

    public static Action CreateAction(typeAction aType) {
        Action ret = null;

        switch (aType){
            case down:
                ret = new BlankDown();
                break;

            case up:
                ret = new BlankUp();
                break;

            case left:
                ret = new BlankLeft();
                break;

            case right:
                ret = new BlankRight();
                break;
        }

        return ret;
    }
}
