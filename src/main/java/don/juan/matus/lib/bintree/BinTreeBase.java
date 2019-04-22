package don.juan.matus.lib.bintree;

import java.util.*;

/**
 * Base class binary tree.
 * Not balances binary tree.
 */
public class BinTreeBase<T extends Comparable<T>>
        extends AbstractSet<T>
        implements BinTreeInterface<T>, NavigableSet<T>, Cloneable, java.io.Serializable {

    public static byte LEFT = 0;
    public static byte RIGHT = 1;

    protected BinTreeNodeInterface<T> root;
    protected Long maxLevel;
    protected Long level;
    protected Long size;
    private GeneralCall<T> removeGeneralCall = new GeneralCall<T>() {

        @Override
        public void generalCall(Boolean theResult, Integer theCmp, BinTreeNodeInterface<T> resultNode, Object generalObject) {
            if (theResult) removeNode(false, resultNode, null);
        }
    };

    public BinTreeBase() {
        super();
        root = new BinTreeNodeBase<T>(null, null, null, null);
        maxLevel = 0L;
        level = 0L;
        size = 0L;
    }

    @Override
    public BinTreeNodeInterface<T> getRoot() {
        return root;
    }

    public Long getMaxLevel() {
        return maxLevel;
    }

    @Override
    public Long getLevel() {
        return level;
    }

    @Override
    public Long getSize() {
        return size;
    }

    @Override
    public void passStraight(BinTreePassEvent<BinTreeNodeInterface<T>> thePassEvent) {
        passStraight(thePassEvent, root);
    }

    @Override
    public void passBack(BinTreePassEvent<BinTreeNodeInterface<T>> thePassEvent) {
        passBack(thePassEvent, root);
    }

    protected void passStraight(BinTreePassEvent<BinTreeNodeInterface<T>> thePassEvent, BinTreeNodeInterface<T> node) {
        thePassEvent.incLevel(node);
        if (node.getLeft() != null) {
            thePassEvent.incLeft(node);
            passStraight(thePassEvent, node.getLeft());
            thePassEvent.decLeft(node);
        }
        if (node != root) thePassEvent.onPass(node);
        if (node.getRight() != null) {
            thePassEvent.incRight(node);
            passStraight(thePassEvent, node.getRight());
            thePassEvent.decRight(node);
        }
        thePassEvent.decLevel(node);
        if (node != root) thePassEvent.onNodeCompleted(node);
    }

    protected void passBack(BinTreePassEvent<BinTreeNodeInterface<T>> thePassEvent, BinTreeNodeInterface<T> node) {
        thePassEvent.incLevel(node);
        if (node.getRight() != null) {
            thePassEvent.incRight(node);
            passBack(thePassEvent, node.getRight());
            thePassEvent.decRight(node);
        }
        if (node != root) thePassEvent.onPass(node);
        if (node.getLeft() != null) {
            thePassEvent.incLeft(node);
            passBack(thePassEvent, node.getLeft());
            thePassEvent.decLeft(node);
        }
        thePassEvent.decLevel(node);
        if (node != root) thePassEvent.onNodeCompleted(node);
    }

    @Override
    public void checkStructure(BinTreeCheckPassEvent<T> thePassEvent) {
        BinTreeNodeInterface<T> previosNode = null;
        BinTreeIterator<T> bti = new BinTreeIterator<T>(this, root);
        while (bti.hasNext()) {
            BinTreeIterator<T> btiLeft = null;
            BinTreeIterator<T> btiRight = null;
            if (bti.getCurrentNode().getLeft() != null) {
                btiLeft = new BinTreeIterator<T>(this, bti.getCurrentNode().getLeft());
                while (btiLeft.hasNext()) btiLeft.next();
            }
            if (bti.getCurrentNode().getRight() != null) {
                btiRight = new BinTreeIterator<T>(this, bti.getCurrentNode().getRight());
                while (btiRight.hasNext()) btiRight.next();
            }
            if (!checkTreeNode(thePassEvent, btiLeft, btiRight, bti.getCurrentNode(), previosNode)) {
                thePassEvent.onPass(btiLeft, btiRight, bti.getCurrentNode(), previosNode);
            }
            previosNode = bti.getCurrentNode();
            bti.next();
        }
    }

    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        boolean result = true;
        if (previousNode != null && currentNode != null && previousNode.getObjectNode() != null && currentNode.getObjectNode() != null) {
            if (previousNode.compareTo(currentNode) > 0) {
                thePassEvent.setErrorMessage("BinTreeBase: Tree structure invalid, previous value greeter then current!");
                result = false;
            }
        }
        return result;
    }

    @Override
    public T lower(T t) {
        return null;
    }

    @Override
    public T floor(T t) {
        return null;
    }

    @Override
    public T ceiling(T t) {
        return null;
    }

    @Override
    public T higher(T t) {
        return null;
    }

    @Override
    public T pollFirst() {
        return null;
    }

    @Override
    public T pollLast() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new BinTreeIterator(this, root);
    }

    @Override
    public NavigableSet<T> descendingSet() {
        return null;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new DescendingBinTreeIterator(this, root);
    }

    @Override
    public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableSet<T> headSet(T toElement, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableSet<T> tailSet(T fromElement, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return null;
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        return null;
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return null;
    }

    @Override
    public T first() {
        return null;
    }

    @Override
    public T last() {
        return null;
    }

    @Override
    public int size() {
        return size.intValue();
    }

    @Override
    public Iterator<T> iterator(T theObject) {
        return new BinTreeIterator(this, root, theObject);
    }

    @Override
    public Iterator<T> descendingIterator(T theObject) {
        return new DescendingBinTreeIterator(this, root, theObject);
    }

    @Override
    public boolean add(T theObject) {
        level = 0L;
        addLoop(theObject, root);
        size++;
        return true;
    }

    protected BinTreeNodeInterface<T> addLoop(final T theObject, final BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeInterface<T> current = currentNode;
        do {
            current.onNode();
            level++;
            if (maxLevel < level) maxLevel = level;
            if (current.getObjectNode() == null) {
                current.onGoLeft();
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    current.setLeft(current.createNode(theObject, null, current, null));
                    current = current.getLeft();
                    break;
                }
            } else {
                if (theObject.compareTo(current.getObjectNode()) < 0) {
                    current.onGoLeft();
                    if (current.getLeft() != null) {
                        current = current.getLeft();
                    } else {
                        current.setLeft(current.createNode(theObject, null, current, null));
                        current = current.getLeft();
                        break;
                    }
                } else {
                    current.onGoRight();
                    if (current.getRight() != null) {
                        current = current.getRight();
                    } else {
                        current.setRight(current.createNode(theObject, null, current, null));
                        current = current.getRight();
                        break;
                    }
                }
            }
        } while (true);
        return postAddLoop(current);
    }

    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        return currentNode;
    }

    @Override
    public void remove(T theObject) {
        seekLoop(theObject, root, removeGeneralCall);
    }

    @Override
    public Boolean seek(T theObject, GeneralCall<T> generalCallObject) {
        return seekLoop(theObject, root, generalCallObject);
    }

    @Override
    public Boolean seekLoop(T theObject, BinTreeNodeInterface<T> currentNode, GeneralCall<T> generalCallObject) {
        Boolean result = false;
        BinTreeNodeInterface<T> current = currentNode;
        if (current != null) {
            do {
                if (current.getObjectNode() == null) {
                    if (current.getLeft() != null) {
                        current = current.getLeft();
                    } else {
                        if (generalCallObject != null) generalCallObject.generalCall(result, null, current, null);
                        break;
                    }
                } else {
                    int cmp = theObject.compareTo(current.getObjectNode());
                    if (cmp == 0) {
                        result = true;
                        if (generalCallObject != null) generalCallObject.generalCall(result, cmp, current, null);
                        break;
                    } else if (cmp < 0) {
                        if (current.getLeft() != null) {
                            current = current.getLeft();
                        } else {
                            if (generalCallObject != null) generalCallObject.generalCall(result, cmp, current, null);
                            break;
                        }
                    } else {
                        if (current.getRight() != null) {
                            current = current.getRight();
                        } else {
                            if (generalCallObject != null) generalCallObject.generalCall(result, cmp, current, null);
                            break;
                        }
                    }
                }
            } while (true);
        } else {
            if (generalCallObject != null) generalCallObject.generalCall(result, null, current, null);
        }
        return result;
    }

    @Override
    public BinTreeNodeInterface<T> removeNode(
            Boolean theDescending,
            BinTreeNodeInterface<T> currentNode,
            BinTreeNodeInterface<T> nextNode) {
        BinTreeNodeInterface<T> next;
        BinTreeNodeInterface<T> target;
        BinTreeNodeInterface<T> result = nextNode;
        BinTreeNodeInterface<T> current = currentNode;
        if (currentNode != null) {
            if (current.getLeft() == null && current.getRight() == null) {
                //
                changeNode(current);
                //
                //remove current
                BinTreeNodeInterface<T> parent = current.getParent();
                if (parent.getLeft() == current) {
                    parent.setLeft(null);
                }
                if (parent.getRight() == current) {
                    parent.setRight(null);
                }
                current.setParent(null);
                //
                size--;
                //
            } else {
                if (current.getRight() != null) {
                    next = current;
                    do {
                        target = next;
                        next = next.getRight();
                        do {
                            if (next.getLeft() != null) {
                                next = next.getLeft();
                            } else {
                                break;
                            }
                        } while (true);
                        //replace next to target
                        if (result != null) {
                            if (next == result) {
                                result = target;
                            }
                        }
                        target.setObjectNode(next.getObjectNode());
                        //
                        if (next.getRight() == null) {
                            break;
                        }
                    } while (true);
                    //
                    changeNode(next);
                    //
                    //remove next
                    BinTreeNodeInterface<T> parent = next.getParent();
                    if (parent.getLeft() == next) {
                        parent.setLeft(null);
                    }
                    if (parent.getRight() == next) {
                        parent.setRight(null);
                    }
                    next.setParent(null);
                    next.setLeft(null);
                    next.setRight(null);
                    //
                    size--;
                    //
                } else {
                    //remove current from left (right) linked list
                    changeNode(current);
                    //
                    BinTreeNodeInterface<T> leftNode = current.getLeft();
                    BinTreeNodeInterface<T> parent = current.getParent();
                    if (parent.getLeft() == current) {
                        parent.setLeft(leftNode);
                    }
                    if (parent.getRight() == current) {
                        parent.setRight(leftNode);
                    }
                    leftNode.setParent(parent);
                    current.setParent(null);
                    current.setLeft(null);
                    current.setRight(null);
                    //
                    size--;
                    //
                }
            }
        }
        return result;
    }

    @Override
    public void clearTree() {
        root.setLeft(null);
        maxLevel = 0L;
        level = 0L;
        size = 0L;
    }

    protected void changeNode(BinTreeNodeInterface<T> theCurrentNode) {
        //
    }

    protected int min(int b1, int b2) {
        return b1 < b2 ? b1 : b2;
    }

    protected int min(int b1, int b2, int b3) {
        int m = min(b1, b2);
        return m < b3 ? m : b3;
    }

    protected int max(int b1, int b2) {
        return b1 > b2 ? b1 : b2;
    }

    protected int max(int b1, int b2, int b3) {
        int m = max(b1, b2);
        return m > b3 ? m : b3;
    }

    protected int abs(int b1) {
        return (b1 < 0) ? -b1 : b1;
    }

}
