package don.juan.matus.lib.collection.sorted.skiplist;

import java.util.Iterator;

import static don.juan.matus.lib.util.util.compareHelper;

public class DescendingSkipListIterator<T extends Comparable<? extends T>> implements Iterator<T> {

    SkipList<T> skipList;
    SkipList.NavigableLaneNodeBaseInterface<T> current;

    public DescendingSkipListIterator(SkipList<T> ts) {
        skipList = ts;
        initIterator();
    }

    public DescendingSkipListIterator(SkipList<T> ts, T theObject) {
        skipList = ts;
        current = skipList.seek(theObject, true);
        if (current != null) {
            T value = ((SkipList.SkipListNodeInterface<T>) current).getElement();
            if (compareHelper((Comparable<T>) value, theObject) > 0) {
                next();
            }
        }
    }

    private void initIterator() {
        SkipList.NavigableLaneNodeBaseInterface<T> laneNode = skipList.tower.get(0);
        while (laneNode.getRight() != null) {
            laneNode = (SkipList.NavigableLaneNodeBaseInterface<T>) laneNode.getRight();
        }
        while (laneNode instanceof SkipList.LaneNodeInterface) laneNode = laneNode.getDown();
        current = (SkipList.NavigableLaneNodeBaseInterface<T>) laneNode.getLeft();
    }


    @Override
    public boolean hasNext() {
        return current != null && current.getLeft() != null;
    }

    @Override
    public T next() {
        if (current != null) {
            T result = ((SkipList.SkipListNodeInterface<T>) current).getElement();
            current = (SkipList.NavigableLaneNodeBaseInterface<T>) current.getLeft();
            return result;
        } else {
            return null;
        }
    }

    @Override
    public void remove() {
        current = skipList.removeNode(current, false);
    }

}
