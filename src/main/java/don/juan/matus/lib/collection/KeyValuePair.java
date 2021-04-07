package don.juan.matus.lib.collection;

import java.io.Serializable;

public class KeyValuePair<
        K extends Comparable<K> & Serializable,
        V extends Serializable>
        implements KeyValuePairInterface<K, V> {

    protected Object key;
    protected Object value;

    protected KeyValuePair() {
    }

    public KeyValuePair(K key, V value) {
        this();
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return (K) key;
    }

    @Override
    public V getValue() {
        return (V) value;
    }

    @Override
    public String toString() {
        return "KeyValuePair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

}
