package don.juan.matus.lib.collection.sorted.tree.btree.bptree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeKeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdInterface;

import java.io.Serializable;
import java.lang.reflect.Array;

public class BPlusTreeNode<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends BTreeKeyValuePairInterface<K, V>,
        PK extends Comparable<PK> & Serializable,
        PI extends BTreePageIdInterface<PK>>
        extends BPlusTreeNodeLeaf<K, V, P, PK, PI> implements BTreeNodeInterface<K, V, P, PK, PI> {

    protected PI[] child = null;

    public BPlusTreeNode(BTreeInterface<K, V, PK, PI> ts) {
        super(ts);
    }

    @Override
    @SuppressWarnings("unchecked")
    public PI[] getPageIds() {
        if (child == null) {
            child = (PI[]) Array.newInstance(btree.getPageIdClass(), btree.getRealPageSize());
        }
        return child;
    }

    @Override
    public boolean isLeaf() {
        return child == null;
    }

}
