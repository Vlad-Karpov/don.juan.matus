package don.juan.matus.lib.collection.sorted.tree.btree.base;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class BTreeKeyValuePair<
        K extends Comparable<K> & Serializable,
        V extends Serializable>
        implements BTreeKeyValuePairInterface<K, V>
        , Comparable<BTreeKeyValuePairInterface<K, V>>
        , Serializable {

    private K key;
    private V value;

    public BTreeKeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public int compareTo(@NotNull BTreeKeyValuePairInterface<K, V> o) {
        return key.compareTo(o.getKey());
    }
}
