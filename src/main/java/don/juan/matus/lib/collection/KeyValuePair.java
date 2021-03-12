package don.juan.matus.lib.collection;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class KeyValuePair<
        K extends Comparable<K> & Serializable,
        V extends Serializable>
        implements KeyValuePairInterface<K, V>
        , Comparable<KeyValuePairInterface<K, V>>
        , Serializable {

    private K key;
    private V value;

    public KeyValuePair(K key, V value) {
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
    public int compareTo(@NotNull KeyValuePairInterface<K, V> o) {
        return key.compareTo(o.getKey());
    }
}
