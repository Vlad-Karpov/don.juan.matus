package don.juan.matus.lib.collection.sorted.skiplist;

import java.util.Iterator;

public class DescendingSkipListIterator<T extends Comparable<T>> implements Iterator<T> {

    public DescendingSkipListIterator(SkipList<T> ts) {
    }

    public DescendingSkipListIterator(SkipList<T> ts, T theObject) {
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}
