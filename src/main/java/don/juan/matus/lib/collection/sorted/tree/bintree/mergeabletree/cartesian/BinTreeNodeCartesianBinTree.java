package don.juan.matus.lib.collection.sorted.tree.bintree.mergeabletree.cartesian;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeBase;

public class BinTreeNodeCartesianBinTree<T extends Comparable<T>> extends BinTreeNodeBase<T> implements PriorityCartesianBinTreeNode {

    public BinTreeNodeCartesianBinTree(
            BinTreeInterface<T> owner,
            T objectNode,
            BinTreeNodeBase<T> left,
            BinTreeNodeBase<T> parent,
            BinTreeNodeBase<T> right) {
        super(owner, objectNode, left, parent, right);
    }

    @Override
    public double getPriority() {
        return 0;
    }

}
