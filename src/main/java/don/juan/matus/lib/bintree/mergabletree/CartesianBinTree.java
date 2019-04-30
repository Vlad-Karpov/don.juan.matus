package don.juan.matus.lib.bintree.mergabletree;

import don.juan.matus.lib.bintree.BinTreeBase;


public class CartesianBinTree<T extends Comparable<T>> extends BinTreeBase<T> implements MergableCartesianBinTree<T> {

    public CartesianBinTree() {
        super();
        root = new BinTreeNodeWithPriorityInNode<>(null, null, null, null);
    }


    public void merge() {

    }

    public void split() {

    }

}
