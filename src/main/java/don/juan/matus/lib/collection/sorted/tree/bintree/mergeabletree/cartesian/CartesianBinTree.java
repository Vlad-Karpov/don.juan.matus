package don.juan.matus.lib.collection.sorted.tree.bintree.mergeabletree.cartesian;

import don.juan.matus.lib.collection.sorted.tree.bintree.*;
import don.juan.matus.lib.collection.sorted.tree.bintree.mergeabletree.MergeableBaseBinTree;


public class CartesianBinTree<T extends Comparable<? extends T>> extends MergeableBaseBinTree<T> {

    public CartesianBinTree() {
        super();
        root = new BinTreeNodeWithPriorityInNode<>(this, null, null, null, null);
        mergeStrategy = (left, right) -> ((BinTreeNodeCartesianBinTree<T>) left).getPriority() < ((BinTreeNodeCartesianBinTree<T>) right).getPriority();
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
