package don.juan.matus.lib.collection.sorted.tree.heap;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.BinTreeNodeBalanceFactor;

public class HeapNode<T extends Comparable<T>> extends BinTreeNodeBalanceFactor<T> {

    private long createTime = System.currentTimeMillis();

    public HeapNode(T objectNode, BinTreeNodeBalanceFactor<T> left, BinTreeNodeBalanceFactor<T> parent, BinTreeNodeBalanceFactor<T> right) {
        super(objectNode, left, parent, right);
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new HeapNode(
                theObject,
                (BinTreeNodeBalanceFactor<T>) theLeft,
                (BinTreeNodeBalanceFactor<T>) theParent,
                (BinTreeNodeBalanceFactor<T>) theRight);
    }

    public int compareTo(BinTreeNodeInterface<T> o) {
        HeapNode<T> ob = (HeapNode<T>) o;
        return (int)(createTime - ob.createTime);
    }


    @Override
    public String toString() {
        return "HeapNode{" +
                "objectNode=" + objectNode +
                ", balanceFactor=" + balanceFactor +
                '}';
    }


}
