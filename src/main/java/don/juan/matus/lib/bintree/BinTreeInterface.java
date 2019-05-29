package don.juan.matus.lib.bintree;

import java.util.Iterator;

/**
 * Interface binary tree.
 */
public interface BinTreeInterface<T extends Comparable<T>> {

    long getMergeCount();

    void setMergeCount(long mergeCount);

    MergeableBinTree.MergeParts getParts();

    BinTreeNodeInterface<T> getRoot();

    Long getMaxLevel();

    Long getLevel();

    Long getSize();

    void passBack(BinTreePassEvent<BinTreeNodeInterface<T>> thePassEvent);

    void passStraight(BinTreePassEvent<BinTreeNodeInterface<T>> thePassEvent);

    Long checkStructure(BinTreeCheckPassEvent<T> thePassEvent);

    boolean checkTreeNode(
            BinTreeCheckPassEvent<T> thePassEvent,
            BinTreeIterator<T> btiLeft,
            BinTreeIterator<T> btiRight,
            BinTreeNodeInterface<T> currentNode,
            BinTreeNodeInterface<T> previousNode);

    Boolean seek(T theObject, GeneralCall<T> generalCallObject);

    Boolean seekLoop(T theObject, BinTreeNodeInterface<T> currentNode, GeneralCall<T> generalCallObject);

    BinTreeBase.TreeProps treePassage();

    Iterator<T> iterator();

    Iterator<T> iterator(T theObject);

    Iterator<T> descendingIterator();

    Iterator<T> descendingIterator(T theObject);

    boolean add(T theObject);

    void remove(T theObject);

    BinTreeNodeInterface<T> removeNode(
            Boolean theDescending,
            BinTreeNodeInterface<T> currentNode,
            BinTreeNodeInterface<T> nextNode);

    void clearTree();

    static <T extends Comparable<T>> BinTreeNodeInterface<T> parentOf(BinTreeNodeInterface<T> p) {
        return (p == null ? null : p.getParent());
    }

    static <T extends Comparable<T>> BinTreeNodeInterface<T> leftOf(BinTreeNodeInterface<T> p) {
        return (p == null) ? null : p.getLeft();
    }

    static <T extends Comparable<T>> BinTreeNodeInterface<T> rightOf(BinTreeNodeInterface<T> p) {
        return (p == null) ? null : p.getRight();
    }

    static int min(int b1, int b2) {
        return b1 < b2 ? b1 : b2;
    }

    static int min(int b1, int b2, int b3) {
        int m = min(b1, b2);
        return m < b3 ? m : b3;
    }

    static int max(int b1, int b2) {
        return b1 > b2 ? b1 : b2;
    }

    static int max(int b1, int b2, int b3) {
        int m = max(b1, b2);
        return m > b3 ? m : b3;
    }

    static int abs(int b1) {
        return (b1 < 0) ? -b1 : b1;
    }

    void rebalanceTree();

}
