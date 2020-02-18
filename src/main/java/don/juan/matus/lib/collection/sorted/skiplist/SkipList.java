package don.juan.matus.lib.collection.sorted.skiplist;

import don.juan.matus.lib.collection.sorted.SortedCollectionBase;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class SkipList<T extends Comparable<T>> extends SortedCollectionBase<T> {

    ListNodeTerminator<T> beginSkipList;
    ListNodeTerminator<T> endSkipList;
    LaneTerminator<T> beginLane;
    LaneTerminator<T> endLane;
    private int size;

    public SkipList() {
        beginSkipList = new ListNodeTerminator<T>();
        endSkipList = new ListNodeTerminator<T>();
        beginSkipList.setRight(endSkipList);
        beginLane = new LaneTerminator<>();
        beginLane.setDown(beginSkipList);
        endLane = new LaneTerminator<>();
        beginLane.setRight(endLane);
        endLane.setDown(endSkipList);
        size = 0;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new SkipListIterator<>(this);
    }

    @NotNull
    @Override
    public Iterator<T> descendingIterator() {
        return new DescendingSkipListIterator<>(this);
    }

    @Override
    public int size() {
        return size;
    }

    @NotNull
    @Override
    public Iterator<T> iterator(T theObject) {
        return new SkipListIterator<>(this, theObject);
    }

    @NotNull
    @Override
    public Iterator<T> descendingIterator(T theObject) {
        return new DescendingSkipListIterator<>(this, theObject);
    }

    @Override
    public boolean add(T theObject) {
        return true;
    }


    interface SkipListNodeBaseInterface<T extends Comparable<T>> {
    }

    interface SkipListNodeRightInterface<T extends Comparable<T>> extends SkipListNodeBaseInterface<T> {
        SkipListNodeBaseInterface<T> getRight();

        void setRight(SkipListNodeBaseInterface<T> right);
    }

    interface SkipListNodeInterface<T extends Comparable<T>> extends SkipListNodeBaseInterface<T> {
        T getElement();

        void setElement(T element);
    }

    interface LaneNodeInterfaceDown<T extends Comparable<T>> extends SkipListNodeBaseInterface<T> {
        SkipListNodeBaseInterface<T> getDown();

        void setDown(SkipListNodeBaseInterface<T> down);
    }

    interface LaneNodeInterface<T extends Comparable<T>> extends SkipListNodeBaseInterface<T> {
        SkipListNodeInterface<T> getSkipListNode();

        void setSkipListNode(SkipListNodeInterface<T> skipListNode);
    }

    static class ListNodeTerminator<T extends Comparable<T>> implements SkipListNodeRightInterface<T> {

        SkipListNodeBaseInterface<T> right;

        @Override
        public SkipListNodeBaseInterface<T> getRight() {
            return right;
        }

        @Override
        public void setRight(SkipListNodeBaseInterface<T> right) {
            this.right = right;
        }

    }

    static class ListNode<T extends Comparable<T>> extends ListNodeTerminator<T> implements SkipListNodeInterface<T> {

        T element;

        @Override
        public T getElement() {
            return element;
        }

        @Override
        public void setElement(T element) {
            this.element = element;
        }

    }

    static class LaneTerminator<T extends Comparable<T>> extends ListNodeTerminator<T> implements LaneNodeInterfaceDown<T> {

        SkipListNodeBaseInterface<T> down;

        @Override
        public SkipListNodeBaseInterface<T> getDown() {
            return down;
        }

        @Override
        public void setDown(SkipListNodeBaseInterface<T> down) {
            this.down = down;
        }

    }

    static class Lane<T extends Comparable<T>> extends LaneTerminator<T> implements LaneNodeInterface<T> {

        SkipListNodeInterface<T> skipListNode;

        @Override
        public SkipListNodeInterface<T> getSkipListNode() {
            return skipListNode;
        }

        @Override
        public void setSkipListNode(SkipListNodeInterface<T> skipListNode) {
            this.skipListNode = skipListNode;
        }
    }

}
