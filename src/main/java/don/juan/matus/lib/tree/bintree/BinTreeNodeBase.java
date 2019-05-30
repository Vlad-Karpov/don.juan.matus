package don.juan.matus.lib.tree.bintree;

/**
 * Base node type binary tree.
 */
public class BinTreeNodeBase<T extends Comparable<T>> implements BinTreeNodeInterface<T> {

    protected T objectNode;
    protected BinTreeNodeBase<T> parent;
    protected BinTreeNodeBase<T> left;
    protected BinTreeNodeBase<T> right;

    public BinTreeNodeBase(
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
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new BinTreeNodeBase(
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
        return objectNode.toString();
    }

    public int compareTo(BinTreeNodeInterface<T> o) {
        return objectNode.compareTo(o.getObjectNode());
    }

    @Override
    public Object changeOuterObject(Object theOuterObject, BinTreeNodeInterface<T> currentNode, int increment) {
        return null;
    }

}
