package don.juan.matus.lib.bintree.mergeabletree.cartesian;

import don.juan.matus.lib.bintree.BinTreeBase;


public class CartesianBinTree<T extends Comparable<T>> extends BinTreeBase<T> implements MergeableCartesianBinTree<T> {

    public CartesianBinTree() {
        super();
        root = new BinTreeNodeWithPriorityInNode<>(null, null, null, null);
    }

    @Override
    public BinTreeNodeCartesianBinTree<T> merge(BinTreeNodeCartesianBinTree<T> left, BinTreeNodeCartesianBinTree<T> right) {
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
            }
        }
        return result;
    }

    @Override
    public void split(MergeParts parts, BinTreeNodeCartesianBinTree<T> tree, T key) {
        BinTreeNodeCartesianBinTree<T> current = tree;
        parts.leftTree = null;
        parts.rightTree = null;
        while (current != null) {
            if (current.getObjectNode().compareTo(key) < 0) {
                if (parts.leftTree == null) {
                    parts.leftTree = current;
                } else {
                    parts.leftTree = merge(parts.leftTree, current);
                }
                current = (BinTreeNodeCartesianBinTree<T>) current.getRight();
                if (current != null) {
                    if (current.getParent() != null) current.getParent().setRight(null);
                    current.setParent(null);
                }
            } else {
                if (parts.rightTree == null) {
                    parts.rightTree = current;
                } else {
                    parts.rightTree = merge(current, parts.rightTree);
                }
                current = (BinTreeNodeCartesianBinTree<T>) current.getLeft();
                if (current != null) {
                    if (current.getParent() != null) current.getParent().setLeft(null);
                    current.setParent(null);
                }
            }
        }
    }

}
