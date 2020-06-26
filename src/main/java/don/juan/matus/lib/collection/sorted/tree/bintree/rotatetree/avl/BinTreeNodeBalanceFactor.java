package don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeBase;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;

/**
 * Нода двоичного дерева с баланс фактором.
 * Для AVL деревьев.
 */
public class BinTreeNodeBalanceFactor<T extends Comparable<T>> extends BinTreeNodeBase<T> {

    protected byte balanceFactor;

    public BinTreeNodeBalanceFactor(T objectNode, BinTreeNodeBalanceFactor<T> left, BinTreeNodeBalanceFactor<T> parent, BinTreeNodeBalanceFactor<T> right) {
        super(objectNode, left, parent, right);
        this.balanceFactor = 0;
    }

    public byte getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(byte balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public byte incBalanceFactor() {
        return balanceFactor++;
    }

    public byte incBalanceFactor(byte theVal) {
        return balanceFactor+=theVal;
    }

    public byte decBalanceFactor() {
        return balanceFactor--;
    }

    public byte decBalanceFactor(byte theVal) {
        return balanceFactor-=theVal;
    }

    @Override
    public void onGoLeft(){
    }

    @Override
    public void onGoRight(){
    }


    @Override
    public BinTreeNodeInterface<T> createNode(
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new BinTreeNodeBalanceFactor(
                theObject,
                (BinTreeNodeBalanceFactor<T>) theLeft,
                (BinTreeNodeBalanceFactor<T>) theParent,
                (BinTreeNodeBalanceFactor<T>) theRight);
    }

    @Override
    public String toString() {
        return "BinTreeNodeBalanceFactor{" +
                "objectNode=" + objectNode +
                ", balanceFactor=" + balanceFactor +
                '}';
    }

    public String desc() {
        return objectNode + "," + balanceFactor;
    }


}
