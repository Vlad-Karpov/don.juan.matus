package don.juan.matus.lib.collection.sorted.tree.btree.base;

import don.juan.matus.lib.collection.KeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdInterface;

import java.io.Serializable;
import java.lang.reflect.Array;

public class BTreeNodeBase<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends KeyValuePairInterface<K, V>,
        PK extends Comparable<PK> & Serializable,
        PI extends BTreePageIdInterface<PK>>
        implements BTreeNodeInterface<K, V, P, PK, PI> {

    protected K[] key;
    protected V[] value;
    protected short size;

    @SuppressWarnings("unchecked")
    public BTreeNodeBase(BTreeInterface<K, V, PK, PI> ts) {
        size = 0;
        short length = ts.getRealPageSize();
        key = (K[]) Array.newInstance(ts.getKeyClass(), length);
        value = (V[]) Array.newInstance(ts.getValueClass(), length);
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
    public PI getPageId(BTreeInterface<K, V, PK, PI> tree, short s) {
        PI[] ps = getPageIds(tree);
        if (ps != null) {
            return ps[s];
        } else {
            return null;
        }
    }

    @Override
    public PI getPageIdLeft() {
        return null;
    }

    @Override
    public PI getPageIdRight() {
        return null;
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
    public PI[] getPageIds(BTreeInterface<K, V, PK, PI> tree) {
        return null;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

}
