package don.juan.matus.lib.tree.bintree.rotatetree.aa;

import don.juan.matus.lib.tree.bintree.BinTreeNodeBase;
import don.juan.matus.lib.tree.bintree.BinTreeNodeInterface;

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
                (BinTreeNodeAA<T>) theLeft,
                (BinTreeNodeAA<T>) theParent,
                (BinTreeNodeAA<T>) theRight);
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

    public short incLevel() {
        return this.level++;
    }

    public short decLevel() {
        return this.level--;
    }

}
