package don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.aa;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeBase;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;

public class BinTreeNodeAA<T extends Comparable<? extends T>> extends BinTreeNodeBase<T> {

    short level;

    public BinTreeNodeAA(BinTreeInterface<T> owner, T objectNode, BinTreeNodeBase<T> left, BinTreeNodeBase<T> parent, BinTreeNodeBase<T> right) {
        super(owner, objectNode, left, parent, right);
        level = 1;
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            BinTreeInterface<T> owner,
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new BinTreeNodeAA(
                owner,
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
