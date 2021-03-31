package don.juan.matus.lib.collection.sorted.tree.heap;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.BinTreeNodeBalanceFactor;

public class HeapNode<T extends Comparable<? extends T>> extends LightHeapNode<T> {

    private long createTime = System.currentTimeMillis();
    private int id;

    public HeapNode(BinTreeInterface<T> owner, T objectNode, BinTreeNodeBalanceFactor<T> left, BinTreeNodeBalanceFactor<T> parent, BinTreeNodeBalanceFactor<T> right) {
        super(owner, objectNode, left, parent, right);
        id = ((HeapInterface<T>)owner).getNextId();
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            BinTreeInterface<T> owner,
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new HeapNode(
                owner,
                theObject,
                (BinTreeNodeBalanceFactor<T>) theLeft,
                (BinTreeNodeBalanceFactor<T>) theParent,
                (BinTreeNodeBalanceFactor<T>) theRight);
    }

    public int compareTo(BinTreeNodeInterface<T> o) {
        HeapNode<T> ob = (HeapNode<T>) o;
        return id - ob.id;
    }

    @Override
    public String toString() {
        return "HeapNode{" +
                "objectNode = " + objectNode +
                ", id = " + id +
                ", createTime = " + createTime +
                ", balanceFactor = " + balanceFactor +
                '}';
    }

}
