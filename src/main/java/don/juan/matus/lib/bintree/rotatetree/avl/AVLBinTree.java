package don.juan.matus.lib.bintree.rotatetree.avl;

import don.juan.matus.lib.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.bintree.BinTreeIterator;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.bintree.rotatetree.RotateBalancedBinTree;

/**
 * Classic AVL binary tree.
 */
public class AVLBinTree<T extends Comparable<T>> extends RotateBalancedBinTree<T> {

    private byte threshold = 2;

    public AVLBinTree() {
        super();
        root = new BinTreeNodeBF<T>(null, null, null, null);
    }

    public byte getThreshold() {
        return threshold;
    }

    public void setThreshold(byte threshold) {
        this.threshold = threshold;
    }

    @Override
    public void afterRotateRight(BinTreeNodeInterface<T> dadNode, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> pivotNode) {
        int pb = ((BinTreeNodeBF<T>) pivotNode).getBalanceFactor();
        int cb = ((BinTreeNodeBF<T>) currentNode).getBalanceFactor();
        int db = ((BinTreeNodeBF<T>) dadNode).getBalanceFactor();
        int cbn = min(0, pb) - 1 + cb - pb;
        int pbn = min(pb, min(0, pb) - 1 + cb) - 1;
        int dbn;
        if (dadNode.getLeft() == pivotNode) {
            dbn = db - max(-cbn - 1, 0, pb) - min(cbn, 0, 1 - pb);
        } else {
            dbn = db + max(-cbn - 1, 0, pb) + min(cbn, 0, 1 - pb);
        }
        ((BinTreeNodeBF<T>) currentNode).setBalanceFactor((byte) cbn);
        ((BinTreeNodeBF<T>) pivotNode).setBalanceFactor((byte) pbn);
        ((BinTreeNodeBF<T>) dadNode).setBalanceFactor((byte) dbn);
    }

    @Override
    public void afterRotateLeft(BinTreeNodeInterface<T> dadNode, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> pivotNode) {
        int pb = ((BinTreeNodeBF<T>) pivotNode).getBalanceFactor();
        int cb = ((BinTreeNodeBF<T>) currentNode).getBalanceFactor();
        int db = ((BinTreeNodeBF<T>) dadNode).getBalanceFactor();
        int cbn = max(pb, 0) + 1 + cb - pb;
        int pbn = max(pb, max(pb, 0) + 1 + cb) + 1;
        int dbn;
        if (dadNode.getLeft() == pivotNode) {
            dbn = db + max(cbn, 0, -pb - 1) + min(1 - cbn, 0, pb);
        } else {
            dbn = db + max(cbn - 1, 0, -pb) + min(-cbn, 0, 1 + pb);
        }
        ((BinTreeNodeBF<T>) currentNode).setBalanceFactor((byte) cbn);
        ((BinTreeNodeBF<T>) pivotNode).setBalanceFactor((byte) pbn);
        ((BinTreeNodeBF<T>) dadNode).setBalanceFactor((byte) dbn);
    }

    protected BinTreeNodeInterface<T> rebalanceTree(final BinTreeNodeBF<T> currentNode, int theIncHeight, int theSign) {
        BinTreeNodeBF<T> parent;
        BinTreeNodeBF<T> cursor = currentNode;
        int incHeight = theIncHeight;
        while (cursor != root) {
            parent = (BinTreeNodeBF<T>) cursor.getParent();
            byte ob = parent.getBalanceFactor();
            parent.incBalanceFactor((byte) incHeight);
            if (cursor.getBalanceFactor() == threshold) {
                if (((BinTreeNodeBF<T>) cursor.getLeft()).getBalanceFactor() == -(threshold - 1)) {
                    byte cb = cursor.getBalanceFactor();
                    super.rotateLeft(cursor.getLeft());
                    byte cbn = cursor.getBalanceFactor();
                    parent.incBalanceFactor((byte) (parent.getRight() == cursor ? - cbn + cb : cbn - cb));
                }
                if (cursor.getBalanceFactor() == threshold) {
                    cursor = (BinTreeNodeBF<T>) super.rotateRight(cursor);
                }
            }
            if (cursor.getBalanceFactor() == -threshold) {
                if (((BinTreeNodeBF<T>) cursor.getRight()).getBalanceFactor() == (threshold - 1)) {
                    byte cb = cursor.getBalanceFactor();
                    super.rotateRight(cursor.getRight());
                    byte cbn = cursor.getBalanceFactor();
                    parent.incBalanceFactor((byte) (parent.getRight() == cursor ? cbn - cb : - cbn + cb));
                }
                if (cursor.getBalanceFactor() == -threshold) {
                    cursor = (BinTreeNodeBF<T>) super.rotateLeft(cursor);
                }
            }
            byte nb = parent.getBalanceFactor();
            incHeight = culculateIncHeight(parent, 0, theSign, ob, nb);
            if (theSign == 1 && parent.getBalanceFactor() == 0 && incHeight == 0) {
                break;
            }
            cursor = parent;
        }
        return cursor;
    }

    private int culculateIncHeight(final BinTreeNodeBF<T> currentNode, int theIncHeight, int theSign, byte ob, byte nb) {
        int incHeight = theIncHeight;
        if (currentNode.getParent() != null) {
            if (theSign == 1) {
                //add
                if (abs(ob) < abs(nb)) {
                    if (currentNode.getParent().getLeft() == currentNode) {
                        incHeight = abs(nb) - abs(ob);
                    } else {
                        incHeight = abs(ob) - abs(nb);
                    }
                } else {
                    if (ob > 0 && nb < 0) {
                        if (currentNode.getParent().getLeft() == currentNode) {
                            incHeight = abs(nb);
                        } else {
                            incHeight = -abs(nb);
                        }
                    }
                    if (ob < 0 && nb > 0) {
                        if (currentNode.getParent().getLeft() == currentNode) {
                            incHeight = abs(nb);
                        } else {
                            incHeight = -abs(nb);
                        }
                    }
                }
            } else {
                //delete
                if (ob > 0 && (nb >= 0 && nb < ob)) {
                    if (currentNode.getParent().getLeft() == currentNode) {
                        incHeight = nb - ob;
                    } else {
                        incHeight = ob - nb;
                    }
                }
                if (ob < 0 && (nb <= 0 && nb > ob)) {
                    if (currentNode.getParent().getLeft() == currentNode) {
                        incHeight = ob - nb;
                    } else {
                        incHeight = nb - ob;
                    }
                }
            }
        }
        return incHeight;
    }

    @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeBF<T> cursor = (BinTreeNodeBF<T>) currentNode;
        BinTreeNodeBF<T> parent = (BinTreeNodeBF<T>) cursor.getParent();
        return rebalanceTree(cursor, parent.getLeft() == cursor ? 1 : -1, 1);
    }

    @Override
    protected void changeNode(BinTreeNodeInterface<T> theCurrentNode) {
        BinTreeNodeBF<T> cursor = (BinTreeNodeBF<T>) theCurrentNode;
        BinTreeNodeBF<T> parent = (BinTreeNodeBF<T>) cursor.getParent();
        rebalanceTree((BinTreeNodeBF<T>)theCurrentNode, parent.getLeft() == cursor ? -1 : 1, -1);
    }


    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        boolean result = super.checkTreeNode(thePassEvent, btiLeft, btiRight, currentNode, previousNode);
        if (result) {
            if (btiLeft != null && btiRight != null) {
                if (btiLeft.getMaxLevel() - btiRight.getMaxLevel() != ((BinTreeNodeBF<T>)currentNode).getBalanceFactor()) {
                    thePassEvent.setErrorMessage("AVLBinTree: Tree structure invalid, incorrect balance factor!");
                    result = false;
                }
            }
        }
        return result;
    }

}
