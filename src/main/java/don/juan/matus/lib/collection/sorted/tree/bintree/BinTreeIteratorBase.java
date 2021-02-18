package don.juan.matus.lib.collection.sorted.tree.bintree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Base binary tree iterator.
 */
public abstract class BinTreeIteratorBase<T extends Comparable<T>> implements Iterator<T> {

    protected BinTreeInterface<T> binTree;
    protected BinTreeNodeInterface<T> rootNode;
    protected BinTreeNodeInterface<T> currentNode;
    protected BinTreeNodeInterface<T> removeNode;
    /**
     * 1 - from parent.
     * 2 - from left.
     * 3 - from right.
     */
    protected byte leftOrRight;
    protected Long maxLevel;
    protected Long level;
    protected Long size;
    protected Object outerObject = null;
    protected boolean eof = false;
    protected GeneralCall<T> seekGeneralCall;

    public BinTreeIteratorBase(BinTreeInterface<T> theBinTree, BinTreeNodeInterface<T> theRootNode) {
        maxLevel = 0L;
        level = 0L;
        size = 0L;
        binTree = theBinTree;
        rootNode = theRootNode;
        if (rootNode.getParent() == null) {
            currentNode = theRootNode.getLeft();
            rootNode = currentNode;
        } else {
            currentNode = theRootNode;
        }
        removeNode = null;
        leftOrRight = 1;
        init();
        leftOrRight = 1;
        eof = tryNext() == null;
    }

    public BinTreeIteratorBase(BinTreeInterface<T> theBinTree, BinTreeNodeInterface<T> theRootNode, T theObject) {
        maxLevel = 0L;
        level = 0L;
        size = 0L;
        binTree = theBinTree;
        rootNode = theRootNode;
        currentNode = theRootNode;
        removeNode = null;
        leftOrRight = 2;
        init();
        binTree.seekLoop(theObject, currentNode, seekGeneralCall);
    }

    abstract protected void init();

    @Override
    public boolean hasNext() {
        return !eof;
    }

    @Override
    public T next() {
        T result;
        if (!eof) {
            removeNode = currentNode;
            result = currentNode.getObjectNode();
        } else {
            throw new NoSuchElementException();
        }
        eof = tryNext() == null;
        return result;
    }

    abstract protected T tryNext();

    public BinTreeInterface<T> getBinTree() {
        return binTree;
    }

    public BinTreeNodeInterface<T> getRootNode() {
        return rootNode;
    }

    public BinTreeNodeInterface<T> getCurrentNode() {
        return currentNode;
    }

    public byte getLeftOrRight() {
        return leftOrRight;
    }

    public Long getMaxLevel() {
        return maxLevel;
    }

    public Long getLevel() {
        return level;
    }

    public Long getSize() {
        return size;
    }

    abstract public void remove();

    public Object getOuterObject() {
        return outerObject;
    }

    @Override
    public String toString() {
        return "BinTreeIteratorBase{" +
                "currentNode=" + currentNode +
                ", maxLevel=" + maxLevel +
                ", size=" + size +
                ", outerObject={" + (outerObject != null ? outerObject.toString() : "null") + '}' +
                '}';
    }

}
