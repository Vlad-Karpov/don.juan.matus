package don.juan.matus.lib.collection.sorted.tree.bintree.mergeabletree.cartesian;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;

public class BinTreeNodeWithPriority<T extends Comparable<? extends T> & PriorityCartesianBinTreeNode> extends BinTreeNodeCartesianBinTree<T> {

    public BinTreeNodeWithPriority(
            BinTreeInterface<T> owner,
            T objectNode,
            BinTreeNodeWithPriority<T> left,
            BinTreeNodeWithPriority<T> parent,
            BinTreeNodeWithPriority<T> right) {
        super(owner, objectNode, left, parent, right);
    }

    @Override
    public double getPriority() {
        return objectNode.getPriority();
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            BinTreeInterface<T> owner,
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new BinTreeNodeWithPriority(
                owner,
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
