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
        SkipList.NavigableLaneNodeBaseInterface<T> laneNode = skipList.tower.get(0);
        while (laneNode.getRight() != null) {
            laneNode = (SkipList.NavigableLaneNodeBaseInterface<T>) laneNode.getRight();
        }
        while (laneNode instanceof SkipList.LaneNodeInterface) laneNode = laneNode.getDown();
        current = (SkipList.SkipListNodeInterface<T>) laneNode.getLeft();
    }


    @Override
    public boolean hasNext() {
        return current != null && current.getLeft() != null;
    }

    @Override
    public T next() {
        if (current != null) {
            T result = current.getElement();
            current = (SkipList.SkipListNodeInterface<T>) current.getLeft();
            return result;
        } else {
            return null;
        }
    }
}
