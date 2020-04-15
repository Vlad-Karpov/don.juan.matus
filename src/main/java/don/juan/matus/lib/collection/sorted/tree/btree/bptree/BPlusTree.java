package don.juan.matus.lib.collection.sorted.tree.btree.bptree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeBase;

import java.io.Serializable;

public class BPlusTree<K extends Comparable<K> & Serializable, V extends Serializable> extends BTreeBase<K, V> {

        public BPlusTree(int pageSize) {
            super(pageSize);
            root = new BPlusTreeNode<>(this);
        }

}
