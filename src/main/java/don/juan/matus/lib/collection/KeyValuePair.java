package don.juan.matus.lib.collection;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class KeyValuePair<
        K extends Comparable<K> & Serializable,
        V extends Serializable>
        implements KeyValuePairInterface<K, V> {

    protected Object key;
    protected Object value;

    public KeyValuePair() {
    }

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return (K) key;
    }

    @Override
    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public V getValue() {
        return (V) value;
    }

    @Override
    public void setValue(V value) {
        this.value = value;
    }

}
