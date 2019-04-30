package don.juan.matus.lib.bintree.mergabletree;

import don.juan.matus.lib.bintree.BinTreeBase;


public class CartesianBinTree<T extends Comparable<T>> extends BinTreeBase<T> implements MergableCartesianBinTree<T> {

    public CartesianBinTree() {
        super();
        root = new BinTreeNodeWithPriorityInNode<>(null, null, null, null);
    }


    @Override
    public BinTreeNodeCartesianBinTree<T> merge(MergeParts theParts) {
        return null;
    }

    @Override
    public MergeParts split(BinTreeNodeCartesianBinTree<T> theRoot, T theKey) {
        return null;
    }

}
