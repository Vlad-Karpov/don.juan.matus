package don.juan.matus.lib.bintree;

import java.util.Iterator;

/**
 * Interface binary tree.
 */
public interface BinTreeInterface<T extends Comparable<T>> {

    BinTreeNodeInterface<T> getRoot();

    Long getMaxLevel();

    Long getLevel();

    Long getSize();

    void passBack(BinTreePassEvent<BinTreeNodeInterface<T>> thePassEvent);

    void passStraight(BinTreePassEvent<BinTreeNodeInterface<T>> thePassEvent);

    void checkStructure(BinTreeCheckPassEvent<T> thePassEvent);

    boolean checkTreeNode(
            BinTreeCheckPassEvent<T> thePassEvent,
            BinTreeIterator<T> btiLeft,
            BinTreeIterator<T> btiRight,
            BinTreeNodeInterface<T> currentNode,
            BinTreeNodeInterface<T> previousNode);

    Boolean seek(T theObject, GeneralCall<T> generalCallObject);

    Boolean seekLoop(T theObject, BinTreeNodeInterface<T> currentNode, GeneralCall<T> generalCallObject);

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

}
