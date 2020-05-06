package don.juan.matus.lib.collection.sorted.tree.btree.bptree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeKeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdInterface;

import java.io.Serializable;

public class BPlusTreeNode<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends BTreeKeyValuePairInterface<K, V>,
        PK extends Comparable<PK> & Serializable,
        PI extends BTreePageIdInterface<PK>>
        extends BTreeNodeBase<K, V, P, PK, PI> implements BTreeNodeInterface<K, V, P, PK, PI> {

    protected PI left;
    protected PI right;

    public BPlusTreeNode(BTreeInterface<K, V, PK, PI> ts) {
        super(ts);
        left = null;
        right = null;
    }

}
