package don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.random;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.waight.BinTreeNodeWeight;

/**
 * Нода для вероятностного дерева.
 */
public class BinTreeNodeRnd<T extends Comparable<T>> extends BinTreeNodeWeight<T> {

    public BinTreeNodeRnd(
            BinTreeInterface<T> owner,
            T objectNode,
            BinTreeNodeRnd<T> left,
            BinTreeNodeRnd<T> parent,
            BinTreeNodeRnd<T> right) {
        super(owner, objectNode, left, parent, right);
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            BinTreeInterface<T> owner,
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new BinTreeNodeRnd(
                owner,
                theObject,
                (BinTreeNodeRnd<T>)theLeft,
                (BinTreeNodeRnd<T>)theParent,
                (BinTreeNodeRnd<T>)theRight);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String desc() {
        return super.desc();
    }

}
