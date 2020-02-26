package don.juan.matus.lib.collection.sorted.skiplist;

import don.juan.matus.lib.collection.sorted.SortedCollectionBase;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SkipList<T extends Comparable<T>> extends SortedCollectionBase<T> {

    List<LaneNode<T>> tower;
    Map<Integer, NavigableLaneNodeBaseInterface<T>> steckPrev;
    private Random rnd;

    public SkipList() {
        initList();
    }

    private void initList() {
        rnd = new Random();
        steckPrev = new HashMap<>();
        tower = new ArrayList<>();
        LaneNode<T> beginLaneNode = new LaneNode<>();
        tower.add(beginLaneNode);
        LaneNode<T> endLaneNode = new LaneNode<>();
        beginLaneNode.setRight(endLaneNode);
        endLaneNode.setLeft(beginLaneNode);
        ListNode<T> beginListNode = new ListNode<>();
        beginLaneNode.setListNode(beginListNode);
        beginLaneNode.setDown(beginListNode);
        beginListNode.setUp(beginLaneNode);
        ListNode<T> endListNode = new ListNode<>();
        endLaneNode.setListNode(endListNode);
        endLaneNode.setDown(endListNode);
        endListNode.setUp(endLaneNode);
        beginListNode.setRight(endListNode);
        endListNode.setLeft(beginListNode);
        size = 0L;
    }

    public void clear() {
        initList();
    }

    public boolean contains(Object o) {
        ListNode<T> node = seek((T) o, false);
        if (node != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(Object o) {
        ListNode<T> node = seek((T) o, false);
        if (node != null) {
            removeNode(node, true);
            return true;
        } else {
            return false;
        }
    }

    public NavigableLaneNodeBaseInterface<T> removeNode(NavigableLaneNodeBaseInterface<T> removeNode, boolean asc) {
        NavigableLaneNodeBaseInterface<T> result = null;
        if (asc) {
            result = (NavigableLaneNodeBaseInterface<T>) removeNode.getRight();
        } else {
            result = (NavigableLaneNodeBaseInterface<T>) removeNode.getLeft();
        }
        NavigableLaneNodeBaseInterface<T> up, down, left, right;
        do {
            up = (NavigableLaneNodeBaseInterface<T>) removeNode.getUp();
            down = removeNode.getDown();
            left = (NavigableLaneNodeBaseInterface<T>) removeNode.getLeft();
            right = (NavigableLaneNodeBaseInterface<T>) removeNode.getRight();
            if (up != null) up.setDown(down);
            if (down != null) down.setUp(up);
            if (left != null) left.setRight(right);
            if (right != null) right.setLeft(left);
            removeNode.setUp(null);
            removeNode.setDown(null);
            removeNode.setLeft(null);
            removeNode.setRight(null);
            removeNode = up;
        } while (up != null);
        return result;
    }

    public ListNode<T> seek(T theObject, boolean softSeek) {
        ListNode<T> result = null;
        int index = tower.size() - 1;
        NavigableNodeBaseInterface<T> prev = tower.get(index);
        NavigableNodeBaseInterface<T> node = prev.getRight();
        while (node != null) {
            if (node instanceof SkipList.LaneNodeInterface) {
                LaneNodeInterface<T> laneNode = (LaneNodeInterface<T>) node;
                LaneNodeInterface<T> prevNode = (LaneNodeInterface<T>) prev;
                if (laneNode.getListNode().getElement() != null) {
                    if (laneNode.getListNode().getElement().compareTo(theObject) > 0) {
                        node = prevNode.getDown();
                    }
                } else {
                    node = prevNode.getDown();
                }
            } else if (node instanceof SkipListNodeInterface) {
                SkipListNodeInterface<T> listNode = (SkipListNodeInterface<T>) node;
                if (listNode.getElement() != null) {
                    if (listNode.getElement().compareTo(theObject) == 0) {
                        result = (ListNode<T>) listNode;
                        break;
                    }
                    if (listNode.getElement().compareTo(theObject) > 0) {
                        if (softSeek) {
                            result = (ListNode<T>) listNode;
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            prev = node;
            node = prev.getRight();
        }
        return result;
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

    public void showMustGoOn() {
        NavigableLaneNodeBaseInterface<T> laneNode = tower.get(tower.size() - 1);
        while (laneNode.getDown() != null) laneNode = laneNode.getDown();
        SkipListNodeInterface<T> current = (SkipListNodeInterface<T>) laneNode.getRight();
        while (current != null) {
            if (current instanceof SkipListNodeInterface) {
                System.out.print(current.toString() + ", ");
            }
            current = (SkipListNodeInterface<T>) current.getRight();
        }
        System.out.println();
        tower.forEach(l -> {
            NavigableNodeBaseInterface<T> r = l;
            while (r != null) {
                if (r instanceof SkipListNodeInterface) {
                    SkipListNodeInterface<T> n = ((SkipListNodeInterface<T>) r);
                    System.out.print(n.toString() + ", ");
                }
                if (r instanceof SkipList.LaneNodeInterface) {
                    LaneNodeInterface<T> n = ((LaneNodeInterface<T>) r);
                    System.out.print(n.toString() + ", ");
                }
                r = r.getRight();
            }
            System.out.println();
        });
    }

    @Override
    public boolean add(T theObject) {
        steckPrev.clear();
        int index = tower.size() - 1;
        NavigableNodeBaseInterface<T> prev = tower.get(index);
        NavigableNodeBaseInterface<T> node = prev.getRight();
        while (node != null) {
            if (node instanceof SkipList.LaneNodeInterface) {
                LaneNodeInterface<T> laneNode = (LaneNodeInterface<T>) node;
                LaneNodeInterface<T> prevNode = (LaneNodeInterface<T>) prev;
                if (laneNode.getListNode().getElement() != null) {
                    if (laneNode.getListNode().getElement().compareTo(theObject) > 0) {
                        steckPrev.put(index, prevNode);
                        index--;
                        node = prevNode.getDown();
                    }
                } else {
                    steckPrev.put(index, prevNode);
                    index--;
                    node = prevNode.getDown();
                }
            } else if (node instanceof SkipListNodeInterface) {
                SkipListNodeInterface<T> listPrev = (SkipListNodeInterface<T>) prev;
                SkipListNodeInterface<T> listNode = (SkipListNodeInterface<T>) node;
                if (listNode.getElement() != null) {
                    if (listNode.getElement().compareTo(theObject) > 0) {
                        insertListNode(listPrev, listNode, theObject);
                        break;
                    }
                } else {
                    insertListNode(listPrev, listNode, theObject);
                    break;
                }
            }
            prev = node;
            node = prev.getRight();
        }
        return true;
    }

    private void insertListNode(SkipListNodeInterface<T> listPrev, SkipListNodeInterface<T> listNode, T theObject) {
        ListNode<T> newNode = new ListNode<T>();
        newNode.setElement(theObject);
        listPrev.setRight(newNode);
        newNode.setRight(listNode);
        newNode.setLeft(listPrev);
        listNode.setLeft(newNode);
        insertLane(newNode);
        size++;
    }

    private void insertLane(ListNode<T> newNode) {
        LaneNode<T> newLaneNode = null;
        int cs = tower.size();
        int index = 0;
        while (index <= cs && rnd.nextBoolean() && rnd.nextBoolean()) {
            if (index == cs) {
                insertNewLane(newNode, newLaneNode);
            } else {
                newLaneNode = insertLane(newNode, newLaneNode, index);
            }
            index++;
        }
    }

    private LaneNode<T> insertLane(ListNode<T> newNode, LaneNode<T> newLaneNode, int index) {
        LaneNodeInterface<T> prev = (LaneNodeInterface<T>) steckPrev.get(index);
        LaneNodeInterface<T> node = (LaneNodeInterface<T>) prev.getRight();
        return insertLaneNode(prev, node, newNode, newLaneNode);
    }

    private LaneNode<T> insertLaneNode(LaneNodeInterface<T> prev, LaneNodeInterface<T> node, SkipListNodeInterface<T> newNode, LaneNodeInterface<T> newLaneNodeLower) {
        LaneNode<T> newLaneNode = new LaneNode<T>();
        prev.setRight(newLaneNode);
        newLaneNode.setRight(node);
        newLaneNode.setListNode(newNode);
        newLaneNode.setLeft(prev);
        node.setLeft(newLaneNode);
        if (newLaneNodeLower != null) {
            newLaneNode.setDown(newLaneNodeLower);
            newLaneNodeLower.setUp(newLaneNode);
        } else {
            newLaneNode.setDown((NavigableLaneNodeBaseInterface<T>) newNode);
            newNode.setUp(newLaneNode);
        }
        return newLaneNode;
    }

    private void insertNewLane(ListNode<T> newNode, LaneNode<T> newLaneNode) {
        int index = tower.size() - 1;
        LaneNodeInterface<T> prev = tower.get(index);
        LaneNodeInterface<T> endPrev = (LaneNodeInterface<T>) prev.getRight();
        while (endPrev.getRight() != null) {
            endPrev = (LaneNodeInterface<T>) endPrev.getRight();
        }
        LaneNode<T> beginLaneNode = new LaneNode<>();
        tower.add(beginLaneNode);
        LaneNode<T> endLaneNode = new LaneNode<>();
        beginLaneNode.setRight(endLaneNode);
        endLaneNode.setLeft(beginLaneNode);
        beginLaneNode.setDown(prev);
        prev.setUp(beginLaneNode);
        beginLaneNode.setListNode(tower.get(0).getListNode());
        endLaneNode.setDown(endPrev);
        endPrev.setUp(endLaneNode);
        endLaneNode.setListNode(endPrev.getListNode());
        steckPrev.put(index + 1, beginLaneNode);
        insertLane(newNode, newLaneNode, index + 1);
    }

    interface NavigableNodeBaseInterface<T extends Comparable<T>> {

        NavigableNodeBaseInterface<T> getRight();

        void setRight(NavigableNodeBaseInterface<T> right);


        NavigableNodeBaseInterface<T> getUp();

        void setUp(NavigableNodeBaseInterface<T> up);

        NavigableNodeBaseInterface<T> getLeft();

        void setLeft(NavigableNodeBaseInterface<T> left);

        default int countRight() {
            int result = 0;
            NavigableNodeBaseInterface<T> r = this;
            while (r != null) {
                result++;
                r = r.getRight();
            }
            return result;
        }

        ;

    }

    interface NavigableLaneNodeBaseInterface<T extends Comparable<T>> extends NavigableNodeBaseInterface<T> {

        NavigableLaneNodeBaseInterface<T> getDown();

        void setDown(NavigableLaneNodeBaseInterface<T> down);

    }

    interface SkipListNodeInterface<T extends Comparable<T>> extends NavigableNodeBaseInterface<T> {

        T getElement();

        void setElement(T element);

    }

    interface LaneNodeInterface<T extends Comparable<T>> extends NavigableLaneNodeBaseInterface<T> {

        SkipListNodeInterface<T> getListNode();

        void setListNode(SkipListNodeInterface<T> listNode);

    }

    static class NavigableNodeBaseClass<T extends Comparable<T>> implements NavigableNodeBaseInterface<T> {

        NavigableNodeBaseInterface<T> right;
        NavigableNodeBaseInterface<T> left;
        NavigableNodeBaseInterface<T> up;

        @Override
        public NavigableNodeBaseInterface<T> getRight() {
            return right;
        }

        @Override
        public void setRight(NavigableNodeBaseInterface<T> right) {
            this.right = right;
        }

        @Override
        public NavigableNodeBaseInterface<T> getUp() {
            return up;
        }

        @Override
        public void setUp(NavigableNodeBaseInterface<T> up) {
            this.up = up;
        }

        @Override
        public NavigableNodeBaseInterface<T> getLeft() {
            return left;
        }

        @Override
        public void setLeft(NavigableNodeBaseInterface<T> left) {
            this.left = left;
        }

    }

    static class NavigableLaneNodeClass<T extends Comparable<T>> extends NavigableNodeBaseClass<T> implements NavigableNodeBaseInterface<T>, NavigableLaneNodeBaseInterface<T> {

        NavigableLaneNodeBaseInterface<T> down;

        @Override
        public NavigableLaneNodeBaseInterface<T> getDown() {
            return down;
        }

        @Override
        public void setDown(NavigableLaneNodeBaseInterface<T> down) {
            this.down = down;
        }

    }

    static class LaneNode<T extends Comparable<T>> extends NavigableLaneNodeClass<T> implements NavigableNodeBaseInterface<T>, NavigableLaneNodeBaseInterface<T>, LaneNodeInterface<T> {

        SkipListNodeInterface<T> listNode;

        @Override
        public SkipListNodeInterface<T> getListNode() {
            return listNode;
        }

        @Override
        public void setListNode(SkipListNodeInterface<T> listNode) {
            this.listNode = listNode;
        }

        @Override
        public String toString() {
            return "LaneNode{" + listNode.getElement() + "}";
        }
    }

    static class ListNode<T extends Comparable<T>> extends NavigableNodeBaseClass<T> implements NavigableNodeBaseInterface<T>, NavigableLaneNodeBaseInterface<T>, SkipListNodeInterface<T> {

        T element;

        @Override
        public T getElement() {
            return element;
        }

        @Override
        public void setElement(T element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return "ListNode{" + element + '}';
        }

        @Override
        public NavigableLaneNodeBaseInterface<T> getDown() {
            return null;
        }

        @Override
        public void setDown(NavigableLaneNodeBaseInterface<T> down) {
        }

    }

}
