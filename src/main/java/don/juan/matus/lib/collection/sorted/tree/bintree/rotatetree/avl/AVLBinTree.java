package don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.avl;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeBase;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeIterator;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;

import static don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface.*;

/**
 * Classic AVL binary tree.
 */
public class AVLBinTree<T extends Comparable<T>> extends BinTreeBase<T> {

    private byte threshold = 2;

    public AVLBinTree() {
        super();
        root = new BinTreeNodeBalanceFactor<T>(null, null, null, null);
    }

    public byte getThreshold() {
        return threshold;
    }

    public void setThreshold(byte threshold) {
        this.threshold = threshold;
    }

    @Override
    public void afterRotateRight(BinTreeNodeInterface<T> dadNode, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> pivotNode) {
        fixBalanceAfterRotateRight(dadNode, (BinTreeNodeBalanceFactor<T>) currentNode, pivotNode);
    }

    public static <T extends Comparable<T>> void fixBalanceAfterRotateRight(BinTreeNodeInterface<T> dadNode, BinTreeNodeBalanceFactor<T> currentNode, BinTreeNodeInterface<T> pivotNode) {
        int pb = ((BinTreeNodeBalanceFactor<T>) pivotNode).getBalanceFactor();
        int cb = currentNode.getBalanceFactor();
        int db = ((BinTreeNodeBalanceFactor<T>) dadNode).getBalanceFactor();
        int cbn = min(0, pb) - 1 + cb - pb;
        int pbn = min(pb, min(0, pb) - 1 + cb) - 1;
        int dbn;
        if (dadNode.getLeft() == pivotNode) {
            dbn = db - max(-cbn - 1, 0, pb) - min(cbn, 0, 1 - pb);
        } else {
            dbn = db + max(-cbn - 1, 0, pb) + min(cbn, 0, 1 - pb);
        }
        currentNode.setBalanceFactor((byte) cbn);
        ((BinTreeNodeBalanceFactor<T>) pivotNode).setBalanceFactor((byte) pbn);
        ((BinTreeNodeBalanceFactor<T>) dadNode).setBalanceFactor((byte) dbn);
    }

    @Override
    public void afterRotateLeft(BinTreeNodeInterface<T> dadNode, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> pivotNode) {
        fixBalanceAfterRotateLeft(dadNode, (BinTreeNodeBalanceFactor<T>) currentNode, pivotNode);
    }

    public static <T extends Comparable<T>> void fixBalanceAfterRotateLeft(BinTreeNodeInterface<T> dadNode, BinTreeNodeBalanceFactor<T> currentNode, BinTreeNodeInterface<T> pivotNode) {
        int pb = ((BinTreeNodeBalanceFactor<T>) pivotNode).getBalanceFactor();
        int cb = currentNode.getBalanceFactor();
        int db = ((BinTreeNodeBalanceFactor<T>) dadNode).getBalanceFactor();
        int cbn = max(pb, 0) + 1 + cb - pb;
        int pbn = max(pb, max(pb, 0) + 1 + cb) + 1;
        int dbn;
        if (dadNode.getLeft() == pivotNode) {
            dbn = db + max(cbn, 0, -pb - 1) + min(1 - cbn, 0, pb);
        } else {
            dbn = db + max(cbn - 1, 0, -pb) + min(-cbn, 0, 1 + pb);
        }
        currentNode.setBalanceFactor((byte) cbn);
        ((BinTreeNodeBalanceFactor<T>) pivotNode).setBalanceFactor((byte) pbn);
        ((BinTreeNodeBalanceFactor<T>) dadNode).setBalanceFactor((byte) dbn);
    }

    private BinTreeNodeInterface<T> balanceTree(final BinTreeNodeBalanceFactor<T> currentNode, int theIncHeight, int theSign) {
        BinTreeNodeBalanceFactor<T> parent;
        BinTreeNodeBalanceFactor<T> cursor = currentNode;
        int incHeight = theIncHeight;
        while (cursor != root) {
            parent = (BinTreeNodeBalanceFactor<T>) cursor.getParent();
            byte ob = parent.getBalanceFactor();
            parent.incBalanceFactor(leftOf(parent) == cursor ? (byte) incHeight : (byte) -incHeight);
            if (cursor.getBalanceFactor() == threshold) {
                if (((BinTreeNodeBalanceFactor<T>) cursor.getLeft()).getBalanceFactor() == -(threshold - 1)) {
                    byte cb = cursor.getBalanceFactor();
                    super.rotateLeft(cursor.getLeft());
                    byte cbn = cursor.getBalanceFactor();
                    int incH = abs(cbn) - abs(cb);
                    parent.incBalanceFactor((byte) (leftOf(parent) == cursor ? incH : -incH));
                }
                if (cursor.getBalanceFactor() == threshold) {
                    cursor = (BinTreeNodeBalanceFactor<T>) super.rotateRight(cursor);
                }
            }
            if (cursor.getBalanceFactor() == -threshold) {
                if (((BinTreeNodeBalanceFactor<T>) cursor.getRight()).getBalanceFactor() == (threshold - 1)) {
                    byte cb = cursor.getBalanceFactor();
                    super.rotateRight(cursor.getRight());
                    byte cbn = cursor.getBalanceFactor();
                    int incH = abs(cbn) - abs(cb);
                    parent.incBalanceFactor((byte) (leftOf(parent) == cursor ? incH : -incH));
                }
                if (cursor.getBalanceFactor() == -threshold) {
                    cursor = (BinTreeNodeBalanceFactor<T>) super.rotateLeft(cursor);
                }
            }
            byte nb = parent.getBalanceFactor();
            incHeight = calculateIncHeight(0, theSign, ob, nb);
            if (theSign == 1 && parent.getBalanceFactor() == 0 && incHeight == 0) {
                break;
            }
            cursor = parent;
        }
        return cursor;
    }

    public static <T extends Comparable<T>> int calculateIncHeight(int theIncHeight, int theSign, byte ob, byte nb) {
        int incHeight = theIncHeight;
        int delta = abs(nb) - abs(ob);
        if (theSign == 1) {
            //add
            if (delta > 0) {
                incHeight = delta;
            }
        } else {
            //delete
            if (delta < 0) {
                incHeight = delta;
            }
        }
        return incHeight;
    }

    @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeBalanceFactor<T> cursor = (BinTreeNodeBalanceFactor<T>) currentNode;
        BinTreeNodeBalanceFactor<T> parent = (BinTreeNodeBalanceFactor<T>) cursor.getParent();
        return balanceTree(cursor, 1, 1);
    }

    @Override
    protected void changeNode(BinTreeNodeInterface<T> theCurrentNode) {
        BinTreeNodeBalanceFactor<T> cursor = (BinTreeNodeBalanceFactor<T>) theCurrentNode;
        BinTreeNodeBalanceFactor<T> parent = (BinTreeNodeBalanceFactor<T>) cursor.getParent();
        balanceTree((BinTreeNodeBalanceFactor<T>) theCurrentNode, -1, -1);
    }

    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        boolean result = super.checkTreeNode(thePassEvent, btiLeft, btiRight, currentNode, previousNode);
        return checkTreeNodeStatic(result, thePassEvent, btiLeft, btiRight, currentNode, previousNode);
    }

    public static <T extends Comparable<T>> boolean checkTreeNodeStatic(boolean result, BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        if (result) {
            if ((btiLeft != null ? btiLeft.getMaxLevel() + 1 : 0) - (btiRight != null ? btiRight.getMaxLevel() + 1 : 0) != ((BinTreeNodeBalanceFactor<T>) currentNode).getBalanceFactor()) {
                thePassEvent.setErrorMessage("AVLBinTree: Tree structure invalid, incorrect balance factor!");
                result = false;
            }
        }
        return result;
    }


}
