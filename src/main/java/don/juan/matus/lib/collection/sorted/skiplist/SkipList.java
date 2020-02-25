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

    List<LaneNode<T>> tower = new ArrayList<>();
    Map<Integer, SkipListNodeBaseInterface<T>> steckPrev = new HashMap<>();
    private Random rnd = new Random();

    public SkipList() {
        LaneNode<T> beginLaneNode = new LaneNode<>();
        tower.add(beginLaneNode);
        LaneNode<T> endLaneNode = new LaneNode<>();
        beginLaneNode.setRight(endLaneNode);
        ListNode<T> beginListNode = new ListNode<>();
        beginLaneNode.setListNode(beginListNode);
        beginLaneNode.setDown(beginListNode);
        ListNode<T> endListNode = new ListNode<>();
        endLaneNode.setListNode(endListNode);
        endLaneNode.setDown(endListNode);
        beginListNode.setRight(endListNode);
        endListNode.setDown(beginListNode);
        size = 0L;
    }

    public ListNode<T> seek(T theObject) {
        ListNode<T> result = null;
        int index = tower.size() - 1;
        SkipListNodeBaseInterface<T> prev = tower.get(index);
        SkipListNodeBaseInterface<T> node = prev.getRight();
        while (node != null) {
            if (node instanceof LaneNodeInterface) {
                LaneNodeInterface<T> laneNode = (LaneNodeInterface<T>) node;
                if (laneNode.getListNode().getElement() != null) {
                    if (laneNode.getListNode().getElement().compareTo(theObject) > 0) {
                        node = prev.getDown();
                    }
                } else {
                    node = prev.getDown();
                }
            } else if (node instanceof SkipListNodeInterface) {
                SkipListNodeInterface<T> listPrev = (SkipListNodeInterface<T>) prev;
                SkipListNodeInterface<T> listNode = (SkipListNodeInterface<T>) node;
                if (listNode.getElement() != null) {
                    if (listNode.getElement().compareTo(theObject) == 0) {
                        result = (ListNode<T>) listNode;
                        break;
                    }
                    if (listNode.getElement().compareTo(theObject) > 0) {
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
        SkipListNodeBaseInterface<T> laneNode = tower.get(tower.size() - 1);
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
            SkipListNodeBaseInterface<T> r = l;
            while (r != null) {
                if (r instanceof SkipListNodeInterface) {
                    SkipListNodeInterface<T> n = ((SkipListNodeInterface<T>) r);
                    System.out.print(n.toString() + ", ");
                }
                if (r instanceof LaneNodeInterface) {
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
        SkipListNodeBaseInterface<T> prev = tower.get(index);
        SkipListNodeBaseInterface<T> node = prev.getRight();
        while (node != null) {
            if (node instanceof LaneNodeInterface) {
                LaneNodeInterface<T> laneNode = (LaneNodeInterface<T>) node;
                if (laneNode.getListNode().getElement() != null) {
                    if (laneNode.getListNode().getElement().compareTo(theObject) > 0) {
                        steckPrev.put(index, prev);
                        index--;
                        node = prev.getDown();
                    }
                } else {
                    steckPrev.put(index, prev);
                    index--;
                    node = prev.getDown();
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
        newNode.setDown(listPrev);
        listNode.setDown(newNode);
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

    private LaneNode<T> insertLaneNode(LaneNodeInterface<T> prev, LaneNodeInterface<T> node, ListNode<T> newNode, LaneNode<T> newLaneNodeLower) {
        LaneNode<T> newLaneNode = new LaneNode<T>();
        prev.setRight(newLaneNode);
        newLaneNode.setRight(node);
        newLaneNode.setListNode(newNode);
        if (newLaneNodeLower != null) {
            newLaneNode.setDown(newLaneNodeLower);
        } else {
            newLaneNode.setDown(newNode);
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
        beginLaneNode.setDown(prev);
        beginLaneNode.setListNode(tower.get(0).getListNode());
        endLaneNode.setDown(endPrev);
        endLaneNode.setListNode(endPrev.getListNode());
        steckPrev.put(index + 1, beginLaneNode);
        insertLane(newNode, newLaneNode, index + 1);
    }

    interface SkipListNodeBaseInterface<T extends Comparable<T>> {
        SkipListNodeBaseInterface<T> getDown();

        void setDown(SkipListNodeBaseInterface<T> down);

        SkipListNodeBaseInterface<T> getRight();

        void setRight(SkipListNodeBaseInterface<T> right);

        int countRight();
    }

    interface SkipListNodeInterface<T extends Comparable<T>> extends SkipListNodeBaseInterface<T> {
        T getElement();

        void setElement(T element);
    }

    interface LaneNodeInterface<T extends Comparable<T>> extends SkipListNodeBaseInterface<T> {
        SkipListNodeInterface<T> getListNode();

        void setListNode(SkipListNodeInterface<T> listNode);
    }

    static class NavigatableNode<T extends Comparable<T>> implements SkipListNodeBaseInterface<T> {

        SkipListNodeBaseInterface<T> right;
        SkipListNodeBaseInterface<T> down;

        @Override
        public SkipListNodeBaseInterface<T> getDown() {
            return down;
        }

        @Override
        public void setDown(SkipListNodeBaseInterface<T> down) {
            this.down = down;
        }

        @Override
        public SkipListNodeBaseInterface<T> getRight() {
            return right;
        }

        @Override
        public void setRight(SkipListNodeBaseInterface<T> right) {
            this.right = right;
        }

        @Override
        public int countRight() {
            int result = 0;
            SkipListNodeBaseInterface<T> r = this;
            while (r != null) {
                result++;
                r = r.getRight();
            }
            return result;
        }
    }

    static class LaneNode<T extends Comparable<T>> extends NavigatableNode<T> implements SkipListNodeBaseInterface<T>, LaneNodeInterface<T> {

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

    static class ListNode<T extends Comparable<T>> extends NavigatableNode<T> implements SkipListNodeBaseInterface<T>, SkipListNodeInterface<T> {

        SkipListNodeBaseInterface<T> left;

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
        public SkipListNodeBaseInterface<T> getDown() {
            return left;
        }

        @Override
        public void setDown(SkipListNodeBaseInterface<T> down) {
            left = down;
        }

        @Override
        public String toString() {
            return "ListNode{" + element + '}';
        }
    }

}
