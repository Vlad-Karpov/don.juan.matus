package don.juan.matus.lib.bintree.rotatetree.random;

import don.juan.matus.lib.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.bintree.BinTreeIterator;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.bintree.rotatetree.waight.BinTreeNodeW;
import don.juan.matus.lib.bintree.rotatetree.waight.BinTreeW;

import java.util.Random;

/**
 * Рендомезированное дерево.
 */
public class RndBinTree<T extends Comparable<T>> extends BinTreeW<T> {

    private Random rnd;

    public RndBinTree() {
        rnd = new Random();
        root = new RndBinTreeNode<T>(null, null, null, null);
    }

    protected <U extends BinTreeNodeW<T>> U rebalance(U cursor) {
        RndBinTreeNode<T> parent = (RndBinTreeNode<T>) cursor.getParent();
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
