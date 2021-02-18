package don.juan.matus.lib.collection.sorted.tree.bintree;

import don.juan.matus.lib.collection.sorted.tree.TreeBase;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Base class binary tree.
 */
public class BinTreeBase<T extends Comparable<T>>
        extends TreeBase<T>
        implements BinTreeInterface<T>
        , RotateBalancedBinTree<T>
        , MergeableBinTree<T> {

    public static byte LEFT = 0;
    public static byte RIGHT = 1;

    protected BinTreeNodeInterface<T> root;
    protected Long maxLevel;
    protected Long level;
    protected Long rotateCount;
    private GeneralCall<T> removeGeneralCall = (theResult, theCmp, resultNode, generalObject) -> {
        if (theResult) removeNode(false, resultNode, null);
    };
    protected MergeStrategy<T> mergeStrategy = (left, right) -> false;
    protected MergeableBinTree.MergeParts parts;
    protected long mergeCount;

    public BinTreeBase() {
        super();
        root = new BinTreeNodeBase<T>(null, null, null, null);
        maxLevel = 0L;
        level = 0L;
        size = 0L;
        rotateCount = 0L;
        parts = new MergeableBinTree.MergeParts();
        mergeCount = 0L;
    }

    @Override
    public long getMergeCount() {
        return mergeCount;
    }

    @Override
    public void setMergeCount(long mergeCount) {
        this.mergeCount = mergeCount;
    }

    @Override
    public MergeableBinTree.MergeParts getParts() {
        return parts;
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
        thePassEvent.onPassCompleted();
    }

    @Override
    public void passBack(BinTreePassEvent<BinTreeNodeInterface<T>> thePassEvent) {
        passBack(thePassEvent, root);
        thePassEvent.onPassCompleted();
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

    //todo: to do this!
    @Override
    public Long checkStructure(BinTreeNodeInterface<T> theRoot) {
        return checkStructure(new BinTreeDebugPassEvent(), root);
    }

    @Override
    public Long checkStructure(BinTreeCheckPassEvent<T> thePassEvent, BinTreeNodeInterface<T> theRoot) {
        Long result = 0L;
        BinTreeNodeInterface<T> previosNode = null;
        BinTreeIterator<T> bti = new BinTreeIterator<T>(this, theRoot);
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
            result++;
            bti.next();
        }
        return result;
    }

    @Override
    public Long checkStructure(BinTreeCheckPassEvent<T> thePassEvent) {
        return checkStructure(thePassEvent, root);
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

    public static <T extends Comparable<T>> TreeProps treePassage(final BinTreeNodeInterface<T> theRoot) {
        TreeProps result = new TreeProps();
        if (theRoot != null) {
            Long currentHeght = 0L;
            Long maxHeght = 0L;
            BinTreeNodeInterface<T> cursor = theRoot;
            byte direction = 0;
            while (true) {
                if (direction == 0) {
                    if (cursor.getLeft() != null) {
                        cursor = cursor.getLeft();
                        direction = 0;
                        currentHeght++;
                        if (maxHeght < currentHeght) maxHeght = currentHeght;
                    } else {
                        direction = 1;
                    }
                }
                if (direction == 1) {
                    if (cursor.getRight() != null) {
                        cursor = cursor.getRight();
                        direction = 0;
                        currentHeght++;
                        if (maxHeght < currentHeght) maxHeght = currentHeght;
                    } else {
                        direction = 2;
                    }
                }
                if (direction == 2) {
                    currentHeght--;
                    result.weight++;
                    if (cursor != theRoot) {
                        if (cursor.getParent().getLeft() == cursor) {
                            cursor = cursor.getParent();
                            direction = 1;
                            continue;
                        }
                        if (cursor.getParent().getRight() == cursor) {
                            cursor = cursor.getParent();
                            direction = 2;
                        }
                    } else {
                        break;
                    }
                }
            }
            result.heght = maxHeght;
        }
        return result;
    }

    @Override
    public BinTreeNodeInterface<T> merge(BinTreeNodeInterface<T> left, BinTreeNodeInterface<T> right) {
        BinTreeNodeInterface<T> result = null;
        BinTreeNodeInterface<T> parent = null;
        BinTreeNodeInterface<T> tmp;
        boolean isLeft = true;
        while (left != null && right != null) {
            if (mergeStrategy.solveIt(left, right)) {
                if (result == null) {
                    result = right;
                }
                if (parent != null) {
                    right.setParent(parent);
                    if (isLeft)
                        parent.setLeft(right);
                    else
                        parent.setRight(right);
                }
                tmp = right.getLeft();
                if (tmp != null) tmp.setParent(null);
                right.setLeft(left);
                left.setParent(right);
                parent = right;
                isLeft = true;
                right = tmp;
            } else {
                if (result == null) {
                    result = left;
                    parent = left.getParent();
                }
                if (parent != null) {
                    left.setParent(parent);
                    if (isLeft)
                        parent.setLeft(left);
                    else
                        parent.setRight(left);
                }
                tmp = left.getRight();
                if (tmp != null) tmp.setParent(null);
                left.setRight(right);
                right.setParent(left);
                parent = left;
                isLeft = false;
                left = tmp;
            }
        }
        if (result == null) {
            result = (left != null ? left : right);
        }
        mergeCount++;
        return result;
    }

    @Override
    public void split(MergeParts parts, BinTreeNodeInterface<T> tree, T key) {
        BinTreeNodeInterface<T> current = tree;
        BinTreeNodeInterface<T> tmpTree;
        BinTreeNodeInterface<T> leftPart = null;
        BinTreeNodeInterface<T> rightPart = null;
        parts.leftTree = null;
        parts.rightTree = null;
        while (current != null) {
            tmpTree = current;
            if (current.getObjectNode().compareTo(key) < 0) {
                current = current.getRight();
                if (current != null) {
                    tmpTree.setRight(null);
                    current.setParent(null);
                }
                if (parts.leftTree == null) {
                    parts.leftTree = tmpTree;
                    leftPart = parts.leftTree;
                } else {
                    leftPart.setRight(tmpTree);
                    tmpTree.setParent(leftPart);
                    leftPart = tmpTree;
                }
            } else {
                current = current.getLeft();
                if (current != null) {
                    tmpTree.setLeft(null);
                    current.setParent(null);
                }
                if (parts.rightTree == null) {
                    parts.rightTree = tmpTree;
                    rightPart = parts.rightTree;
                } else {
                    rightPart.setLeft(tmpTree);
                    tmpTree.setParent(rightPart);
                    rightPart = tmpTree;

                }
            }
        }
        mergeCount++;
    }

    public static class TreeProps {
        Long heght = 0L;
        Long weight = 0L;
    }

    public static <T extends Comparable<T>> long sizeOfNode(BinTreeNodeInterface<T> cursor) {
        TreeProps tp = treePassage(cursor);
        return tp.weight;
    }

    @Override
    public TreeProps treePassage() {
        return treePassage(root.getLeft());
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinTreeIterator<>(this, root);
    }

    @NotNull
    @Override
    public Iterator<T> descendingIterator() {
        return new DescendingBinTreeIterator<>(this, root);
    }

    @Override
    public int size() {
        return size.intValue();
    }

    @NotNull
    @Override
    public Iterator<T> iterator(T theObject) {
        return new BinTreeIterator<>(this, root, theObject);
    }

    @NotNull
    @Override
    public Iterator<T> descendingIterator(T theObject) {
        return new DescendingBinTreeIterator<>(this, root, theObject);
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
        current = beforeAddLoop(current);
        do {
            current.onNode();
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
                level++;
                if (maxLevel < level) maxLevel = level;
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

    protected BinTreeNodeInterface<T> beforeAddLoop(final BinTreeNodeInterface<T> currentNode) {
        return currentNode;
    }

    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        return currentNode;
    }

    @Override
    public boolean remove(Object theObject) {
        return seekLoop((T)theObject, root, removeGeneralCall);
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
                        splay(root, current);
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
    public void splay(final BinTreeNodeInterface<T> theRoot, final BinTreeNodeInterface<T> currentNode) {
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
                removeFromLinkeList(current);
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
                    removeFromLinkeList(next);
                } else {
                    removeFromLinkeList(current);
                }
            }
        }
        return result;
    }

    private void removeFromLinkeList(BinTreeNodeInterface<T> current) {
        changeNode(current);
        BinTreeNodeInterface<T> leftNode = current.getLeft();
        BinTreeNodeInterface<T> parent = current.getParent();
        if (parent.getLeft() == current) {
            parent.setLeft(leftNode);
        }
        if (parent.getRight() == current) {
            parent.setRight(leftNode);
        }
        if (leftNode != null) leftNode.setParent(parent);
        current.setParent(null);
        current.setLeft(null);
        current.setRight(null);
        size--;
        changeNodeAfter(parent);
    }

    protected void changeNodeAfter(BinTreeNodeInterface<T> currentNode) {
        //
    }

    @Override
    public void clearTree() {
        root.setLeft(null);
        root.clear();
        maxLevel = 0L;
        level = 0L;
        size = 0L;
    }

    protected void changeNode(BinTreeNodeInterface<T> theCurrentNode) {
        //
    }

    public Long getRotateCount() {
        return rotateCount;
    }

    public void setRotateCount(Long rotateCount) {
        this.rotateCount = rotateCount;
    }

    public void afterRotateRight(BinTreeNodeInterface<T> dadNode, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> pivotNode) {

    }

    public void afterRotateLeft(BinTreeNodeInterface<T> dadNode, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> pivotNode) {

    }

    public BinTreeNodeInterface<T> rotateRight(BinTreeNodeInterface<T> currentNode) {
        rotateCount++;
        BinTreeNodeInterface<T> pivot = currentNode.getLeft();
        BinTreeNodeInterface<T> pivotRight = pivot.getRight();
        BinTreeNodeInterface<T> dad = currentNode.getParent();
        if (dad.getLeft() == currentNode) {
            dad.setLeft(pivot);
        } else {
            dad.setRight(pivot);
        }
        pivot.setParent(dad);
        pivot.setRight(currentNode);
        currentNode.setParent(pivot);
        if (pivotRight != null) pivotRight.setParent(currentNode);
        currentNode.setLeft(pivotRight);
        afterRotateRight(dad, currentNode, pivot);
        return pivot;
    }

    public BinTreeNodeInterface<T> rotateLeft(BinTreeNodeInterface<T> currentNode) {
        rotateCount++;
        BinTreeNodeInterface<T> pivot = currentNode.getRight();
        BinTreeNodeInterface<T> pivotLeft = pivot.getLeft();
        BinTreeNodeInterface<T> dad = currentNode.getParent();
        if (dad.getLeft() == currentNode) {
            dad.setLeft(pivot);
        } else {
            dad.setRight(pivot);
        }
        pivot.setParent(dad);
        pivot.setLeft(currentNode);
        currentNode.setParent(pivot);
        if (pivotLeft != null) pivotLeft.setParent(currentNode);
        currentNode.setRight(pivotLeft);
        afterRotateLeft(dad, currentNode, pivot);
        return pivot;
    }

    public BinTreeNodeInterface<T> rebalanceTree(BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeInterface<T> theRoot = currentNode;
        boolean theFirst = true;
        int wheight = 0;
        Double nDouble;
        int n;
        if (currentNode != null) {
            BinTreeNodeInterface<T> cursor = currentNode;
            do {
                while (cursor.getRight() != null)
                    cursor = rotateLeft(cursor);
                if (theFirst) {
                    theRoot = cursor;
                    theFirst = false;
                }
                wheight++;
                cursor = cursor.getLeft();
            } while (cursor != null);
            nDouble = Math.floor(Math.log(wheight) / Math.log(2));
            n = nDouble.intValue() - 1;
            for (int i = 0; i < n; i++) {
                theFirst = true;
                cursor = theRoot;
                wheight = wheight / 2;
                for (int j = 0; j < wheight; j++) {
                    cursor = rotateRight(cursor);
                    if (theFirst) {
                        theRoot = cursor;
                        theFirst = false;
                    }
                    cursor = cursor.getLeft();
                }
            }
        }
        restructureAfterRebalance(theRoot);
        return theRoot;
    }

    public void restructureAfterRebalance(BinTreeNodeInterface<T> currentNode) {
    }

    public void rebalanceTree() {
        root.setLeft(rebalanceTree(root.getLeft()));
    }

}
