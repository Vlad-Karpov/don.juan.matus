package don.juan.matus.lib.collection.sorted.tree.btree.base;

import don.juan.matus.lib.collection.sorted.tree.TreeBase;

import java.io.Serializable;

public class BTreeBase<
        K extends Comparable<K> & Serializable,
        V extends Serializable>
        extends TreeBase<BTreeKeyValuePairInterface<K, V>> {

    protected BTreeNodeInterface<K, V> root;
    protected Long maxLevel;
    protected Long level;
    protected int pageSize;

    public BTreeBase(int pageSize) {
        super();
        this.pageSize = pageSize;
        maxLevel = 0L;
        level = 0L;
        size = 0L;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public boolean add(BTreeKeyValuePairInterface<K, V> kvbTreeKeyValuePairInterface) {
        return super.add(kvbTreeKeyValuePairInterface);
    }

}
