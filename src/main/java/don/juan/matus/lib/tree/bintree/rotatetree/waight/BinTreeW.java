package don.juan.matus.lib.tree.bintree.rotatetree.waight;

import don.juan.matus.lib.tree.bintree.*;

/**
 * Binary tree with balance by weight.
 */
public class BinTreeW<T extends Comparable<T>> extends BinTreeBase<T> {

    private double alpha = 4.0d;

    public BinTreeW() {
        super();
        root = new BinTreeNodeWeight<T>(null, null, null, null);
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeWeight<T> cursor = (BinTreeNodeWeight<T>) currentNode;
        cursor.incWeight();
        while (cursor != root) {
            cursor = rebalance(cursor);
            cursor = (BinTreeNodeWeight<T>) cursor.getParent();
            if (cursor != root) cursor.incWeight();
        };
        return cursor;
    }

    protected <U extends BinTreeNodeWeight<T>> U rebalance(U cursor) {
        long leftWeight = cursor.getLeft() == null ? 0 : ((BinTreeNodeWeight<T>) cursor.getLeft()).getWeight();
        long rightWeight = cursor.getRight() == null ? 0 : ((BinTreeNodeWeight<T>) cursor.getRight()).getWeight();
        if (rightWeight > leftWeight * alpha) {
            cursor = (U) rotateLeft(cursor);
        }
        if (leftWeight > rightWeight * alpha) {
            cursor = (U) rotateRight(cursor);
        }
        return cursor;
    }

    @Override
    protected void changeNode(BinTreeNodeInterface<T> theCurrentNode) {
        BinTreeNodeWeight<T> cursor = (BinTreeNodeWeight<T>) theCurrentNode;
        cursor.decWeight();
        cursor = (BinTreeNodeWeight<T>) theCurrentNode.getParent();
        if (cursor != root) {
            cursor.decWeight();
            while (cursor != root) {
                cursor = (BinTreeNodeWeight<T>) cursor.getParent();
                if (cursor != root) {
                    cursor.decWeight();
                    cursor = rebalance(cursor);
                } else {
                    break;
                }
            }
            ;
        }
    }

    protected BinTreeNodeWeight<T> rotateRight(BinTreeNodeWeight<T> currentNode) {
        BinTreeNodeWeight<T> node2 = (BinTreeNodeWeight<T>) currentNode.getLeft();
        super.rotateRight(currentNode);
        currentNode.setWeight((currentNode.getLeft() == null ? 0 : ((BinTreeNodeWeight<T>) currentNode.getLeft()).getWeight()) + (currentNode.getRight() == null ? 0 : ((BinTreeNodeWeight<T>) currentNode.getRight()).getWeight()) + 1);
        node2.setWeight((node2.getLeft() == null ? 0 : ((BinTreeNodeWeight<T>) node2.getLeft()).getWeight()) + (node2.getRight() == null ? 0 : ((BinTreeNodeWeight<T>) node2.getRight()).getWeight()) + 1);
        return node2;
    }

    protected BinTreeNodeWeight<T> rotateLeft(BinTreeNodeWeight<T> currentNode) {
        BinTreeNodeWeight<T> node2 = (BinTreeNodeWeight<T>) currentNode.getRight();
        super.rotateLeft(currentNode);
        currentNode.setWeight((currentNode.getLeft() == null ? 0 : ((BinTreeNodeWeight<T>) currentNode.getLeft()).getWeight()) + (currentNode.getRight() == null ? 0 : ((BinTreeNodeWeight<T>) currentNode.getRight()).getWeight()) + 1);
        node2.setWeight((node2.getLeft() == null ? 0 : ((BinTreeNodeWeight<T>) node2.getLeft()).getWeight()) + (node2.getRight() == null ? 0 : ((BinTreeNodeWeight<T>) node2.getRight()).getWeight()) + 1);
        return node2;
    }

    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        boolean result = super.checkTreeNode(thePassEvent, btiLeft, btiRight, currentNode, previousNode);
        if (result) {
            if (btiLeft != null && btiRight != null) {
                if (btiLeft.getSize() + btiRight.getSize() + 1 != ((BinTreeNodeWeight<T>)currentNode).getWeight()) {
                    thePassEvent.setErrorMessage("BinTreeW: Tree structure invalid, incorrect weight!");
                    result = false;
                }
            }
        }
        return result;
    }

}
