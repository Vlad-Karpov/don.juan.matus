package don.juan.matus.lib.collection.sorted.skiplist;

import java.util.Iterator;

public class DescendingSkipListIterator<T extends Comparable<T>> implements Iterator<T> {

    SkipList<T> skipList;
    SkipList.SkipListNodeInterface<T> current;

    public DescendingSkipListIterator(SkipList<T> ts) {
        skipList = ts;
        initIterator();
    }

    public DescendingSkipListIterator(SkipList<T> ts, T theObject) {
        skipList = ts;
        current = skipList.seek(theObject);
    }

    private void initIterator() {
        SkipList.SkipListNodeBaseInterface<T> laneNode = skipList.tower.get(0);
        while (laneNode.getRight() != null) {
            laneNode = laneNode.getRight();
        }
        while(laneNode instanceof SkipList.LaneNode) laneNode = laneNode.getDown();
        current = (SkipList.SkipListNodeInterface<T>) laneNode.getDown();
    }


    @Override
    public boolean hasNext() {
        return current != null && current.getDown() != null;
    }

    @Override
    public T next() {
        if (current != null) {
            T result = current.getElement();
            current = (SkipList.SkipListNodeInterface<T>) current.getDown();
            return result;
        } else {
            return null;
        }
    }
}
