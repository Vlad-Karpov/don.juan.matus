package don.juan.matus.lib.collection.sorted.skiplist;

import java.util.Iterator;

public class SkipListIterator<T extends Comparable<T>> implements Iterator<T> {

    public SkipListIterator(SkipList<T> ts) {
    }

    public SkipListIterator(SkipList<T> ts, T theObject) {
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
