package don.juan.matus.lib.collection.tree.bintree.rotatetree.red_black;

import don.juan.matus.lib.collection.tree.bintree.*;

import static don.juan.matus.lib.collection.tree.bintree.BinTreeInterface.*;

/*
Classic Red-Black binary tree.
 */
public class RedBlackTree<T extends Comparable<T>> extends BinTreeBase<T> {

    public RedBlackTree() {
        super();
        root = new BinTreeNodeRedBlack<T>(null, null, null, null);
    }

    @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        return rebalanceAfterInsertion((BinTreeNodeRedBlack<T>) currentNode);
    }

    @Override
    protected void changeNode(BinTreeNodeInterface<T> theCurrentNode) {
        rebalanceAfterDeletion((BinTreeNodeRedBlack<T>) theCurrentNode);
    }

    private void rebalanceAfterDeletion(BinTreeNodeRedBlack<T> theCurrentNode) {
        while (theCurrentNode != root.getLeft() && colorOf(theCurrentNode) == BinTreeNodeRedBlack.BLACK) {
            if (theCurrentNode == leftOf(parentOf(theCurrentNode))) {
                BinTreeNodeRedBlack<T> sib = (BinTreeNodeRedBlack<T>) rightOf(parentOf(theCurrentNode));
                if (colorOf(sib) == BinTreeNodeRedBlack.RED) {
                    setColor(sib, BinTreeNodeRedBlack.BLACK);
                    setColor((BinTreeNodeRedBlack<T>) parentOf(theCurrentNode), BinTreeNodeRedBlack.RED);
                    rotateLeft(parentOf(theCurrentNode));
                    sib = (BinTreeNodeRedBlack<T>) rightOf(parentOf(theCurrentNode));
                }
                if (colorOf((BinTreeNodeRedBlack<T>) leftOf(sib)) == BinTreeNodeRedBlack.BLACK &&
                        colorOf((BinTreeNodeRedBlack<T>) rightOf(sib)) == BinTreeNodeRedBlack.BLACK) {
                    setColor(sib, BinTreeNodeRedBlack.RED);
                    theCurrentNode = (BinTreeNodeRedBlack<T>) parentOf(theCurrentNode);
                } else {
                    if (colorOf((BinTreeNodeRedBlack<T>) rightOf(sib)) == BinTreeNodeRedBlack.BLACK) {
                        setColor((BinTreeNodeRedBlack<T>) leftOf(sib), BinTreeNodeRedBlack.BLACK);
                        setColor(sib, BinTreeNodeRedBlack.RED);
                        rotateRight(sib);
                        sib = (BinTreeNodeRedBlack<T>) rightOf(parentOf(theCurrentNode));
                    }
                    setColor(sib, colorOf((BinTreeNodeRedBlack<T>) parentOf(theCurrentNode)));
                    setColor((BinTreeNodeRedBlack<T>) parentOf(theCurrentNode), BinTreeNodeRedBlack.BLACK);
                    setColor((BinTreeNodeRedBlack<T>) rightOf(sib), BinTreeNodeRedBlack.BLACK);
                    rotateLeft(parentOf(theCurrentNode));
                    theCurrentNode = (BinTreeNodeRedBlack<T>) root.getLeft();
                }
            } else {
                BinTreeNodeRedBlack<T> sib = (BinTreeNodeRedBlack<T>) leftOf(parentOf(theCurrentNode));
                if (colorOf(sib) == BinTreeNodeRedBlack.RED) {
                    setColor(sib, BinTreeNodeRedBlack.BLACK);
                    setColor((BinTreeNodeRedBlack<T>) parentOf(theCurrentNode), BinTreeNodeRedBlack.RED);
                    rotateRight(parentOf(theCurrentNode));
                    sib = (BinTreeNodeRedBlack<T>) leftOf(parentOf(theCurrentNode));
                }
                if (colorOf((BinTreeNodeRedBlack<T>) rightOf(sib)) == BinTreeNodeRedBlack.BLACK &&
                        colorOf((BinTreeNodeRedBlack<T>) leftOf(sib)) == BinTreeNodeRedBlack.BLACK) {
                    setColor(sib, BinTreeNodeRedBlack.RED);
                    theCurrentNode = (BinTreeNodeRedBlack<T>) parentOf(theCurrentNode);
                } else {
                    if (colorOf((BinTreeNodeRedBlack<T>) leftOf(sib)) == BinTreeNodeRedBlack.BLACK) {
                        setColor((BinTreeNodeRedBlack<T>) rightOf(sib), BinTreeNodeRedBlack.BLACK);
                        setColor(sib, BinTreeNodeRedBlack.RED);
                        rotateLeft(sib);
                        sib = (BinTreeNodeRedBlack<T>) leftOf(parentOf(theCurrentNode));
                    }
                    setColor(sib, colorOf((BinTreeNodeRedBlack<T>) parentOf(theCurrentNode)));
                    setColor((BinTreeNodeRedBlack<T>) parentOf(theCurrentNode), BinTreeNodeRedBlack.BLACK);
                    setColor((BinTreeNodeRedBlack<T>) leftOf(sib), BinTreeNodeRedBlack.BLACK);
                    rotateRight(parentOf(theCurrentNode));
                    theCurrentNode = (BinTreeNodeRedBlack<T>) root.getLeft();
                }
            }
        }
        setColor(theCurrentNode, BinTreeNodeRedBlack.BLACK);
    }


    private BinTreeNodeInterface<T> rebalanceAfterInsertion(BinTreeNodeRedBlack<T> currentNode) {
        currentNode.color = BinTreeNodeRedBlack.RED;
        while (currentNode != null && currentNode != root.getLeft() && ((BinTreeNodeRedBlack<T>) currentNode.getParent()).color == BinTreeNodeRedBlack.RED) {
            if (parentOf(currentNode) == leftOf(parentOf(parentOf(currentNode)))) {
                BinTreeNodeRedBlack<T> y = (BinTreeNodeRedBlack<T>) rightOf(parentOf(parentOf(currentNode)));
                if (colorOf(y) == BinTreeNodeRedBlack.RED) {
                    setColor((BinTreeNodeRedBlack<T>) parentOf(currentNode), BinTreeNodeRedBlack.BLACK);
                    setColor(y, BinTreeNodeRedBlack.BLACK);
                    setColor((BinTreeNodeRedBlack<T>) parentOf(parentOf(currentNode)), BinTreeNodeRedBlack.RED);
                    currentNode = (BinTreeNodeRedBlack<T>) parentOf(parentOf(currentNode));
                } else {
                    if (currentNode == rightOf(parentOf(currentNode))) {
                        currentNode = (BinTreeNodeRedBlack<T>) parentOf(currentNode);
                        rotateLeft(currentNode);
                    }
                    setColor((BinTreeNodeRedBlack<T>) BinTreeInterface.parentOf(currentNode), BinTreeNodeRedBlack.BLACK);
                    setColor((BinTreeNodeRedBlack<T>) parentOf(parentOf(currentNode)), BinTreeNodeRedBlack.RED);
                    rotateRight(parentOf(parentOf(currentNode)));
                }
            } else {
                BinTreeNodeRedBlack<T> y = (BinTreeNodeRedBlack<T>) leftOf(parentOf(parentOf(currentNode)));
                if (colorOf(y) == BinTreeNodeRedBlack.RED) {
                    setColor((BinTreeNodeRedBlack<T>) parentOf(currentNode), BinTreeNodeRedBlack.BLACK);
                    setColor(y, BinTreeNodeRedBlack.BLACK);
                    setColor((BinTreeNodeRedBlack<T>) parentOf(parentOf(currentNode)), BinTreeNodeRedBlack.RED);
                    currentNode = (BinTreeNodeRedBlack<T>) parentOf(parentOf(currentNode));
                } else {
                    if (currentNode == leftOf(parentOf(currentNode))) {
                        currentNode = (BinTreeNodeRedBlack<T>) parentOf(currentNode);
                        rotateRight(currentNode);
                    }
                    setColor((BinTreeNodeRedBlack<T>) parentOf(currentNode), BinTreeNodeRedBlack.BLACK);
                    setColor((BinTreeNodeRedBlack<T>) parentOf(parentOf(currentNode)), BinTreeNodeRedBlack.RED);
                    rotateLeft(parentOf(parentOf(currentNode)));
                }
            }
        }
        ((BinTreeNodeRedBlack<T>) root.getLeft()).color = BinTreeNodeRedBlack.BLACK;
        return currentNode;
    }

    private static <T extends Comparable<T>> byte colorOf(BinTreeNodeRedBlack<T> p) {
        return (p == null ? BinTreeNodeRedBlack.BLACK : p.color);
    }

    private static <T extends Comparable<T>> void setColor(BinTreeNodeRedBlack<T> p, byte c) {
        if (p != null)
            p.color = c;
    }

    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        boolean result = super.checkTreeNode(thePassEvent, btiLeft, btiRight, currentNode, previousNode);
        if (result) {
            BinTreeNodeRedBlack<T> current = (BinTreeNodeRedBlack<T>) currentNode;
            BinTreeNodeRedBlack<T> parent = (BinTreeNodeRedBlack<T>) currentNode.getParent();
            BinTreeNodeRedBlack<T> left = (BinTreeNodeRedBlack<T>) currentNode.getLeft();
            BinTreeNodeRedBlack<T> right = (BinTreeNodeRedBlack<T>) currentNode.getRight();
            if (current.isRed()) {
                if (!(((left == null ? BinTreeNodeRedBlack.BLACK : left.getColor()) == BinTreeNodeRedBlack.BLACK) && ((right == null ? BinTreeNodeRedBlack.BLACK : right.getColor()) == BinTreeNodeRedBlack.BLACK))) {
                    thePassEvent.setErrorMessage("RedBlackTree: Tree structure invalid, incorrect RED BLACK sequence!");
                    result = false;
                }
                if (parent != null && parent.isRed()) {
                    thePassEvent.setErrorMessage("RedBlackTree: Tree structure invalid, incorrect RED BLACK sequence!");
                    result = false;
                }
            }
            if (btiLeft != null && btiRight != null) {
                BinTreeNodeRedBlack.CountBlackHeight countBlackHeightLeft = (BinTreeNodeRedBlack.CountBlackHeight) btiLeft.getOuterObject();
                BinTreeNodeRedBlack.CountBlackHeight countBlackHeightRight = (BinTreeNodeRedBlack.CountBlackHeight) btiRight.getOuterObject();
                if (abs(countBlackHeightLeft.maxBlackHeight - countBlackHeightRight.maxBlackHeight) > 1) {
                    thePassEvent.setErrorMessage("RedBlackTree: Tree structure invalid, black height difference more then 1!");
                    result = false;
                }
            }
        }
        return result;
    }

}
