package don.juan.matus.lib.collection.sorted.tree.heap.avl.splay;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.BinTreeNodeBalanceFactor;

public class AvlSplayHeapNode<T extends Comparable<T>> extends BinTreeNodeBalanceFactor<T> {

    private long createTime = System.currentTimeMillis();

    public AvlSplayHeapNode(T objectNode, BinTreeNodeBalanceFactor<T> left, BinTreeNodeBalanceFactor<T> parent, BinTreeNodeBalanceFactor<T> right) {
        super(objectNode, left, parent, right);
    }

    public long getCreateTime() {
        return createTime;
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new AvlSplayHeapNode(
                theObject,
                (BinTreeNodeBalanceFactor<T>) theLeft,
                (BinTreeNodeBalanceFactor<T>) theParent,
                (BinTreeNodeBalanceFactor<T>) theRight);
    }

    @Override
    public String toString() {
        return "AvlSplayHeapNode{" +
                "objectNode=" + objectNode +
                ", balanceFactor=" + balanceFactor +
                '}';
    }


}