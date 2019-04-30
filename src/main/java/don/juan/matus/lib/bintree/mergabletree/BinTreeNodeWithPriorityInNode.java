package don.juan.matus.lib.bintree.mergabletree;

import don.juan.matus.lib.bintree.BinTreeNodeBase;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;

public class BinTreeNodeWithPriorityInNode<T extends Comparable<T>> extends BinTreeNodeBase<T> implements PriorityCartesianBinTreeNode {

    private double priority;

    public BinTreeNodeWithPriorityInNode(T objectNode, BinTreeNodeWithPriorityInNode<T> left, BinTreeNodeWithPriorityInNode<T> parent, BinTreeNodeWithPriorityInNode<T> right) {
        super(objectNode, left, parent, right);
        priority = Math.random();
    }

    @Override
    public double getPriority() {
        return priority;
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        if (theObject instanceof PriorityCartesianBinTreeNode) {
            return new BinTreeNodeWithPriority(theObject,
                    (BinTreeNodeWithPriority) theLeft,
                    (BinTreeNodeWithPriority) theParent,
                    (BinTreeNodeWithPriority) theRight);
        } else {
            return new BinTreeNodeWithPriorityInNode<T>(
                    theObject,
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
        return objectNode + "," + priority;
    }

}
