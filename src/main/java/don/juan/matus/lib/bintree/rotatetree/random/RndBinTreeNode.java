package don.juan.matus.lib.bintree.rotatetree.random;

import don.juan.matus.lib.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.bintree.rotatetree.waight.BinTreeNodeW;

/**
 * Нода для вероятностного дерева.
 */
public class RndBinTreeNode<T extends Comparable<T>> extends BinTreeNodeW<T> {

    public RndBinTreeNode(T objectNode, RndBinTreeNode<T> left, RndBinTreeNode<T> parent, RndBinTreeNode<T> right) {
        super(objectNode, left, parent, right);
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new RndBinTreeNode(
                theObject,
                (RndBinTreeNode<T>)theLeft,
                (RndBinTreeNode<T>)theParent,
                (RndBinTreeNode<T>)theRight);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String desc() {
        return super.desc();
    }

}
