package don.juan.matus.lib.collection.sorted.tree.heap.avl.splay;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeIterator;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.GeneralCall;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.BinTreeNodeBalanceFactor;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.splay.SplayTree;
import don.juan.matus.lib.collection.sorted.tree.heap.HeapInterface;
import don.juan.matus.lib.collection.sorted.tree.heap.LightHeapNode;

import static don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface.*;
import static don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree.*;

public class AvlSplayHeap<T extends Comparable<? extends T>> extends SplayTree<T> implements HeapInterface<T> {

    private int maxHeapSize;
    private EvictionStrategy evictionStrategy = EvictionStrategy.SPLAY;
    private long ttl;

    public AvlSplayHeap(int maxHeapSize) {
        super();
        if (maxHeapSize <= 0) {
            throw new RuntimeException("AvlSplayHeap: maxHeapSize must be greater then zero!");
        }
        this.maxHeapSize = maxHeapSize;
        this.ttl = Long.MAX_VALUE;
        root = new LightHeapNode<T>(this, null, null, null, null);
    }

    @Override
    public long getTtl() {
        return ttl;
    }

    @Override
    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public int getMaxHeapSize() {
        return maxHeapSize;
    }

    public void setMaxHeapSize(int maxHeapSize) {
        this.maxHeapSize = maxHeapSize;
    }

    public EvictionStrategy getEvictionStrategy() {
        return evictionStrategy;
    }

    public void setEvictionStrategy(EvictionStrategy evictionStrategy) {
        this.evictionStrategy = evictionStrategy;
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
        expungeStaleEntries();
        if (size == maxHeapSize) {
            LightHeapNode<T> evictNode = findLastUsed();
            switch (evictionStrategy) {
                case SIMPLE: {
                    evict(evictNode);
                    break;
                }
                case SPLAY:
                default: {
                    removeNode(false, evictNode, evictNode, null);
                    break;
                }
            }
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
        LightHeapNode<T> parent = (LightHeapNode<T>) parentOf(currentNode);
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
        LightHeapNode<T> cursor = (LightHeapNode<T>) currentNode;
        while (parentOf(cursor) != theRoot && cursor != theRoot) {
            LightHeapNode<T> parent = (LightHeapNode<T>) parentOf(cursor);
            LightHeapNode<T> grandpa = (LightHeapNode<T>) parentOf(parent);
            LightHeapNode<T> greatGrandfather = (LightHeapNode<T>) parentOf(grandpa);
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

    protected void zigZagRightLift(BinTreeNodeInterface<T> currentNode) {
        int incHeight;
        LightHeapNode<T> grandpa = (LightHeapNode<T>) parentOf(parentOf(currentNode));
        LightHeapNode<T> greatGrandfather = (LightHeapNode<T>) parentOf(parentOf(parentOf(currentNode)));
        byte ob = grandpa != null ? grandpa.getBalanceFactor() : 0;
        rotateRight(parentOf(currentNode));
        if (greatGrandfather != null) {
            byte nb = grandpa.getBalanceFactor();
            incHeight = calculateIncHeight(ob, nb, (LightHeapNode<T>) currentNode);
            greatGrandfather.incBalanceFactor(leftOf(greatGrandfather) == grandpa ? (byte) incHeight : (byte) -incHeight);
        }
        rotateLeft(parentOf(currentNode));
    }

    protected void zigZagLiftRight(BinTreeNodeInterface<T> currentNode) {
        int incHeight;
        LightHeapNode<T> grandpa = (LightHeapNode<T>) parentOf(parentOf(currentNode));
        LightHeapNode<T> greatGrandfather = (LightHeapNode<T>) parentOf(parentOf(parentOf(currentNode)));
        byte ob = grandpa != null ? grandpa.getBalanceFactor() : 0;
        rotateLeft(parentOf(currentNode));
        if (greatGrandfather != null) {
            byte nb = grandpa.getBalanceFactor();
            incHeight = calculateIncHeight(ob, nb, currentNode);
            greatGrandfather.incBalanceFactor(leftOf(greatGrandfather) == grandpa ? (byte) incHeight : (byte) -incHeight);
        }
        rotateRight(parentOf(currentNode));
    }

    protected boolean choiceLeftOrRight(BinTreeNodeInterface<T> left, BinTreeNodeInterface<T> right) {
        LightHeapNode<T> parent = (LightHeapNode<T>) parentOf(left);
        if (parent != null) {
            byte bf = parent.getBalanceFactor();
            switch (Integer.signum(bf)) {
                case -1: {
                    return false;
                }
                case 1: {
                    return true;
                }
                default: {
                    return super.choiceLeftOrRight(left, right);
                }
            }
        } else {
            return super.choiceLeftOrRight(left, right);
        }
    }

    protected void onMergeSplay(BinTreeNodeInterface<T> extreme) {
        LightHeapNode<T> cursor = (LightHeapNode<T>) extreme;
        LightHeapNode<T> parent = (LightHeapNode<T>) parentOf(cursor);
        if (parent.getRight() == cursor) {
            cursor.setBalanceFactor(parent.getBalanceFactor());
            cursor.incBalanceFactor();
        } else {
            cursor.setBalanceFactor(parent.getBalanceFactor());
            cursor.decBalanceFactor();
        }
    }

    public void evict(final LightHeapNode<T> theCurrentNode) {
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

    public LightHeapNode<T> findLastUsed() {
        LightHeapNode<T> result = null;
        LightHeapNode<T> current = (LightHeapNode<T>) root;
        if (current != null) {
            do {
                if (current.getObjectNode() == null) {
                    if (current.getLeft() != null) {
                        current = (LightHeapNode<T>) current.getLeft();
                    } else {
                        break;
                    }
                } else {
                    if (current.getBalanceFactor() > 0) {
                        if (current.getLeft() != null) {
                            current = (LightHeapNode<T>) current.getLeft();
                        } else {
                            if (current.getRight() != null) {
                                throw new RuntimeException("AvlSplayHeap: invalid structure!");
                            }
                            result = current;
                            break;
                        }
                    } else if (current.getBalanceFactor() < 0) {
                        if (current.getRight() != null) {
                            current = (LightHeapNode<T>) current.getRight();
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
                                current = (LightHeapNode<T>) current.getLeft();
                            } else {
                                if (current.getRight() != null) {
                                    throw new RuntimeException("AvlSplayHeap: invalid structure!");
                                }
                                result = current;
                                break;
                            }
                        } else {
                            if (current.getRight() != null) {
                                current = (LightHeapNode<T>) current.getRight();
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

    public void restructureAfterRebalance(BinTreeNodeInterface<T> currentNode) {
        AVLBinTree.restructureAfterRebalanceAVL(currentNode);
    }

    @Override
    public int getNextId() {
        return 0;
    }

    @Override
    public void expungeStaleEntries() {
        if (ttl < Long.MAX_VALUE) {
            LightHeapNode<T> evictNode;
            long createTime;
            long currentTime = System.currentTimeMillis();
            do {
                evictNode = findLastUsed();
                if (evictNode != null) {
                    createTime = evictNode.getCreateTime();
                    if (currentTime > createTime + ttl) {
                        evict(evictNode);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } while (true);
        }
    }

    @Override
    public BinTreeNodeInterface<T> beforeSeekLoop(T theObject, BinTreeNodeInterface<T> currentNode, GeneralCall<T> generalCallObject) {
        expungeStaleEntries();
        return null;
    }

    @Override
    public void beforeGet() {
        expungeStaleEntries();
    }

    public static enum EvictionStrategy {
        SPLAY,
        SIMPLE
    }

}
