package don.juan.matus.lib.collection.sorted.tree.bintree.mergeabletree.cartesian;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;

public class BinTreeNodeWithPriorityInNode<T extends Comparable<? extends T>> extends BinTreeNodeCartesianBinTree<T> {

    private double priority;

    public BinTreeNodeWithPriorityInNode(
            BinTreeInterface<T> owner,
            T objectNode,
            BinTreeNodeWithPriorityInNode<T> left,
            BinTreeNodeWithPriorityInNode<T> parent,
            BinTreeNodeWithPriorityInNode<T> right) {
        super(owner, objectNode, left, parent, right);
        priority = Math.random();
    }

    @Override
    public double getPriority() {
        return priority;
    }

    public void setPriority(double thePriority) {
        priority = thePriority;
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            BinTreeInterface<T> owner,
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        if (theObject instanceof PriorityCartesianBinTreeNode) {
            return new BinTreeNodeWithPriority(owner, theObject,
                    (BinTreeNodeWithPriority) theLeft,
                    (BinTreeNodeWithPriority) theParent,
                    (BinTreeNodeWithPriority) theRight);
        } else {
            return new BinTreeNodeWithPriorityInNode<T>(
                    owner, theObject,
                    (BinTreeNodeWithPriorityInNode<T>) theLeft,
                    (BinTreeNodeWithPriorityInNode<T>) theParent,
                    (BinTreeNodeWithPriorityInNode<T>) theRight);
        }
    }

    @Override
    public String toString() {
        return "BinTreeNodeWithPriorityInNode{" +
                "objectNode=" + objectNode +
                ", priority=" + priority +
                '}';
    }

    public String desc() {
        return objectNode + "," + String.format("%.4f", priority);
    }

}
