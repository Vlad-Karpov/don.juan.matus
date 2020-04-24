package don.juan.matus.lib.collection.sorted.tree.btree.btree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeKeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageStorageInterface;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class BTree<K extends Comparable<K> & Serializable, V extends Serializable> extends BTreeBase<K, V> {

    AtomicInteger pageCounter = new AtomicInteger(0);

    public BTree(short pageSize) {
        super(pageSize);
        initBTree();
    }

    public BTree(short pageSize, BTreePageStorageInterface<K, V, BTreePageIdBase> pageStorage) {
        super(pageSize, pageStorage);
        initBTree();
    }

    private void initBTree() {
        root = new BTreePageIdBase(pageCounter.incrementAndGet());
    }

    @Override
    public boolean add(BTreeKeyValuePairInterface<K, V> kvbTreeKeyValuePairInterface) {
        return false;
    }

}
