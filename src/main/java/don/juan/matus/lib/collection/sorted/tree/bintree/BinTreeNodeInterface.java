package don.juan.matus.lib.collection.sorted.tree.bintree;

/**
 * Базовый интерфейс ноды бинарного дерева.
 */
public interface BinTreeNodeInterface<T extends Comparable<T>> extends Comparable<BinTreeNodeInterface<T>> {

    T getObjectNode();

    void setObjectNode(T objectNode);

    BinTreeNodeInterface<T> getParent();

    void setParent(BinTreeNodeInterface<T> parent);

    BinTreeNodeInterface<T> getLeft();

    void setLeft(BinTreeNodeInterface<T> left);

    BinTreeNodeInterface<T> getRight();

    void setRight(BinTreeNodeInterface<T> right);

    void onNode();

    void onGoLeft();

    void onGoRight();

    BinTreeNodeInterface<T> createNode(
            BinTreeInterface<T> owner,
            T theObject,
            BinTreeNodeInterface<T> theLeft,
            BinTreeNodeInterface<T> theParent,
            BinTreeNodeInterface<T> theRight);

    String desc();

    Object changeOuterObject(Object theOuterObject, BinTreeNodeInterface<T> currentNode, int increment);

    void clear();

}
