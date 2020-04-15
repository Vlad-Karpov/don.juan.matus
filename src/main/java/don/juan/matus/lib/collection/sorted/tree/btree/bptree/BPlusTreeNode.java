package don.juan.matus.lib.collection.sorted.tree.btree.bptree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeKeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeInterface;

import java.io.Serializable;

public class BPlusTreeNode<K extends Comparable<K> & Serializable, V extends Serializable, P extends BTreeKeyValuePairInterface<K, V>> extends BTreeNodeBase<K, V, P> implements BTreeNodeInterface<K, V> {

    protected K[] key;
    protected V[] value;
    protected BTreeNodeBase<K, V, P>[] child;
    protected BTreeNodeBase<K, V, P> left;
    protected BTreeNodeBase<K, V, P> right;
    protected short size;

    @SuppressWarnings("unchecked")
    public BPlusTreeNode(BTreeBase<K, V> ts) {
        super(ts);
        size = 0;
        key = (K[]) new Object[ts.getPageSize()];
        value = (V[]) new Object[ts.getPageSize()];
        child = (BTreeNodeBase<K, V, P>[]) new Object[ts.getPageSize()];
        left = null;
        right = null;
    }

}
