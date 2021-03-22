package don.juan.matus.lib.collection.sorted.tree.bintree;

import don.juan.matus.lib.collection.CollectionBaseInterface;

/**
 * Interface binary tree.
 */
public interface BinTreeInterface<T extends Comparable<T>> extends CollectionBaseInterface<T> {

    long getMergeCount();

    void setMergeCount(long mergeCount);

    MergeableBinTree.MergeParts getParts();

    BinTreeNodeInterface<T> getRoot();

    Long getMaxLevel();

    Long getLevel();

    Long getSize();

    void passBack(BinTreePassEvent<BinTreeNodeInterface<T>> thePassEvent);

    void passStraight(BinTreePassEvent<BinTreeNodeInterface<T>> thePassEvent);

    Long checkStructure(BinTreeNodeInterface<T> theRoot);

    Long checkStructure(BinTreeCheckPassEvent<T> thePassEvent, BinTreeNodeInterface<T> theRoot);

    Long checkStructure(BinTreeCheckPassEvent<T> thePassEvent);

    boolean checkTreeNode(
            BinTreeCheckPassEvent<T> thePassEvent,
            BinTreeIterator<T> btiLeft,
            BinTreeIterator<T> btiRight,
            BinTreeNodeInterface<T> currentNode,
            BinTreeNodeInterface<T> previousNode);

    BinTreeNodeInterface<T> beforeSeekLoop(T theObject, BinTreeNodeInterface<T> currentNode, GeneralCall<T> generalCallObject);
    void beforeGet();

    Boolean seek(T theObject, GeneralCall<T> generalCallObject);

    Boolean seekLoop(T theObject, BinTreeNodeInterface<T> currentNode, GeneralCall<T> generalCallObject);

    static <T extends Comparable<T>> BinTreeNodeInterface<T> seekLoop(T theObject, BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeInterface<T> result = null;
        BinTreeNodeInterface<T> current = currentNode;
        if (current != null) {
            do {
                if (current.getObjectNode() == null) {
                    if (current.getLeft() != null) {
                        current = current.getLeft();
                    } else {
                        break;
                    }
                } else {
                    int cmp = theObject.compareTo(current.getObjectNode());
                    if (cmp == 0) {
                        result = current;
                        break;
                    } else if (cmp < 0) {
                        if (current.getLeft() != null) {
                            current = current.getLeft();
                        } else {
                            break;
                        }
                    } else {
                        if (current.getRight() != null) {
                            current = current.getRight();
                        } else {
                            break;
                        }
                    }
                }
            } while (true);
        }
        return result;
    };

    BinTreeBase.TreeProps treePassage();

    BinTreeNodeInterface<T> removeNode(
            Boolean theDescending,
            BinTreeNodeInterface<T> currentNode,
            BinTreeNodeInterface<T> nextNode, BinTreeNodeInterface<BinTreeNodeInterface<T>> evictNodeIndex);

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

    static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    void rebalanceTree();

    void splay(final BinTreeNodeInterface<T> theRoot, final BinTreeNodeInterface<T> currentNode);

    BinTreeNodeInterface<T> getFirst();
    BinTreeNodeInterface<T> getLast();

}
