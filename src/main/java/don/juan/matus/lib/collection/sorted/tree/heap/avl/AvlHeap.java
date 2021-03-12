package don.juan.matus.lib.collection.sorted.tree.heap.avl;

import don.juan.matus.lib.collection.sorted.tree.bintree.*;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.AVLBinTree;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl.BinTreeNodeBalanceFactor;
import don.juan.matus.lib.collection.sorted.tree.heap.HeapInterface;
import don.juan.matus.lib.collection.sorted.tree.heap.HeapNode;

public class AvlHeap<T extends Comparable<T>> extends AVLBinTree<T> implements HeapInterface<T> {

    private int maxHeapSize;
    private final AVLBinTree<BinTreeNodeInterface<T>> indexTree;
    private int heapNodeId = 0;

    public AvlHeap(int maxHeapSize) {
        super();
        if (maxHeapSize <= 0) {
            throw new RuntimeException("AvlHeap: maxHeapSize must be greater then zero!");
        }
        this.maxHeapSize = maxHeapSize;
        root = new HeapNode<>(this, null, null, null, null);
        indexTree = new AVLBinTree<>();
    }

    public int getMaxHeapSize() {
        return maxHeapSize;
    }

    public void setMaxHeapSize(int maxHeapSize) {
        this.maxHeapSize = maxHeapSize;
    }

    @Override
    protected BinTreeNodeInterface<T> beforeAddLoop(final BinTreeNodeInterface<T> currentNode) {
        if (size == maxHeapSize) {
            BinTreeNodeInterface<BinTreeNodeInterface<T>> evictNodeIndex = indexTree.getFirst();
            BinTreeNodeInterface<T> evictNode = evictNodeIndex.getObjectNode();
            removeNode(false, evictNode, evictNode, evictNodeIndex);
        }
        return currentNode;
    }

    @Override
    public BinTreeNodeInterface<T> removeNode(
            Boolean theDescending,
            BinTreeNodeInterface<T> currentNode,
            BinTreeNodeInterface<T> nextNode, BinTreeNodeInterface<BinTreeNodeInterface<T>> evictNodeIndex) {
        if (evictNodeIndex != null) {
            indexTree.removeNode(false, evictNodeIndex, evictNodeIndex, null);
        } else {
            indexTree.remove(currentNode);
        }
        return super.removeNode(theDescending, currentNode, nextNode, evictNodeIndex);
    }

        @Override
    protected BinTreeNodeInterface<T> onReplaceNextToTarget(BinTreeNodeInterface<T> target, BinTreeNodeInterface<T> next) {

        BinTreeNodeInterface<T> np = BinTreeInterface.parentOf(next);
        BinTreeNodeInterface<T> nl = BinTreeInterface.leftOf(next);
        BinTreeNodeInterface<T> nr = BinTreeInterface.rightOf(next);
        byte nbf = ((BinTreeNodeBalanceFactor<T>) next).getBalanceFactor();

        BinTreeNodeInterface<T> tp = BinTreeInterface.parentOf(target);
        BinTreeNodeInterface<T> tl = BinTreeInterface.leftOf(target);
        BinTreeNodeInterface<T> tr = BinTreeInterface.rightOf(target);
        byte tbf = ((BinTreeNodeBalanceFactor<T>) target).getBalanceFactor();

        next.setParent(tp);
        if (tp.getLeft() == target) {
            tp.setLeft(next);
        } else {
            tp.setRight(next);
        }

        if (tl == next) {
            next.setLeft(target);
            target.setParent(next);
        } else {
            next.setLeft(tl);
            if (tl != null) {
                tl.setParent(next);
            }
        }

        if (tr == next) {
            next.setRight(target);
            target.setParent(next);
        } else {
            next.setRight(tr);
            if (tr != null) {
                tr.setParent(next);
            }
        }

        ((BinTreeNodeBalanceFactor<T>) next).setBalanceFactor(tbf);

        if (np != target) {
            target.setParent(np);
            if (np.getLeft() == next) {
                np.setLeft(target);
            } else {
                np.setRight(target);
            }
        }

        target.setLeft(nl);
        if (nl != null) {
            nl.setParent(target);
        }

        target.setRight(nr);
        if (nr != null) {
            nr.setParent(target);
        }

        ((BinTreeNodeBalanceFactor<T>) target).setBalanceFactor(nbf);

        return target;
    }


    @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeInterface<T> result = super.postAddLoop(currentNode);
        indexTree.add(currentNode);
        return result;
    }

    @Override
    public Long checkStructure(BinTreeCheckPassEvent<T> thePassEvent) {
        indexTree.checkStructure(new BinTreeCheckPassEvent<BinTreeNodeInterface<T>>() {

            @Override
            public void onPass(BinTreeIterator<BinTreeNodeInterface<T>> leftIterator, BinTreeIterator<BinTreeNodeInterface<T>> rightIterator, BinTreeNodeInterface<BinTreeNodeInterface<T>> currentNode, BinTreeNodeInterface<BinTreeNodeInterface<T>> previousNode) {
                thePassEvent.onPass(null, null, new BinTreeNodeBase(null, currentNode.getObjectNode().getObjectNode(), null, null, null), new BinTreeNodeBase(null, previousNode.getObjectNode().getObjectNode(), null, null, null));
            }

            @Override
            public void setErrorMessage(String errMsg) {
                thePassEvent.setErrorMessage(errMsg);
            }

            @Override
            public String getErrorMessage() {
                return thePassEvent.getErrorMessage();
            }
        });
        return super.checkStructure(thePassEvent);
    }

    @Override
    public int getNextId() {
        return ++heapNodeId;
    }

}
