package don.juan.matus.lib.collection.sorted.tree.heap;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.BinTreeNodeBalanceFactor;

public class LightHeapNode<T extends Comparable<T>> extends BinTreeNodeBalanceFactor<T> {

    private long createTime = System.currentTimeMillis();

    public LightHeapNode(BinTreeInterface<T> owner, T objectNode, BinTreeNodeBalanceFactor<T> left, BinTreeNodeBalanceFactor<T> parent, BinTreeNodeBalanceFactor<T> right) {
        super(owner, objectNode, left, parent, right);
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
        LightHeapNode<T> ob = (LightHeapNode<T>) o;
        return (int)(createTime - ob.createTime);
    }

    @Override
    public String toString() {
        return "LightHeapNode{" +
                "objectNode = " + objectNode +
                ", createTime = " + createTime +
                ", balanceFactor = " + balanceFactor +
                '}';
    }

}
