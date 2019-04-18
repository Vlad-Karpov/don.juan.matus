package don.juan.matus.lib.bintree.rotatetree;

import don.juan.matus.lib.bintree.BinTreeBase;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;

/**
 * Balanced binary tree based on node rotation.
 */
public abstract class RotateBalancedBinTree<T extends Comparable<T>> extends BinTreeBase<T> {

    private Long rotateCount;

    public RotateBalancedBinTree() {
        super();
        rotateCount = 0L;
    }

    public Long getRotateCount() {
        return rotateCount;
    }

    public void setRotateCount(Long rotateCount) {
        this.rotateCount = rotateCount;
    }

    public void afterRotateRight(BinTreeNodeInterface<T> dadNode, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> pivotNode) {

    };
    public void afterRotateLeft(BinTreeNodeInterface<T> dadNode, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> pivotNode) {

    };

    protected BinTreeNodeInterface<T> rotateRight(BinTreeNodeInterface<T> currentNode) {
        rotateCount++;
        BinTreeNodeInterface<T> pivot = currentNode.getLeft();
        BinTreeNodeInterface<T> pivotRight = pivot.getRight();
        BinTreeNodeInterface<T> dad = currentNode.getParent();
        if (dad.getLeft() == currentNode) {
            dad.setLeft(pivot);
        } else {
            dad.setRight(pivot);
        }
        pivot.setParent(dad);
        pivot.setRight(currentNode);
        currentNode.setParent(pivot);
        if (pivotRight != null) pivotRight.setParent(currentNode);
        currentNode.setLeft(pivotRight);
        afterRotateRight(dad, currentNode, pivot);
        return pivot;
    }

    protected BinTreeNodeInterface<T> rotateLeft(BinTreeNodeInterface<T> currentNode) {
        rotateCount++;
        BinTreeNodeInterface<T> pivot = currentNode.getRight();
        BinTreeNodeInterface<T> pivotLeft = pivot.getLeft();
        BinTreeNodeInterface<T> dad = currentNode.getParent();
        if (dad.getLeft() == currentNode) {
            dad.setLeft(pivot);
        } else {
            dad.setRight(pivot);
        }
        pivot.setParent(dad);
        pivot.setLeft(currentNode);
        currentNode.setParent(pivot);
        if (pivotLeft != null) pivotLeft.setParent(currentNode);
        currentNode.setRight(pivotLeft);
        afterRotateLeft(dad, currentNode, pivot);
        return pivot;
    }

}
