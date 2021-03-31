package don.juan.matus.lib.collection.sorted.tree.bintree.mergeabletree;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeBase;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;

public class MergeableBaseBinTree<T extends Comparable<? extends T>> extends BinTreeBase<T> {

    @Override
    public boolean add(T theObject) {
        split(parts, root.getLeft(), theObject);
        BinTreeNodeInterface<T> newNode = root.createNode(this, theObject, null, null, null);
        BinTreeNodeInterface<T> newTree = merge(merge(parts.leftTree, newNode), parts.rightTree);
        newTree.setParent(root);
        root.setLeft(newTree);
        size++;
        return true;
    }

}
