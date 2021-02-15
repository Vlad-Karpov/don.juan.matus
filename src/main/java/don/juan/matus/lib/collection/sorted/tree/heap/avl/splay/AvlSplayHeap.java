package don.juan.matus.lib.collection.sorted.tree.heap.avl.splay;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeIterator;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.BinTreeNodeBalanceFactor;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.splay.SplayTree;

import static don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface.*;
import static don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree.checkTreeNodeStatic;
import static don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree.fixBalanceAfterRotateLeft;
import static don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree.fixBalanceAfterRotateRight;

public class AvlSplayHeap<T extends Comparable<T>> extends SplayTree<T> {

    private int maxHeapSize;

    public AvlSplayHeap(int maxHeapSize) {
        super();
        if (maxHeapSize <= 0) {
            throw new RuntimeException("AvlSplayHeap: maxHeapSize must be greater then zero!");
        }
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
    public void splay(final BinTreeNodeInterface<T> theRoot, final BinTreeNodeInterface<T> currentNode) {
        fixBalanceAfterChange(theRoot, (BinTreeNodeBalanceFactor<T>) currentNode, 0, true);
    }

    @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        int incHeight = 1;
        AvlSplayHeapNode<T> parent = (AvlSplayHeapNode<T>) parentOf(currentNode);
        if (parent != null) {
            byte ob = parent.getBalanceFactor();
            byte nb = parent.incBalanceFactor(leftOf(parent) == currentNode ? (byte) incHeight : (byte) -incHeight);
            incHeight = calculateIncHeight(ob, nb, currentNode);
        }
        fixBalanceAfterChange(root, (BinTreeNodeBalanceFactor<T>) currentNode, incHeight, true);
        return currentNode;
    }

    private void fixBalanceAfterChange(
            final BinTreeNodeInterface<T> theRoot,
            final BinTreeNodeBalanceFactor<T> currentNode,
            final int theIncHeight,
            final boolean splayIt) {
        int incHeight = theIncHeight;
        level = 0L;
        byte ob;
        byte nb;
        AvlSplayHeapNode<T> cursor = (AvlSplayHeapNode<T>) currentNode;
        while (parentOf(cursor) != theRoot && cursor != theRoot) {
            AvlSplayHeapNode<T> parent = (AvlSplayHeapNode<T>) parentOf(cursor);
            AvlSplayHeapNode<T> grandpa = (AvlSplayHeapNode<T>) parentOf(parent);
            AvlSplayHeapNode<T> greatGrandfather = (AvlSplayHeapNode<T>) parentOf(grandpa);
            if (grandpa != null) {
                ob = grandpa.getBalanceFactor();
                nb = grandpa.incBalanceFactor(leftOf(grandpa) == parent ? (byte) incHeight : (byte) -incHeight);
                incHeight = calculateIncHeight(ob, nb, parent);
            }
            if (greatGrandfather != null) {
                ob = greatGrandfather.getBalanceFactor();
                greatGrandfather.incBalanceFactor(leftOf(greatGrandfather) == grandpa ? (byte) incHeight : (byte) -incHeight);
            } else {
                ob = 0;
            }
            if (splayIt) {
                splayQuantum(theRoot, cursor);
            } else {
                cursor = grandpa;
            }
            if (greatGrandfather != null) {
                nb = greatGrandfather.getBalanceFactor();
                incHeight = calculateIncHeight(ob, nb, cursor);
            }
            level++;
            if (maxLevel < level) maxLevel = level;
        }
    }

    //todo: remove it to AVLBinTree instead of calculateIncHeight
    private int calculateIncHeight(
            final byte parentOldBalance,
            final byte parentNewBalance,
            final BinTreeNodeInterface<T> currentNode) {
        int incHeight = 0;
        if (parentNewBalance != parentOldBalance) {
            if (leftOf(parentOf(currentNode)) == currentNode) {
                if (parentOldBalance <= 0) {
                    if (parentNewBalance >= 0) {
                        incHeight = parentNewBalance;
                    }
                } else {
                    if (parentNewBalance > 0) {
                        incHeight = parentNewBalance - parentOldBalance;
                    } else {
                        incHeight = - parentOldBalance;
                    }
                }
            } else {
                if (parentOldBalance >= 0) {
                    if (parentNewBalance <= 0) {
                        incHeight = -parentNewBalance;
                    }
                } else {
                    if (parentNewBalance < 0) {
                        incHeight = - parentNewBalance + parentOldBalance;
                    } else {
                        incHeight = parentOldBalance;
                    }
                }
            }
        }
        return incHeight;
    }

    @Override
    protected void splayQuantum(BinTreeNodeInterface<T> theRoot, BinTreeNodeInterface<T> currentNode) {
        int incHeight;
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
                    incHeight = calculateIncHeight(ob, nb, (AvlSplayHeapNode<T>) currentNode);
                    greatGrandfather.incBalanceFactor(leftOf(greatGrandfather) == grandpa ? (byte) incHeight : (byte) -incHeight);
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
                    incHeight = calculateIncHeight(ob, nb, currentNode);
                    greatGrandfather.incBalanceFactor(leftOf(greatGrandfather) == grandpa ? (byte) incHeight : (byte) -incHeight);
                }
                rotateRight(parentOf(currentNode));
            }
        }
    }

    public void evict(final AvlSplayHeapNode<T> theCurrentNode) {
        if (theCurrentNode != null) {
            int incHeight = -1;
            BinTreeNodeBalanceFactor<T> parent = (BinTreeNodeBalanceFactor<T>) parentOf(theCurrentNode);
            if (parent != null) {
                byte ob = parent.getBalanceFactor();
                byte nb = parent.incBalanceFactor(leftOf(parent) == theCurrentNode ? (byte) incHeight : (byte) -incHeight);
                incHeight = calculateIncHeight(ob, nb, theCurrentNode);
            }
            fixBalanceAfterChange(root, theCurrentNode, incHeight, false);
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
                        } else {
                            if (current.getRight() != null) {
                                throw new RuntimeException("AvlSplayHeap: invalid structure!");
                            }
                            result = current;
                            break;
                        }
                    } else if (current.getBalanceFactor() < 0) {
                        if (current.getRight() != null) {
                            current = (AvlSplayHeapNode<T>) current.getRight();
                        } else {
                            if (current.getLeft() != null) {
                                throw new RuntimeException("AvlSplayHeap: invalid structure!");
                            }
                            result = current;
                            break;
                        }
                    } else {
                        if (getRandomBoolean()) {
                            if (current.getLeft() != null) {
                                current = (AvlSplayHeapNode<T>) current.getLeft();
                            } else {
                                if (current.getRight() != null) {
                                    throw new RuntimeException("AvlSplayHeap: invalid structure!");
                                }
                                result = current;
                                break;
                            }
                        } else {
                            if (current.getRight() != null) {
                                current = (AvlSplayHeapNode<T>) current.getRight();
                            } else {
                                if (current.getLeft() != null) {
                                    throw new RuntimeException("AvlSplayHeap: invalid structure!");
                                }
                                result = current;
                                break;
                            }
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
