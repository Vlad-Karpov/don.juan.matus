package don.juan.matus.lib.bintree.mergeabletree.cartesian;

import don.juan.matus.lib.bintree.BinTreeNodeBase;

public class BinTreeNodeCartesianBinTree<T extends Comparable<T>> extends BinTreeNodeBase<T> implements PriorityCartesianBinTreeNode {

    public BinTreeNodeCartesianBinTree(T objectNode, BinTreeNodeBase<T> left, BinTreeNodeBase<T> parent, BinTreeNodeBase<T> right) {
        super(objectNode, left, parent, right);
    }

    @Override
    public double getPriority() {
        return 0;
    }

}
