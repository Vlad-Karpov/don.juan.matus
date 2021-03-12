package don.juan.matus.lib.collection;

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

}
