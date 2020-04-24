package don.juan.matus.lib.collection.sorted.tree.btree.bptree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeKeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageStorageInterface;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class BPlusTree<K extends Comparable<K> & Serializable, V extends Serializable> extends BTreeBase<K, V> {

    AtomicInteger pageCounter = new AtomicInteger(0);

    public BPlusTree(short pageSize) {
        super(pageSize);
        initBPlusTree();
    }

    public BPlusTree(short pageSize, BTreePageStorageInterface<K, V, BTreePageIdBase> pageStorage) {
        super(pageSize, pageStorage);
        initBPlusTree();
    }

    private void initBPlusTree() {
        root = new BTreePageIdBase(pageCounter.incrementAndGet());
    }

    @Override
    public boolean add(BTreeKeyValuePairInterface<K, V> kvbTreeKeyValuePairInterface) {
        return false;
    }

}
