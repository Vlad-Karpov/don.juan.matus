package don.juan.matus.lib.collection.sorted.tree.btree.btree;

import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeKeyValuePairInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.BTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageIdBase;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.BTreePageStorageInterface;
import don.juan.matus.lib.collection.sorted.tree.btree.base.page.storage.CharacterArray;

import java.io.Serializable;

public class BTree<K extends Comparable<K> & Serializable,
        V extends Serializable>
        extends BTreeBase<K, V, CharacterArray, BTreePageIdBase> {

    public BTree(short pageSize, Class<K> keyClass, Class<V> valueClass)
            throws InstantiationException, IllegalAccessException {
        super(pageSize, keyClass, valueClass, BTreePageIdBase.class);
    }

    public BTree(
            short pageSize,
            Class<K> keyClass,
            Class<V> valueClass,
            BTreePageStorageInterface<K, V, BTreeKeyValuePairInterface<K, V>, CharacterArray, BTreePageIdBase> pageStorage)
            throws IllegalAccessException, InstantiationException {
        super(pageSize, keyClass, valueClass, BTreePageIdBase.class, pageStorage);
    }

    @Override
    public short getRealPageSize() {
        return (short) (pageSize + 1);
    }

    @Override
    public boolean add(BTreeKeyValuePairInterface<K, V> kvbTreeKeyValuePairInterface) {
        addInternal(kvbTreeKeyValuePairInterface, root);
        return true;
    }

    private void addInternal(
            BTreeKeyValuePairInterface<K, V> keyValuePair,
            BTreePageIdBase pageId) {
        BTreeNodeInterface<K, V, BTreeKeyValuePairInterface<K, V>, CharacterArray, BTreePageIdBase> page = getPageById(pageId);
        if (page.getSize() < getPageSize()) {
            short s = 0;
            while (s < page.getSize()) {
                K key = page.getKey(s);
                if (key.compareTo(keyValuePair.getKey()) > 0) {
                    BTreePageIdBase pid = page.getPageId(s);
                    if (pid != null) {
                        addInternal(keyValuePair, pid);
                    }
                    break;
                }
                s++;
            }
        } else {
            //todo:
        }
    }

    private BTreeNodeInterface<K, V, BTreeKeyValuePairInterface<K, V>, CharacterArray, BTreePageIdBase> getPageById(BTreePageIdBase pid) {
        BTreeNodeInterface<K, V, BTreeKeyValuePairInterface<K, V>, CharacterArray, BTreePageIdBase> page = pageStorage.getPage(pid);
        if (page == null) {
            page = new BTreeNodeLeaf<>(this);
            pageStorage.putPage(pid, page);
        }
        return page;
    }

}
