package don.juan.matus.lib.collection.sorted.tree.btree.base;

import java.io.Serializable;

public class BTreeNodeBase<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends BTreeKeyValuePairInterface<K, V>>
        implements BTreeNodeInterface<K, V> {

    @SuppressWarnings("unchecked")
    public BTreeNodeBase(BTreeBase<K, V> ts) {
    }

}
