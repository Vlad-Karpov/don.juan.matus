package don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.impl.inmemory;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageStorageInterface;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BTreePageStorageMap<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends BTreePageIdInterface<?>> implements BTreePageStorageInterface<K, V, P> {

    Map<P, BTreeNodeInterface<K, V>> storage = new ConcurrentHashMap<>();

    @Override
    public BTreeNodeInterface<K, V> getPage(P id) {
        return storage.get(id);
    }

    @Override
    public void putPage(P id, BTreeNodeInterface<K, V> page) {
        storage.put(id, page);
    }

}
