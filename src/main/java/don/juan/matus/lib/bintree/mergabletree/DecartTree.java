package don.juan.matus.lib.bintree.mergabletree;

import don.juan.matus.lib.bintree.BinTreeBase;


public class DecartTree<T extends Comparable<T>> extends BinTreeBase<T> implements MergableDecartBinTree<T> {

    public DecartTree() {
        super();
        root = new BinTreeNodeWithPriorityInNode<T>(null, null, null, null);
    }


    public void merge() {

    }

    public void split() {

    }

}
