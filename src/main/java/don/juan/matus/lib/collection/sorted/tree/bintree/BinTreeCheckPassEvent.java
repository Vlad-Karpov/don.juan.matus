package don.juan.matus.lib.collection.sorted.tree.bintree;

/**
 * BinTreeCheckPassEvent interface.
 */
public interface BinTreeCheckPassEvent<T extends Comparable<T>> {
    void onPass(BinTreeIterator<T> leftIterator, BinTreeIterator<T> rightIterator, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode);

    void setErrorMessage(String errMsg);

    String getErrorMessage();
}
