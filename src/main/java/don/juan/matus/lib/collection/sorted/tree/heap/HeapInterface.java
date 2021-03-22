package don.juan.matus.lib.collection.sorted.tree.heap;

public interface HeapInterface<T extends Comparable<T>> {

    int getNextId();

    default long getTtl() {
        return Long.MAX_VALUE;
    }

    default void setTtl(long ttl) {
    }

    /**
     * Expunges stale entries from the table.
     */
    void expungeStaleEntries();

}
