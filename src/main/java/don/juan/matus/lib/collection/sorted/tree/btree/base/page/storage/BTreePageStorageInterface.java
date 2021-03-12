package don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage;

import don.juan.matus.lib.collection.KeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeInterface;

import java.io.Serializable;

public interface BTreePageStorageInterface<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends KeyValuePairInterface<K, V>,
        PK extends Comparable<PK> & Serializable,
        PI extends BTreePageIdInterface<PK>> {

    BTreeNodeInterface<K, V, P, PK, PI> getPage(PI id);

    void putPage(PI id, BTreeNodeInterface<K, V, P, PK, PI> page);

    void flushPage(PI id, BTreeNodeInterface<K, V, P, PK, PI> page);

}
