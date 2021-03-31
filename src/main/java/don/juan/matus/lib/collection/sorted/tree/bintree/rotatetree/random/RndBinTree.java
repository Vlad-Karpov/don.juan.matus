package don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.random;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeIterator;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.waight.BinTreeNodeWeight;
import don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.waight.BinTreeW;

import java.util.Random;

/**
 * Рендомезированное дерево.
 */
public class RndBinTree<T extends Comparable<? extends T>> extends BinTreeW<T> {

    private Random rnd;

    public RndBinTree() {
        rnd = new Random();
        root = new BinTreeNodeRnd<T>(this, null, null, null, null);
    }

    protected <U extends BinTreeNodeWeight<T>> U rebalance(U cursor) {
        BinTreeNodeRnd<T> parent = (BinTreeNodeRnd<T>) cursor.getParent();
        if (parent != root) {
            if ((rnd.nextLong() % (parent.getWeight()) == 0)) {
                if (cursor == parent.getLeft()) {
                    cursor = (U) rotateRight(parent);
                } else {
                    cursor = (U) rotateLeft(parent);
                }
            }
        }
        return cursor;
    }

    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        return super.checkTreeNode(thePassEvent, btiLeft, btiRight, currentNode, previousNode);
    }

}
