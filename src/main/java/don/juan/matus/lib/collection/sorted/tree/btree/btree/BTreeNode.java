package don.juan.matus.lib.collection.sorted.tree.btree.btree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeKeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdBase;

import java.io.Serializable;

public class BTreeNode<
        K extends Comparable<K> & Serializable,
        V extends Serializable,
        P extends BTreeKeyValuePairInterface<K, V>>
        extends BTreeNodeBase<K, V, P>
        implements BTreeNodeInterface<K, V> {

    protected K[] key;
    protected V[] value;
    protected BTreePageIdBase[] child;
    protected short size;
    protected transient short length;

    @SuppressWarnings("unchecked")
    public BTreeNode(BTreeInterface ts) {
        super(ts);
        size = 0;
        length = ts.getPageSize();
        key = (K[]) new Object[length];
        value = (V[]) new Object[length];
        child = new BTreePageIdBase[length];
    }

}
