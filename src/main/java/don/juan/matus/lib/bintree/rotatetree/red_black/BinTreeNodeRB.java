package don.juan.matus.lib.bintree.rotatetree.red_black;

import don.juan.matus.lib.bintree.BinTreeNodeBase;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;

public class BinTreeNodeRB<T extends Comparable<T>> extends BinTreeNodeBase<T> {

    public static byte RED = 1;
    public static byte BLACK = 0;

    // 0 - black; 1 - red
    byte color;

    public BinTreeNodeRB(T objectNode, BinTreeNodeBase<T> left, BinTreeNodeBase<T> parent, BinTreeNodeBase<T> right) {
        super(objectNode, left, parent, right);
        color = BLACK;
    }

    public byte getColor() {
        return color;
    }

    public void setColor(byte color) {
        this.color = color;
    }

    public boolean isRed() {
        return color == RED;
    }

    public boolean isBlack() {
        return color == BLACK;
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
        return new BinTreeNodeRB(
                theObject,
                (BinTreeNodeRB<T>) theLeft,
                (BinTreeNodeRB<T>) theParent,
                (BinTreeNodeRB<T>) theRight);
    }

    @Override
    public String toString() {
        return "BinTreeNodeRB{" +
                "objectNode=" + objectNode +
                ", color=" + (color == 0 ? "BLACK" : "RED") +
                '}';
    }

    public String desc() {
        return objectNode + ", " + (color == 0 ? "BLACK" : "RED");
    }

    @Override
    public Object changeOuterObject(Object theOuterObject, BinTreeNodeInterface<T> currentNode, int increment) {
        CountBlackHeight result = (CountBlackHeight) theOuterObject;
        if (result == null) {
            result = new CountBlackHeight();
        }
        if (isBlack()) {
            result.curentBlackHeight += increment;
        }
        if (result.curentBlackHeight > result.maxBlackHeight) {
            result.maxBlackHeight = result.curentBlackHeight;
        }
        return result;
    }

    public static class CountBlackHeight {
        public int curentBlackHeight = 0;
        public int maxBlackHeight = 0;

        @Override
        public String toString() {
            return "maxBlackHeight=" + maxBlackHeight;
        }
    }

}
