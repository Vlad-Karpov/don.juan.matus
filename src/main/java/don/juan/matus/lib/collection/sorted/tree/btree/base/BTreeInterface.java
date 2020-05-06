package don.juan.matus.lib.collection.sorted.tree.btree.base;

import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdInterface;

import java.io.Serializable;

/**
 * Btree base interface.
 * @param <K> key class
 * @param <V> value class
 * @param <PK> btree page key class
 * @param <PI> bree page id class
 */
public interface BTreeInterface<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        PK extends Comparable<PK> & Serializable,
        PI extends BTreePageIdInterface<PK>> {

    Class<K> getKeyClass();

    Class<V> getValueClass();

    short getPageSize();

    Class<PI> getPageIdClass();
}
