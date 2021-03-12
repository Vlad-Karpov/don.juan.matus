package don.juan.matus.lib.collection.sorted.tree.btree.btree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeInterface;
import don.juan.matus.lib.collection.KeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdInterface;

import java.io.Serializable;

public class BTreeNodeLeaf<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends KeyValuePairInterface<K, V>,
        PK extends Comparable<PK> & Serializable,
        PI extends BTreePageIdInterface<PK>>
        extends BTreeNodeBase<K, V, P, PK, PI>
        implements BTreeNodeInterface<K, V, P, PK, PI> {

    public BTreeNodeLeaf(BTreeInterface<K, V, PK, PI> ts) {
        super(ts);
    }

}
