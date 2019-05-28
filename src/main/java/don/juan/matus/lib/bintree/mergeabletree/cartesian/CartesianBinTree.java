package don.juan.matus.lib.bintree.mergeabletree.cartesian;

import don.juan.matus.lib.bintree.BinTreeBase;
import don.juan.matus.lib.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.bintree.BinTreeIterator;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;


public class CartesianBinTree<T extends Comparable<T>> extends BinTreeBase<T> implements MergeableCartesianBinTree<T> {

    private MergeableCartesianBinTree.MergeParts parts;
    private long mergeCount;

    public CartesianBinTree() {
        super();
        root = new BinTreeNodeWithPriorityInNode<>(null, null, null, null);
        parts = new MergeableCartesianBinTree.MergeParts();
        mergeCount = 0L;
    }

    public long getMergeCount() {
        return mergeCount;
    }

    public void setMergeCount(long mergeCount) {
        this.mergeCount = mergeCount;
    }

    public MergeParts getParts() {
        return parts;
    }

    @Override
    public boolean add(T theObject) {
        splitCartesian(parts, (BinTreeNodeCartesianBinTree<T>) root.getLeft(), theObject);
        BinTreeNodeCartesianBinTree<T> newNode = (BinTreeNodeCartesianBinTree<T>) root.createNode(theObject, null, null, null);
        //System.out.println(newNode);
        BinTreeNodeCartesianBinTree<T> newTree = mergeCartesian(mergeCartesian(parts.leftTree, newNode), parts.rightTree);
        newTree.setParent(root);
        root.setLeft(newTree);
        size++;
        return true;
    }

    @Override
    public BinTreeNodeCartesianBinTree<T> mergeCartesian(BinTreeNodeCartesianBinTree<T> left, BinTreeNodeCartesianBinTree<T> right) {
        BinTreeNodeCartesianBinTree<T> result = null;
        BinTreeNodeCartesianBinTree<T> parent = null;
        BinTreeNodeCartesianBinTree<T> tmp;
        boolean isLeft = true;
        while (left != null && right != null) {
            if (left.getPriority() < right.getPriority()) {
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
                tmp = (BinTreeNodeCartesianBinTree<T>) right.getLeft();
                if (tmp != null)tmp.setParent(null);
                right.setLeft(left);
                left.setParent(right);
                parent = right;
                isLeft = true;
                right = tmp;
            } else {
                if (result == null) {
                    result = left;
                    parent = (BinTreeNodeCartesianBinTree<T>) left.getParent();
                }
                if (parent != null) {
                    left.setParent(parent);
                    if (isLeft)
                        parent.setLeft(left);
                    else
                        parent.setRight(left);
                }
                tmp = (BinTreeNodeCartesianBinTree<T>) left.getRight();
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
    public void splitCartesian(MergeParts parts, BinTreeNodeCartesianBinTree<T> tree, T key) {
        BinTreeNodeCartesianBinTree<T> current = tree;
        BinTreeNodeCartesianBinTree<T> tmpTree;
        BinTreeNodeCartesianBinTree<T> leftPart = null;
        BinTreeNodeCartesianBinTree<T> rightPart = null;
        parts.leftTree = null;
        parts.rightTree = null;
        while (current != null) {
            tmpTree = current;
            if (current.getObjectNode().compareTo(key) < 0) {
                current = (BinTreeNodeCartesianBinTree<T>) current.getRight();
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
                current = (BinTreeNodeCartesianBinTree<T>) current.getLeft();
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

    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        boolean result = super.checkTreeNode(thePassEvent, btiLeft, btiRight, currentNode, previousNode);
        if (result) {
            if (currentNode != null) {
                PriorityCartesianBinTreeNode current = (PriorityCartesianBinTreeNode) currentNode;
                PriorityCartesianBinTreeNode left = (PriorityCartesianBinTreeNode) currentNode.getLeft();
                PriorityCartesianBinTreeNode right = (PriorityCartesianBinTreeNode) currentNode.getRight();
                if (current.getPriority() < (left != null ? left.getPriority() : 0.0d) || current.getPriority() < (right != null ? right.getPriority() : 0.0d)) {
                    thePassEvent.setErrorMessage("CartesianBinTree: Tree structure invalid, incorrect priority!");
                    result = false;
                }
            }
        }
        return result;
    }


}
