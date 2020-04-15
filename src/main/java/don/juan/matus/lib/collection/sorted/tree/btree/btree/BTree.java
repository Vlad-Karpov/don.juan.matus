package don.juan.matus.lib.collection.sorted.tree.btree.btree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeBase;

import java.io.Serializable;

public class BTree<K extends Comparable<K> & Serializable, V extends Serializable> extends BTreeBase<K, V> {

    public BTree(int pageSize) {
        super(pageSize);
        root = new BTreeNode<>(this);
    }

}
