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
        BinTreeNodeCartesianBinTree<T> newTree = mergeCartesian(mergeCartesian(parts.leftTree, newNode), parts.rightTree);
        newTree.setParent(root);
        root.setLeft(newTree);
        return true;
    }

    @Override
    public BinTreeNodeCartesianBinTree<T> mergeCartesian(BinTreeNodeCartesianBinTree<T> left, BinTreeNodeCartesianBinTree<T> right) {
        BinTreeNodeCartesianBinTree<T> result = null;
        BinTreeNodeCartesianBinTree<T> current;
        while (left != null && right != null) {
            if (left.getPriority() < right.getPriority()) {
                current = right;
                if (result == null) result = current;
                right = (BinTreeNodeCartesianBinTree<T>) current.getLeft();
                current.setLeft(left);
                if (left.getParent() != null) {
                    if (left.getParent().getLeft() == left) {
                        left.getParent().setLeft(current);
                    } else {
                        left.getParent().setRight(current);
                    }
                }
                left.setParent(current);
                if (right != null) right.setParent(null);
            } else {
                current = left;
                if (result == null) result = current;
                left = (BinTreeNodeCartesianBinTree<T>) current.getRight();
                current.setRight(right);
                if (right.getParent() != null) {
                    if (right.getParent().getLeft() == right) {
                        right.getParent().setLeft(current);
                    } else {
                        right.getParent().setRight(current);
                    }
                }
                right.setParent(current);
                if (left != null) left.setParent(null);
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
