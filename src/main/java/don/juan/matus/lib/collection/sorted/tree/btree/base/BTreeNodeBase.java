package don.juan.matus.lib.collection.sorted.tree.btree.base;

import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdInterface;

import java.io.Serializable;
import java.lang.reflect.Array;

public class BTreeNodeBase<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends BTreeKeyValuePairInterface<K, V>,
        PK extends Comparable<PK> & Serializable,
        PI extends BTreePageIdInterface<PK>>
        implements BTreeNodeInterface<K, V, P, PK, PI> {

    protected K[] key;
    protected V[] value;
    protected PI[] child;
    protected transient BTreeInterface<K, V, PK, PI> btree;
    protected short size;

    @SuppressWarnings("unchecked")
    public BTreeNodeBase(BTreeInterface<K, V, PK, PI> ts) {
        btree = ts;
        size = 0;
        short length = ts.getPageSize();
        key = (K[]) Array.newInstance(btree.getKeyClass(), length);
        value = (V[]) Array.newInstance(btree.getValueClass(), length);
        child = (PI[]) Array.newInstance(btree.getPageIdClass(), length);
    }

    @Override
    public BTreeInterface<K, V, PK, PI> getBtree() {
        return btree;
    }

    @Override
    public short getSize() {
        return size;
    }

    @Override
    public K getKey(short s) {
        return key[s];
    }

    @Override
    public V getValue(short s) {
        return value[s];
    }

    @Override
    public PI getPageKey(short s) {
        return child[s];
    }

    @Override
    public K[] getKeys() {
        return key;
    }

    @Override
    public V[] getValues() {
        return value;
    }

    @Override
    public PI[] getPageKeys() {
        return child;
    }

}
