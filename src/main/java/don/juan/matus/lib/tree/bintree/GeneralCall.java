package don.juan.matus.lib.tree.bintree;

/**
 * Interface GeneralCall.
 */
@FunctionalInterface
public interface GeneralCall<T extends Comparable<T>> {
    void generalCall(Boolean theResult, Integer theCmp, BinTreeNodeInterface<T> resultNode, Object generalObject);
}
