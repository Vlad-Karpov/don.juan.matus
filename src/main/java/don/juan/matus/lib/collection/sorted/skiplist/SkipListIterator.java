package don.juan.matus.lib.collection.sorted.skiplist;

import java.util.Iterator;

public class SkipListIterator<T extends Comparable<T>> implements Iterator<T> {

    SkipList<T> skipList;
    SkipList.SkipListNodeInterface<T> current;

    public SkipListIterator(SkipList<T> ts) {
        skipList = ts;
        initIterator();
    }

    public SkipListIterator(SkipList<T> ts, T theObject) {
        skipList = ts;
        current = skipList.seek(theObject);
    }

    private void initIterator() {
        SkipList.NavigableLaneNodeBaseInterface<T> laneNode = skipList.tower.get(0);
        while (laneNode.getDown() != null) laneNode = laneNode.getDown();
        current = (SkipList.SkipListNodeInterface<T>) laneNode.getRight();
    }


    @Override
    public boolean hasNext() {
        return current != null && current.getRight() != null;
    }

    @Override
    public T next() {
        if (current != null) {
            T result = current.getElement();
            current = (SkipList.SkipListNodeInterface<T>) current.getRight();
            return result;
        } else {
            return null;
        }
    }

    public void remove() {
        current = skipList.removeNode((SkipList.ListNode<T>) current);
    }

}
