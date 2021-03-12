package don.juan.matus.lib.collection.sorted.tree.btree.base;

import don.juan.matus.lib.collection.KeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.TreeBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageStorageInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.impl.inmemory.BTreePageStorageMap;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class BTreeBase<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        PK extends Comparable<PK> & Serializable,
        PI extends BTreePageIdInterface<PK>>
        extends TreeBase<KeyValuePairInterface<K, V>> implements BTreeInterface<K, V, PK, PI> {

    protected BTreePageStorageInterface<K, V, KeyValuePairInterface<K, V>, PK, PI> pageStorage;
    protected PI root;
    protected Long maxLevel;
    protected Long level;
    protected short pageSize;
    protected AtomicInteger pageCounter = new AtomicInteger(0);
    protected Class<K> keyClass;
    protected Class<V> valueClass;
    protected Class<PI> pageIdClass;

    public BTreeBase(
            short pageSize,
            Class<K> keyClass,
            Class<V> valueClass,
            Class<PI> pageIdClass) throws IllegalAccessException, InstantiationException {
        super();
        this.keyClass = keyClass;
        this.valueClass = valueClass;
        this.pageIdClass = pageIdClass;
        root = getNewPageId();
        pageStorage = new BTreePageStorageMap<>();
        this.pageSize = pageSize;
        maxLevel = 0L;
        level = 0L;
        size = 0L;
    }

    public BTreeBase(
            short pageSize,
            Class<K> keyClass,
            Class<V> valueClass,
            Class<PI> pageIdClass,
            BTreePageStorageInterface<K, V, KeyValuePairInterface<K, V>, PK, PI> pageStorage)
            throws InstantiationException, IllegalAccessException {
        this(pageSize, keyClass, valueClass, pageIdClass);
        this.pageStorage = pageStorage;
    }

    @Override
    public Class<K> getKeyClass() {
        return keyClass;
    }

    @Override
    public Class<V> getValueClass() {
        return valueClass;
    }

    @Override
    public short getPageSize() {
        return pageSize;
    }

    @Override
    public short getRealPageSize() {
        return pageSize;
    }

    @Override
    public Class<PI> getPageIdClass() {
        return pageIdClass;
    }

    @Override
    public boolean add(KeyValuePairInterface<K, V> kvbTreeKeyValuePairInterface) {
        return false;
    }

    @Override
    public PI getNewPageId() throws IllegalAccessException, InstantiationException {
        PI pageId = pageIdClass.newInstance();
        pageId.setIdAsInt(pageCounter.incrementAndGet());
        return pageId;
    }

}
