package don.juan.matus.lib.collection.sorted.tree.heap.avl;

import don.juan.matus.lib.collection.KeyValuePairInterface;
import don.juan.matus.lib.collection.WeakKeyValuePair;

import java.io.Serializable;
import java.lang.ref.ReferenceQueue;

//todo: this class
public class AvlHeapKeyValue<
        K extends Comparable<K> & Serializable,
        V extends Serializable> extends AvlHeap<KeyValuePairInterface<K, V>> {

    private ReferenceQueue<? super K> q = new ReferenceQueue<>();
    //private final AVLBinTree<HeapNode<KeyValuePairInterface<K, V>>> indexTree;

    public AvlHeapKeyValue(int maxHeapSize) {
        super(maxHeapSize);
    }

    public boolean add(K key, V value) {
        return super.add(new WeakKeyValuePair<>(key, value, q));
    }

    public void expungeAtherEntries() {

    }


}
