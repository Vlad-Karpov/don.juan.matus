package don.juan.matus.lib.collection.sorted.tree.heap.avl;

import don.juan.matus.lib.collection.KeyValuePair;
import don.juan.matus.lib.collection.KeyValuePairInterface;
import don.juan.matus.lib.collection.WeakKeyValuePair;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.GeneralCall;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class AvlHeapKeyValue<
        K extends Comparable<K> & Serializable,
        V extends Serializable> extends AvlHeap<KeyValuePairInterface<K, V>> {

    private Class<? extends KeyValuePairInterface<K, V>> keyValueClass;
    private ReferenceQueue<? super K> q = new ReferenceQueue<>();
    private final AVLBinTree<KeyValuePairInterface<Integer, BinTreeNodeInterface<? extends KeyValuePairInterface<K, V>>>> indexRef = new AVLBinTree<>();

    public AvlHeapKeyValue(int maxHeapSize) {
        super(maxHeapSize);
        this.keyValueClass = (Class<? extends KeyValuePairInterface<K, V>>) KeyValuePair.class;
    }

    public AvlHeapKeyValue(int maxHeapSize, Class<? extends KeyValuePairInterface<K, V>> keyValueClass) {
        super(maxHeapSize);
        this.keyValueClass = keyValueClass;
    }

    public boolean add(K key, V value) {
        KeyValuePairInterface<K, V> kvp;
        if (this.keyValueClass == WeakKeyValuePair.class) {
            kvp = new WeakKeyValuePair<>(key, value, q);
            return super.add(kvp);
        }
        kvp = new KeyValuePair<>(key, value);
        return super.add(kvp);
    }

    @Override
    protected BinTreeNodeInterface<KeyValuePairInterface<K, V>> postAddLoop(final BinTreeNodeInterface<KeyValuePairInterface<K, V>> currentNode) {
        super.postAddLoop(currentNode);
        if (this.keyValueClass != KeyValuePair.class) {
            KeyValuePairInterface<Integer, BinTreeNodeInterface<? extends KeyValuePairInterface<K, V>>> kvp;
            if (this.keyValueClass == WeakKeyValuePair.class) {
                kvp = new KeyValuePair<>(System.identityHashCode(currentNode.getObjectNode().getKey()), currentNode);
                indexRef.add(kvp);
            }
        }
        return currentNode;
    }

    @Override
    public BinTreeNodeInterface<KeyValuePairInterface<K, V>> removeNode(
            Boolean theDescending,
            BinTreeNodeInterface<KeyValuePairInterface<K, V>> currentNode,
            BinTreeNodeInterface<KeyValuePairInterface<K, V>> nextNode,
            BinTreeNodeInterface<BinTreeNodeInterface<KeyValuePairInterface<K, V>>> evictNodeIndex) {
        if (this.keyValueClass != KeyValuePair.class) {
            KeyValuePairInterface<Integer, BinTreeNodeInterface<? extends KeyValuePairInterface<K, V>>> kvp;
            if (this.keyValueClass == WeakKeyValuePair.class) {
                kvp = new KeyValuePair<>(System.identityHashCode(currentNode.getObjectNode().getKey()), currentNode);
                indexRef.remove(kvp);
            }
        }
        return super.removeNode(theDescending, currentNode, nextNode, evictNodeIndex);
    }

    public void expungeAtherEntries() {
        for (Reference<K> k = (Reference<K>) q.poll(); k != null; k = (Reference<K>) q.poll()) {
            KeyValuePairInterface<Integer, BinTreeNodeInterface<? extends KeyValuePairInterface<K, V>>> kvp;
            kvp = new KeyValuePair<>(System.identityHashCode(k), null);
            indexRef.seek(kvp, new GeneralCall<KeyValuePairInterface<Integer, BinTreeNodeInterface<? extends KeyValuePairInterface<K, V>>>>() {
                @Override
                public void generalCall(Boolean theResult, Integer theCmp, BinTreeNodeInterface<KeyValuePairInterface<Integer, BinTreeNodeInterface<? extends KeyValuePairInterface<K, V>>>> resultNode, Object generalObject) {
                    if (theResult) {
                        removeNode(
                                false,
                                (BinTreeNodeInterface<KeyValuePairInterface<K, V>>) resultNode.getObjectNode().getValue(),
                                (BinTreeNodeInterface<KeyValuePairInterface<K, V>>) resultNode.getObjectNode().getValue(),
                                null);
                    }
                }
            });
        }
    }

}
