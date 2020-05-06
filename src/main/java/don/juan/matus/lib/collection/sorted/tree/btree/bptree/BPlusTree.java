package don.juan.matus.lib.collection.sorted.tree.btree.bptree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeKeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageStorageInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.CharacterArray;

import java.io.Serializable;

public class BPlusTree
        <K extends Comparable<K> & Serializable,
                V extends Serializable>
        extends BTreeBase<K, V, CharacterArray, BTreePageIdBase> {

    public BPlusTree(short pageSize, Class<K> keyClass, Class<V> valueClass)
            throws InstantiationException, IllegalAccessException {
        super(pageSize, keyClass, valueClass, BTreePageIdBase.class);
    }

    public BPlusTree(
            short pageSize,
            Class<K> keyClass,
            Class<V> valueClass,
            BTreePageStorageInterface<K, V, BTreeKeyValuePairInterface<K, V>, CharacterArray, BTreePageIdBase> pageStorage)
            throws IllegalAccessException, InstantiationException {
        super(pageSize, keyClass, valueClass, BTreePageIdBase.class, pageStorage);
    }

    @Override
    public boolean add(BTreeKeyValuePairInterface<K, V> kvbTreeKeyValuePairInterface) {
        BPlusTreeNode<K, V, BTreeKeyValuePairInterface<K, V>, CharacterArray, BTreePageIdBase> page = (BPlusTreeNode<K, V, BTreeKeyValuePairInterface<K, V>, CharacterArray, BTreePageIdBase>) pageStorage.getPage(root);
        if (page == null) {
            page = new BPlusTreeNode<>(this);
            pageStorage.putPage(root, page);
        }
        if (page.getSize() < getPageSize()) {
            short s = 0;
            while (s < page.getSize()) {
                K key = page.getKey(s);
                s++;
            }
        } else {
            //todo:
        }
        return false;
    }

}
