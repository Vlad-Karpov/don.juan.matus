package don.juan.matus.lib.collection;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface KeyValuePairInterface<
        K extends Comparable<K> & Serializable,
        V extends Serializable>
        extends
        Comparable<KeyValuePairInterface<K, V>>
        , Serializable {

    K getKey();

    void setKey(K key);

    V getValue();

    void setValue(V value);

    @Override
    default int compareTo(@NotNull KeyValuePairInterface<K, V> o) {
        return getKey().compareTo(o.getKey());
    }

}
