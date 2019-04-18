package don.juan.matus.lib.bintree.rotatetree.avl;

import don.juan.matus.lib.bintree.BinTreeNodeBase;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;

/**
 * Нода двоичного дерева с баланс фактором.
 * Для AVL деревьев.
 */
public class BinTreeNodeBF<T extends Comparable<T>> extends BinTreeNodeBase<T> {

    private byte balanceFactor;

    public BinTreeNodeBF(T objectNode, BinTreeNodeBF<T> left, BinTreeNodeBF<T> parent, BinTreeNodeBF<T> right) {
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
        return new BinTreeNodeBF(
                theObject,
                (BinTreeNodeBF<T>) theLeft,
                (BinTreeNodeBF<T>) theParent,
                (BinTreeNodeBF<T>) theRight);
    }

    @Override
    public String toString() {
        return "BinTreeNodeBF{" +
                "objectNode=" + objectNode +
                ", balanceFactor=" + balanceFactor +
                '}';
    }

    public String desc() {
        return objectNode + "," + balanceFactor;
    }


}
