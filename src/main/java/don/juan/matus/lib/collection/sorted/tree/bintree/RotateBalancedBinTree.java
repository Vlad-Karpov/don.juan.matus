package don.juan.matus.lib.collection.sorted.tree.bintree;

/**
 * Balanced binary tree based on node rotation.
 */
public interface RotateBalancedBinTree<T extends Comparable<T>> {

    Long getRotateCount();

    void setRotateCount(Long rotateCount);

    void afterRotateRight(BinTreeNodeInterface<T> dadNode, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> pivotNode);

    void afterRotateLeft(BinTreeNodeInterface<T> dadNode, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> pivotNode);

    BinTreeNodeInterface<T> rotateRight(BinTreeNodeInterface<T> currentNode);

    BinTreeNodeInterface<T> rotateLeft(BinTreeNodeInterface<T> currentNode);

    BinTreeNodeInterface<T> rebalanceTree(BinTreeNodeInterface<T> currentNode);

    void rebalanceTree();
}
