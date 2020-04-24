package don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeInterface;

import java.io.Serializable;

public interface BTreePageStorageInterface<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends BTreePageIdInterface<?>> {

    BTreeNodeInterface<K, V> getPage(P id);

    void putPage(P id, BTreeNodeInterface<K, V> page);

}
