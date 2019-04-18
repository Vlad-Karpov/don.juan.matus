package don.juan.matus.lib.bintree.rotatetree.random;

import don.juan.matus.lib.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.bintree.BinTreeIterator;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;
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

    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        RndBinTreeNode<T> cursor = (RndBinTreeNode<T>) currentNode;
        do {
            RndBinTreeNode<T> parent = (RndBinTreeNode<T>) cursor.getParent();
            if (parent != null && parent != root) {
                if ((rnd.nextLong() % (parent.getWeight()) == 0)) {
                    if (cursor == parent.getLeft()) {
                        cursor = (RndBinTreeNode<T>) rotateRight(parent);
                    } else {
                        cursor = (RndBinTreeNode<T>) rotateLeft(parent);
                    }
                } else {
                    cursor = parent;
                }
            } else
                break;
        } while (true);
        return cursor;
    }

    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        return super.checkTreeNode(thePassEvent, btiLeft, btiRight, currentNode, previousNode);
    }

}
