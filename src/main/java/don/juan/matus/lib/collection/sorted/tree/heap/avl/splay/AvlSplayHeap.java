package don.juan.matus.lib.collection.sorted.tree.heap.avl.splay;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeIterator;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.BinTreeNodeBalanceFactor;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.splay.SplayTree;

import static don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface.leftOf;
import static don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface.parentOf;
import static don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree.checkTreeNodeStatic;
import static don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree.fixBalanceAfterRotateLeft;
import static don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree.fixBalanceAfterRotateRight;
import static don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree.calculateIncHeight;

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
        int incHeight = 1;
        AvlSplayHeapNode<T> parent = (AvlSplayHeapNode<T>) parentOf(currentNode);
        if (parent != null) {
            byte ob = parent.getBalanceFactor();
            byte nb = parent.incBalanceFactor(leftOf(parent) == currentNode ? (byte) incHeight : (byte) -incHeight);
            incHeight = calculateIncHeight(parent, 0, 1, ob, nb);
        }
        fixBalanceAfterChange((BinTreeNodeBalanceFactor<T>) currentNode, incHeight, 1);
        return currentNode;
    }

    private void fixBalanceAfterChange(final BinTreeNodeBalanceFactor<T> currentNode, int theIncHeight, int theSign) {
        AvlSplayHeapNode<T> cursor = (AvlSplayHeapNode<T>) currentNode;
        int incHeight = theIncHeight;
        while (parentOf(cursor) != root) {
            AvlSplayHeapNode<T> parent = (AvlSplayHeapNode<T>) parentOf(cursor);
            AvlSplayHeapNode<T> grandpa = (AvlSplayHeapNode<T>) parentOf(parent);
            AvlSplayHeapNode<T> greatGrandfather = (AvlSplayHeapNode<T>) parentOf(grandpa);
            byte obg = greatGrandfather != null ? greatGrandfather.getBalanceFactor() : 0;
            if (grandpa != null) {
                byte ob = grandpa.getBalanceFactor();
                byte nb = grandpa.incBalanceFactor((byte) incHeight);
                incHeight = calculateIncHeight(grandpa, 0, theSign, ob, nb);
            }
            if (greatGrandfather != null) {
                greatGrandfather.incBalanceFactor((byte) incHeight);
            }
            splayQuantum(root, cursor, theSign);
            if (greatGrandfather != null) {
                byte nb = greatGrandfather.getBalanceFactor();
                incHeight = calculateIncHeight(greatGrandfather, 0, theSign, obg, nb);
            }
        }
    }

    @Override
    protected void splayQuantum(BinTreeNodeInterface<T> theRoot, BinTreeNodeInterface<T> currentNode, int theSign) {
        if (currentNode == parentOf(currentNode).getLeft()) {
            if (parentOf(parentOf(currentNode)) == theRoot) {
                rotateRight(parentOf(currentNode));
            } else if (parentOf(currentNode) == parentOf(parentOf(currentNode)).getLeft()) {
                rotateRight(parentOf(parentOf(currentNode)));
                rotateRight(parentOf(currentNode));
            } else {
                AvlSplayHeapNode<T> grandpa = (AvlSplayHeapNode<T>) parentOf(parentOf(currentNode));
                AvlSplayHeapNode<T> greatGrandfather = (AvlSplayHeapNode<T>) parentOf(parentOf(parentOf(currentNode)));
                byte ob = grandpa != null ? grandpa.getBalanceFactor() : 0;
                rotateRight(parentOf(currentNode));
                if (greatGrandfather != null) {
                    byte nb = grandpa.getBalanceFactor();
                    int incHeight = calculateIncHeight(grandpa, 0, theSign, ob, nb);
                    greatGrandfather.incBalanceFactor((byte) incHeight);
                }
                rotateLeft(parentOf(currentNode));
            }
        } else {
            if (parentOf(parentOf(currentNode)) == theRoot) {
                rotateLeft(parentOf(currentNode));
            } else if (parentOf(currentNode) == parentOf(parentOf(currentNode)).getRight()) {
                rotateLeft(parentOf(parentOf(currentNode)));
                rotateLeft(parentOf(currentNode));
            } else {
                AvlSplayHeapNode<T> grandpa = (AvlSplayHeapNode<T>) parentOf(parentOf(currentNode));
                AvlSplayHeapNode<T> greatGrandfather = (AvlSplayHeapNode<T>) parentOf(parentOf(parentOf(currentNode)));
                byte ob = grandpa != null ? grandpa.getBalanceFactor() : 0;
                rotateLeft(parentOf(currentNode));
                if (greatGrandfather != null) {
                    byte nb = grandpa.getBalanceFactor();
                    int incHeight = calculateIncHeight(grandpa, 0, theSign, ob, nb);
                    greatGrandfather.incBalanceFactor((byte) incHeight);
                }
                rotateRight(parentOf(currentNode));
            }
        }
    }

    public void evict(final AvlSplayHeapNode<T> theCurrentNode) {
        if (theCurrentNode != null) {
            int incHeight = -1;
            BinTreeNodeBalanceFactor<T> parent = (BinTreeNodeBalanceFactor<T>) theCurrentNode.getParent();
            if (parent != null) {
                byte ob = parent.getBalanceFactor();
                byte nb = parent.incBalanceFactor(leftOf(parent) == theCurrentNode ? (byte) incHeight : (byte) -incHeight);
                incHeight = calculateIncHeight(parent, 0, 1, ob, nb);
            }
            fixBalanceAfterChange(theCurrentNode, incHeight, -1);
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
