package don.juan.matus.lib.bintree;

/**
 * BinTreePassEvent interface.
 */
public interface BinTreePassEvent<T> {
    void onPass(T theObject);
    Long incLevel();
    Long decLevel();
    Long incLeft();
    Long decLeft();
    Long incRight();
    Long decRight();
}
