package don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.waight;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeBase;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;

/**
 * Элемент двоичного дерева с балансировкой по весам.
 */
public class BinTreeNodeWeight<T extends Comparable<? extends T>> extends BinTreeNodeBase<T> {

    private Long weight;

    public BinTreeNodeWeight(
            BinTreeInterface<T> owner,
            T objectNode,
            BinTreeNodeWeight<T> left,
            BinTreeNodeWeight<T> parent,
            BinTreeNodeWeight<T> right) {
        super(owner, objectNode, left, parent, right);
        this.objectNode = objectNode;
        this.weight = 0L;
    }

    public Long getWeight() {
        return weight;
    }

    public Long incWeight() {
        return weight++;
    }

    public Long decWeight() {
        return weight > 0 ? weight-- : weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    @Override
    public BinTreeNodeInterface<T> createNode(
            BinTreeInterface<T> owner,
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new BinTreeNodeWeight(
                owner, theObject,
                (BinTreeNodeWeight<T>)theLeft,
                (BinTreeNodeWeight<T>)theParent,
                (BinTreeNodeWeight<T>)theRight);
    }

    @Override
    public String toString() {
        return "BinTreeNodeWeight{" +
                "objectNode=" + objectNode +
                ", weight=" + weight +
                '}';
    }

    public String desc() {
        return objectNode + "," + weight;
    }


}
