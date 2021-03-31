package don.juan.matus.lib.collection.sorted.tree.bintree;

import static don.juan.matus.lib.util.util.compareHelper;

/**
 * Base node type binary tree.
 */
public class BinTreeNodeBase<T extends Comparable<? extends T>> implements BinTreeNodeInterface<T> {

    protected T objectNode;
    protected BinTreeNodeBase<T> parent;
    protected BinTreeNodeBase<T> left;
    protected BinTreeNodeBase<T> right;

    public BinTreeNodeBase(
            BinTreeInterface<T> owner,
            T objectNode,
            BinTreeNodeBase<T> left,
            BinTreeNodeBase<T> parent,
            BinTreeNodeBase<T> right) {
        this.objectNode = objectNode;
        this.left = left;
        this.parent = parent;
        this.right = right;
    }

    public T getObjectNode() {
        return objectNode;
    }

    public void setObjectNode(T objectNode) {
        this.objectNode = objectNode;
    }

    public BinTreeNodeInterface<T> getParent() {
        return parent;
    }

    public void setParent(BinTreeNodeInterface<T> parent) {
        this.parent = (BinTreeNodeBase<T>) parent;
    }

    public BinTreeNodeInterface<T> getLeft() {
        return left;
    }

    public void setLeft(BinTreeNodeInterface<T> left) {
        this.left = (BinTreeNodeBase<T>) left;
    }

    public BinTreeNodeInterface<T> getRight() {
        return right;
    }

    public void setRight(BinTreeNodeInterface<T> right) {
        this.right = (BinTreeNodeBase<T>) right;
    }

    public void onNode() {
        //
    }

    public void onGoLeft(){
        //
    }

    public void onGoRight(){
        //
    }

    public BinTreeNodeInterface<T> createNode(
            BinTreeInterface<T> owner,
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new BinTreeNodeBase(
                owner,
                theObject,
                (BinTreeNodeBase<T>)theLeft,
                (BinTreeNodeBase<T>)theParent,
                (BinTreeNodeBase<T>)theRight);
    }

    public String toString() {
        return "BinTreeNodeBase{" +
                "objectNode=" + objectNode +
                '}';
    }

    public String desc() {
        if (objectNode != null) {
            return objectNode.toString();
        } else {
            return "null";
        }
    }

    public int compareTo(BinTreeNodeInterface<T> o) {
        return compareHelper((Comparable<T>) objectNode, o.getObjectNode());
    }

    @Override
    public Object changeOuterObject(Object theOuterObject, BinTreeNodeInterface<T> currentNode, int increment) {
        return null;
    }

    @Override
    public void clear() {

    }

}
