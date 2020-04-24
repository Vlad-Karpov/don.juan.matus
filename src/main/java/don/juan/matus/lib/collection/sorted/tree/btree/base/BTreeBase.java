package don.juan.matus.lib.collection.sorted.tree.btree.base;

import don.juan.matus.lib.collection.sorted.tree.TreeBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageStorageInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.impl.inmemory.BTreePageStorageMap;

import java.io.Serializable;

public class BTreeBase<
        K extends Comparable<K> & Serializable,
        V extends Serializable>
        extends TreeBase<BTreeKeyValuePairInterface<K, V>> implements BTreeInterface {

    protected BTreePageStorageInterface<K, V, BTreePageIdBase> pageStorage;
    protected BTreePageIdBase root;
    protected Long maxLevel;
    protected Long level;
    protected short pageSize;

    public BTreeBase(short pageSize) {
        super();
        pageStorage = new BTreePageStorageMap<>();
        this.pageSize = pageSize;
        maxLevel = 0L;
        level = 0L;
        size = 0L;
    }

    public BTreeBase(short pageSize, BTreePageStorageInterface<K, V, BTreePageIdBase> pageStorage) {
        this(pageSize);
        this.pageStorage = pageStorage;
    }

    @Override
    public short getPageSize() {
        return pageSize;
    }

    @Override
    public boolean add(BTreeKeyValuePairInterface<K, V> kvbTreeKeyValuePairInterface) {
        return false;
    }

}
