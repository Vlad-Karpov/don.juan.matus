package don.juan.matus.lib.collection.sorted.tree.heap.avl.splay;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeIterator;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.BinTreeNodeBalanceFactor;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.splay.SplayTree;

import static don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree.checkTreeNodeStatic;
import static don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree.culculateIncHeight;
import static don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree.fixBalanceAfterRotateLeft;
import static don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree.fixBalanceAfterRotateRight;

public class AvlSplayHeap<T extends Comparable<T>> extends SplayTree<T> {

    private int maxHeapSize;

    public AvlSplayHeap(int maxHeapSize) {
        super();
        this.maxHeapSize = maxHeapSize;
        root = new AvlSplayHeapNode<T>(null, null, null, null);
    }

    public int getMaxHeapSize() {
        return maxHeapSize;
    }

    public void setMaxHeapSize(int maxHeapSize) {
        this.maxHeapSize = maxHeapSize;
    }

    @Override
    public void afterRotateRight(BinTreeNodeInterface<T> dadNode, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> pivotNode) {
        fixBalanceAfterRotateRight(dadNode, (BinTreeNodeBalanceFactor<T>) currentNode, pivotNode);
    }

    @Override
    public void afterRotateLeft(BinTreeNodeInterface<T> dadNode, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> pivotNode) {
        fixBalanceAfterRotateLeft(dadNode, (BinTreeNodeBalanceFactor<T>) currentNode, pivotNode);
    }

    @Override
    protected BinTreeNodeInterface<T> beforeAddLoop(final BinTreeNodeInterface<T> currentNode) {
        if (size == maxHeapSize) {
            evict(findLastUsed());
        }
        return currentNode;
    }


    @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeBalanceFactor<T> cursor = (BinTreeNodeBalanceFactor<T>) currentNode;
        BinTreeNodeBalanceFactor<T> parent = (BinTreeNodeBalanceFactor<T>) cursor.getParent();
        fixBalanceAfterChange(cursor, parent.getLeft() == cursor ? 1 : -1, 1);
        splay(root, cursor);
        return cursor;
    }

    private void fixBalanceAfterChange(final BinTreeNodeBalanceFactor<T> currentNode, int theIncHeight, int theSign) {
        BinTreeNodeBalanceFactor<T> parent;
        BinTreeNodeBalanceFactor<T> cursor = currentNode;
        int incHeight = theIncHeight;
        while (cursor != root) {
            parent = (BinTreeNodeBalanceFactor<T>) cursor.getParent();
            byte ob = parent.getBalanceFactor();
            parent.incBalanceFactor((byte) incHeight);
            byte nb = parent.getBalanceFactor();
            incHeight = culculateIncHeight(parent, 0, theSign, ob, nb);
            if (theSign == 1 && parent.getBalanceFactor() == 0 && incHeight == 0) {
                break;
            }
            cursor = parent;
        }
    }

    public void evict(final AvlSplayHeapNode<T> theCurrentNode) {
        if (theCurrentNode != null) {
            BinTreeNodeBalanceFactor<T> parent = (BinTreeNodeBalanceFactor<T>) theCurrentNode.getParent();
            fixBalanceAfterChange(theCurrentNode, parent.getLeft() == theCurrentNode ? -1 : 1, -1);
            //remove node
            if (parent.getLeft() == theCurrentNode) {
                parent.setLeft(null);
                theCurrentNode.setParent(null);
            } else {
                parent.setRight(null);
                theCurrentNode.setParent(null);
            }
            size--;
        }
    }

    public AvlSplayHeapNode<T> findLastUsed() {
        AvlSplayHeapNode<T> result = null;
        AvlSplayHeapNode<T> current = (AvlSplayHeapNode<T>) root;
        if (current != null) {
            do {
                if (current.getObjectNode() == null) {
                    if (current.getLeft() != null) {
                        current = (AvlSplayHeapNode<T>) current.getLeft();
                    } else {
                        break;
                    }
                } else {
                    if (current.getBalanceFactor() > 0) {
                        if (current.getLeft() != null) {
                            current = (AvlSplayHeapNode<T>) current.getLeft();
                        } else if (current.getRight() != null) {
                            current = (AvlSplayHeapNode<T>) current.getRight();
                        } else {
                            result = current;
                            break;
                        }
                    } else {
                        if (current.getRight() != null) {
                            current = (AvlSplayHeapNode<T>) current.getRight();
                        } else if (current.getLeft() != null) {
                            current = (AvlSplayHeapNode<T>) current.getLeft();
                        } else {
                            result = current;
                            break;
                        }
                    }
                }
            } while (true);
        }
        return result;
    }

    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        boolean result = super.checkTreeNode(thePassEvent, btiLeft, btiRight, currentNode, previousNode);
        return checkTreeNodeStatic(result, thePassEvent, btiLeft, btiRight, currentNode, previousNode);
    }

}
