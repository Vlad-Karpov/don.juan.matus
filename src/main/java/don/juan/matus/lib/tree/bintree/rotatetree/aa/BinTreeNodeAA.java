package don.juan.matus.lib.tree.bintree.rotatetree.aa;

import don.juan.matus.lib.tree.bintree.BinTreeNodeBase;
import don.juan.matus.lib.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.tree.bintree.rotatetree.red_black.BinTreeNodeRedBlack;

public class BinTreeNodeAA<T extends Comparable<T>> extends BinTreeNodeBase<T> {

    short level;

    public BinTreeNodeAA(T objectNode, BinTreeNodeBase<T> left, BinTreeNodeBase<T> parent, BinTreeNodeBase<T> right) {
        super(objectNode, left, parent, right);
        level = 1;
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new BinTreeNodeAA(
                theObject,
                (BinTreeNodeRedBlack<T>) theLeft,
                (BinTreeNodeRedBlack<T>) theParent,
                (BinTreeNodeRedBlack<T>) theRight);
    }

    @Override
    public String toString() {
        return "BinTreeNodeAA{" +
                "objectNode=" + objectNode +
                ", level=" + level +
                '}';
    }

    public String desc() {
        return objectNode + ", " + level;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

}
