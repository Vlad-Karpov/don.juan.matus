package don.juan.matus.lib.bintree.rotatetree.waight;

import don.juan.matus.lib.bintree.BinTreeNodeBase;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;

/**
 * Элемент двоичного дерева с балансировкой по весам.
 */
public class BinTreeNodeW<T extends Comparable<T>> extends BinTreeNodeBase<T> {

    private Long weight;

    public BinTreeNodeW(T objectNode, BinTreeNodeW<T> left, BinTreeNodeW<T> parent, BinTreeNodeW<T> right) {
        super(objectNode, left, parent, right);
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
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight) {
        return new BinTreeNodeW(
                theObject,
                (BinTreeNodeW<T>)theLeft,
                (BinTreeNodeW<T>)theParent,
                (BinTreeNodeW<T>)theRight);
    }

    @Override
    public String toString() {
        return "BinTreeNodeW{" +
                "objectNode=" + objectNode +
                ", weight=" + weight +
                '}';
    }

    public String desc() {
        return objectNode + "," + weight;
    }


}
