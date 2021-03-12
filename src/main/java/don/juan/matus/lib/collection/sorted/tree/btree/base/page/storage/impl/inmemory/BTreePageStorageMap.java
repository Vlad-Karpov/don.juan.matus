package don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.impl.inmemory;

import don.juan.matus.lib.collection.KeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageStorageInterface;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BTreePageStorageMap<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends KeyValuePairInterface<K, V>,
        PK extends Comparable<PK> & Serializable,
        PI extends BTreePageIdInterface<PK>> implements BTreePageStorageInterface<K, V, P, PK, PI> {

    Map<PI, BTreeNodeInterface<K, V, P, PK, PI>> storage = new ConcurrentHashMap<>();

    @Override
    public BTreeNodeInterface<K, V, P, PK, PI> getPage(PI id) {
        return storage.get(id);
    }

    @Override
    public void putPage(PI id, BTreeNodeInterface<K, V, P, PK, PI> page) {
        storage.put(id, page);
    }

    @Override
    public void flushPage(PI id, BTreeNodeInterface<K, V, P, PK, PI> page) {
        //nothing
    }

}
