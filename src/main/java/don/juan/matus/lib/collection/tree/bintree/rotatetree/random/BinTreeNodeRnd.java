package don.juan.matus.lib.collection.tree.bintree.rotatetree.random;

import don.juan.matus.lib.collection.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.collection.tree.bintree.rotatetree.waight.BinTreeNodeWeight;

/**
 * Нода для вероятностного дерева.
 */
public class BinTreeNodeRnd<T extends Comparable<T>> extends BinTreeNodeWeight<T> {

    public BinTreeNodeRnd(T objectNode, BinTreeNodeRnd<T> left, BinTreeNodeRnd<T> parent, BinTreeNodeRnd<T> right) {
        super(objectNode, left, parent, right);
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new BinTreeNodeRnd(
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
