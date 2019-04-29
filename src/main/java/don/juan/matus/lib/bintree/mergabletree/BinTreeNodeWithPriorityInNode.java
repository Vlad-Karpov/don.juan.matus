package don.juan.matus.lib.bintree.mergabletree;

import don.juan.matus.lib.bintree.BinTreeNodeBase;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;

public class BinTreeNodeWithPriorityInNode<T extends Comparable<T>> extends BinTreeNodeBase<T> implements PriorityDecartBinTreeNode {

    private Long priority;

    public BinTreeNodeWithPriorityInNode(T objectNode, BinTreeNodeWithPriorityInNode<T> left, BinTreeNodeWithPriorityInNode<T> parent, BinTreeNodeWithPriorityInNode<T> right) {
        super(objectNode, left, parent, right);
        priority = Math.round(Math.random() * Long.MAX_VALUE);
    }

    @Override
    public Long getPriority() {
        return priority;
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new BinTreeNodeWithPriorityInNode(
                theObject,
                (BinTreeNodeWithPriorityInNode<T>) theLeft,
                (BinTreeNodeWithPriorityInNode<T>) theParent,
                (BinTreeNodeWithPriorityInNode<T>) theRight);
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
