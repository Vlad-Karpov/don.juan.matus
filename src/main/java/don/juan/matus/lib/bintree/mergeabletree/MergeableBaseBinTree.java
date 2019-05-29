package don.juan.matus.lib.bintree.mergeabletree;

import don.juan.matus.lib.bintree.BinTreeBase;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;

public class MergeableBaseBinTree<T extends Comparable<T>> extends BinTreeBase<T> {

    @Override
    public boolean add(T theObject) {
        split(parts, root.getLeft(), theObject);
        BinTreeNodeInterface<T> newNode = root.createNode(theObject, null, null, null);
        BinTreeNodeInterface<T> newTree = merge(merge(parts.leftTree, newNode), parts.rightTree);
        newTree.setParent(root);
        root.setLeft(newTree);
        size++;
        return true;
    }

}
