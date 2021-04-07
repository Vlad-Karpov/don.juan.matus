package don.juan.matus.lib.collection;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakKeyValuePair<
        K extends Comparable<K> & Serializable,
        V extends Serializable>
        extends KeyValuePair<K, V> {

    protected WeakKeyValuePair() {
        super();
    }

    public WeakKeyValuePair(K key, V value) {
        this();
        this.key = new WeakReference<>(key);
        this.value = value;
    }

    public WeakKeyValuePair(K key, V value, ReferenceQueue<? super K> q) {
        this();
        this.key = new WeakReference<K>(key, q);
        this.value = value;
    }

    @Override
    public K getKey() {
        return ((WeakReference<K>) key).get();
    }

    @Override
    public int compareTo(@NotNull KeyValuePairInterface<K, V> o) {
        int result = 0;
        if (this.key != null) {
            K thisk = ((WeakReference<K>) this.key).get();
            if (thisk != null) {
                if (o.getKey() != null) {
                    K therek = ((WeakReference<K>) o.getKey()).get();
                    if (therek != null) {
                        result = thisk.compareTo(therek);
                    }
                }
            }
        }
        return result;
    }

}
