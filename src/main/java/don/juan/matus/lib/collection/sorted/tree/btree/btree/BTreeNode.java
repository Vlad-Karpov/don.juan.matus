package don.juan.matus.lib.collection.sorted.tree.btree.btree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeInterface;
import don.juan.matus.lib.collection.KeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdInterface;

import java.io.Serializable;
import java.lang.reflect.Array;

public class BTreeNode<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends KeyValuePairInterface<K, V>,
        PK extends Comparable<PK> & Serializable,
        PI extends BTreePageIdInterface<PK>>
        extends BTreeNodeLeaf<K, V, P, PK, PI>
        implements BTreeNodeInterface<K, V, P, PK, PI> {

    protected PI[] child = null;

    public BTreeNode(BTreeInterface<K, V, PK, PI> ts) {
        super(ts);
    }

    @Override
    @SuppressWarnings("unchecked")
    public PI[] getPageIds(BTreeInterface<K, V, PK, PI> tree) {
        if (child == null) {
            child = (PI[]) Array.newInstance(tree.getPageIdClass(), tree.getRealPageSize());
        }
        return child;
    }

    @Override
    public boolean isLeaf() {
        return child == null;
    }

}
