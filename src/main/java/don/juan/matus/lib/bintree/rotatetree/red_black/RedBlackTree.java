package don.juan.matus.lib.bintree.rotatetree.red_black;

import don.juan.matus.lib.bintree.*;
import don.juan.matus.lib.bintree.rotatetree.RotateBalancedBinTree;

import static don.juan.matus.lib.bintree.BinTreeInterface.*;

/*
Classic Red-Black binary tree.
 */
public class RedBlackTree<T extends Comparable<T>> extends RotateBalancedBinTree<T> {

    public RedBlackTree() {
        super();
        root = new BinTreeNodeRB<T>(null, null, null, null);
    }

    @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        return rebalanceAfterInsertion((BinTreeNodeRB<T>)currentNode);
    }

    private BinTreeNodeInterface<T> rebalanceAfterInsertion(BinTreeNodeRB<T> currentNode) {
        currentNode.color = BinTreeNodeRB.RED;
        while (currentNode != null && currentNode != root.getLeft() && ((BinTreeNodeRB<T>) currentNode.getParent()).color == BinTreeNodeRB.RED) {
            if (parentOf(currentNode) == leftOf(parentOf(parentOf(currentNode)))) {
                BinTreeNodeRB<T> y = (BinTreeNodeRB<T>) rightOf(parentOf(parentOf(currentNode)));
                if (colorOf(y) == BinTreeNodeRB.RED) {
                    setColor((BinTreeNodeRB<T>) parentOf(currentNode), BinTreeNodeRB.BLACK);
                    setColor(y, BinTreeNodeRB.BLACK);
                    setColor((BinTreeNodeRB<T>) parentOf(parentOf(currentNode)), BinTreeNodeRB.RED);
                    currentNode = (BinTreeNodeRB<T>) parentOf(parentOf(currentNode));
                } else {
                    if (currentNode == rightOf(parentOf(currentNode))) {
                        currentNode = (BinTreeNodeRB<T>) parentOf(currentNode);
                        rotateLeft(currentNode);
                    }
                    setColor((BinTreeNodeRB<T>) BinTreeInterface.parentOf(currentNode), BinTreeNodeRB.BLACK);
                    setColor((BinTreeNodeRB<T>) parentOf(parentOf(currentNode)), BinTreeNodeRB.RED);
                    rotateRight(parentOf(parentOf(currentNode)));
                }
            } else {
                BinTreeNodeRB<T> y = (BinTreeNodeRB<T>) leftOf(parentOf(parentOf(currentNode)));
                if (colorOf(y) == BinTreeNodeRB.RED) {
                    setColor((BinTreeNodeRB<T>) parentOf(currentNode), BinTreeNodeRB.BLACK);
                    setColor(y, BinTreeNodeRB.BLACK);
                    setColor((BinTreeNodeRB<T>) parentOf(parentOf(currentNode)), BinTreeNodeRB.RED);
                    currentNode = (BinTreeNodeRB<T>)parentOf(parentOf(currentNode));
                } else {
                    if (currentNode == leftOf(parentOf(currentNode))) {
                        currentNode = (BinTreeNodeRB<T>) parentOf(currentNode);
                        rotateRight(currentNode);
                    }
                    setColor((BinTreeNodeRB<T>) parentOf(currentNode), BinTreeNodeRB.BLACK);
                    setColor((BinTreeNodeRB<T>) parentOf(parentOf(currentNode)), BinTreeNodeRB.RED);
                    rotateLeft(parentOf(parentOf(currentNode)));
                }
            }
        }
        ((BinTreeNodeRB<T>) root.getLeft()).color = BinTreeNodeRB.BLACK;
        return currentNode;
    }

    private static <T extends Comparable<T>> byte colorOf(BinTreeNodeRB<T> p) {
        return (p == null ? BinTreeNodeRB.BLACK : p.color);
    }

    private static <T extends Comparable<T>> void setColor(BinTreeNodeRB<T> p, byte c) {
        if (p != null)
            p.color = c;
    }

    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        boolean result = super.checkTreeNode(thePassEvent, btiLeft, btiRight, currentNode, previousNode);
        if (result) {
            BinTreeNodeRB<T> current = (BinTreeNodeRB<T>) currentNode;
            BinTreeNodeRB<T> parent = (BinTreeNodeRB<T>) currentNode.getParent();
            BinTreeNodeRB<T> left = (BinTreeNodeRB<T>) currentNode.getLeft();
            BinTreeNodeRB<T> right = (BinTreeNodeRB<T>) currentNode.getRight();
            if (current.isRed()) {
                if (!(((left == null ? BinTreeNodeRB.BLACK : left.getColor()) == BinTreeNodeRB.BLACK) && ((right == null ? BinTreeNodeRB.BLACK : right.getColor()) == BinTreeNodeRB.BLACK))) {
                    thePassEvent.setErrorMessage("RedBlackTree: Tree structure invalid, incorrect RED BLACK sequence!");
                    result = false;
                }
                if (parent != null && parent.isRed()) {
                    thePassEvent.setErrorMessage("RedBlackTree: Tree structure invalid, incorrect RED BLACK sequence!");
                    result = false;
                }
            }
            if (btiLeft != null && btiRight != null) {
                BinTreeNodeRB.CountBlackHeight countBlackHeightLeft = (BinTreeNodeRB.CountBlackHeight) btiLeft.getOuterObject();
                BinTreeNodeRB.CountBlackHeight countBlackHeightRight = (BinTreeNodeRB.CountBlackHeight) btiRight.getOuterObject();
                if (abs(countBlackHeightLeft.maxBlackHeight - countBlackHeightRight.maxBlackHeight) > 1) {
                    thePassEvent.setErrorMessage("RedBlackTree: Tree structure invalid, black height difference more then 1!");
                    result = false;
                }
            }
        }
        return result;
    }

}
