package don.juan.matus.lib.tree.bintree;

/**
 * BinTreePassEvent interface.
 */
public interface BinTreePassEvent<T> {
    void onPass(T theObject);
    Long incLevel(T theObject);
    Long decLevel(T theObject);
    Long incLeft(T theObject);
    Long decLeft(T theObject);
    Long incRight(T theObject);
    Long decRight(T theObject);
    void onNodeCompleted(T theObject);

    void onPassCompleted();
}
