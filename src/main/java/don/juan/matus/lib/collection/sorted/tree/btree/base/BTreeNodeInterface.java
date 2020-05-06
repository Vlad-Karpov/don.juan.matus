package don.juan.matus.lib.collection.sorted.tree.btree.base;

import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdInterface;

import java.io.Serializable;

public interface BTreeNodeInterface<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends BTreeKeyValuePairInterface<K, V>,
        PK extends Comparable<PK> & Serializable,
        PI extends BTreePageIdInterface<PK>>
        extends Serializable {

    BTreeInterface<K, V, PK, PI> getBtree();

    short getSize();

    K getKey(short s);

    V getValue(short s);

    PI getPageKey(short s);

    K[] getKeys();

    V[] getValues();

    PI[] getPageKeys();

}
