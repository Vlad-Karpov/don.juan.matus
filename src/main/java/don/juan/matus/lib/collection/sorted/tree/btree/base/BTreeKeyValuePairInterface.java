package don.juan.matus.lib.collection.sorted.tree.btree.base;

import java.io.Serializable;

public interface BTreeKeyValuePairInterface<
        K extends Comparable<K> & Serializable,
        V extends Serializable>
        extends
        Comparable<BTreeKeyValuePairInterface<K, V>>
        , Serializable {

    K getKey();

    void setKey(K key);

    V getValue();

    void setValue(V value);

}
