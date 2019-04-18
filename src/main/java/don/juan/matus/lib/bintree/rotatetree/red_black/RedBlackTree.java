package don.juan.matus.lib.bintree.rotatetree.red_black;

import don.juan.matus.lib.bintree.BinTreeBase;
import don.juan.matus.lib.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.bintree.BinTreeIterator;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.bintree.rotatetree.RotateBalancedBinTree;

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
        BinTreeNodeRB<T> parent;
        BinTreeNodeRB<T> grandfather;
        BinTreeNodeRB<T> uncle;
        byte cursorSide;
        byte parentSide;
        BinTreeNodeRB<T> cursor = (BinTreeNodeRB<T>) currentNode;
        if (cursor != null) {
            cursor.setColor(BinTreeNodeRB.RED);
            while (true) {
                parent = (BinTreeNodeRB<T>) cursor.getParent();
                if (parent != null) {
                    if (parent != root) {
                        if (parent.isRed()) {
                            grandfather = (BinTreeNodeRB<T>) parent.getParent();
                            if (grandfather != null && grandfather != root) {
                                if (grandfather.getRight() == parent) {
                                    uncle = (BinTreeNodeRB<T>) grandfather.getLeft();
                                } else {
                                    uncle = (BinTreeNodeRB<T>) grandfather.getRight();
                                }
                                if (uncle != null && uncle.isRed()) {
                                    uncle.setColor(BinTreeNodeRB.BLACK);
                                    parent.setColor(BinTreeNodeRB.BLACK);
                                    grandfather.setColor(BinTreeNodeRB.RED);
                                    cursor = grandfather;
                                } else {
                                    cursorSide = parent.getLeft() == cursor ? BinTreeBase.LEFT : BinTreeBase.RIGHT;
                                    parentSide = grandfather.getLeft() == parent ? BinTreeBase.LEFT : BinTreeBase.RIGHT;
                                    if (cursorSide != parentSide) {
                                        cursor = parent;
                                        if (parentSide == BinTreeBase.LEFT) {
                                            parent = (BinTreeNodeRB<T>) super.rotateLeft(parent);
                                        } else {
                                            parent = (BinTreeNodeRB<T>) super.rotateRight(parent);
                                        }
                                    }
                                    parent.setColor(BinTreeNodeRB.RED);
                                    cursor.setColor(BinTreeNodeRB.BLACK);
                                    if (grandfather != root) {
                                        grandfather.setColor(BinTreeNodeRB.BLACK);
                                        if (parentSide == BinTreeBase.LEFT) {
                                            grandfather = (BinTreeNodeRB<T>) super.rotateRight(grandfather);
                                        } else {
                                            grandfather = (BinTreeNodeRB<T>) super.rotateLeft(grandfather);
                                        }
                                        cursor = grandfather;
                                    } else {
                                        break;
                                    }
                                }
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        if (cursor.isRed()) {
                            cursor.setColor(BinTreeNodeRB.BLACK);
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return cursor;
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
