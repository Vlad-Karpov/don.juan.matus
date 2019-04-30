package don.juan.matus.lib.bintree.mergabletree.cartesian;

import don.juan.matus.lib.bintree.BinTreeNodeInterface;

public class BinTreeNodeWithPriority<T extends Comparable<T> & PriorityCartesianBinTreeNode> extends BinTreeNodeCartesianBinTree<T> {

    public BinTreeNodeWithPriority(T objectNode, BinTreeNodeWithPriority<T> left, BinTreeNodeWithPriority<T> parent, BinTreeNodeWithPriority<T> right) {
        super(objectNode, left, parent, right);
    }

    @Override
    public double getPriority() {
        return objectNode.getPriority();
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new BinTreeNodeWithPriority(
                theObject,
                (BinTreeNodeWithPriority<T>) theLeft,
                (BinTreeNodeWithPriority<T>) theParent,
                (BinTreeNodeWithPriority<T>) theRight);
    }

    @Override
    public String toString() {
        return "BinTreeNodeWithPriority{" +
                "objectNode=" + objectNode +
                ", priority=" + objectNode.getPriority() +
                '}';
    }

    public String desc() {
        return objectNode + "," + objectNode.getPriority();
    }

}
