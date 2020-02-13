package don.juan.matus.lib.tree.bintree.rotatetree.aa;

import don.juan.matus.lib.tree.bintree.BinTreeBase;
import don.juan.matus.lib.tree.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.tree.bintree.BinTreeIterator;
import don.juan.matus.lib.tree.bintree.BinTreeNodeInterface;

import static don.juan.matus.lib.tree.bintree.BinTreeInterface.*;

public class AATree<T extends Comparable<T>> extends BinTreeBase<T> {

    public AATree() {
        super();
        root = new BinTreeNodeAA<T>(null, null, null, null);
    }

    BinTreeNodeAA<T> skew(BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeAA<T> result = (BinTreeNodeAA<T>) currentNode;
        if (result != null) {
            BinTreeNodeAA<T> left = (BinTreeNodeAA<T>) result.getLeft();
            if (left != null && left.getLevel() == result.getLevel()) {
                result = (BinTreeNodeAA<T>) rotateRight(result);
            }
        }
        return result;
    }

    BinTreeNodeAA<T> split(BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeAA<T> result = (BinTreeNodeAA<T>) currentNode;
        if (result != null) {
            BinTreeNodeAA<T> right = (BinTreeNodeAA<T>) result.getRight();
            if (right != null && right.getLevel() == result.getLevel()) {
                BinTreeNodeAA<T> x = (BinTreeNodeAA<T>) right.getRight();
                if (x != null && x.getLevel() == result.getLevel()) {
                    result = (BinTreeNodeAA<T>) rotateLeft(currentNode);
                    result.incLevel();
                }
            }
        }
        return result;
    }

    @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeAA<T> cursor = (BinTreeNodeAA<T>) currentNode;
        while (cursor != root) {
            cursor = skew(cursor);
            cursor = split(cursor);
            cursor = (BinTreeNodeAA<T>) cursor.getParent();
        }
        return cursor;
    }

    @Override
    protected void changeNodeAfter(BinTreeNodeInterface<T> theCurrentNode) {
        BinTreeNodeAA<T> cursor = (BinTreeNodeAA<T>) theCurrentNode;
        while (cursor != root) {
            BinTreeNodeAA<T> left = (BinTreeNodeAA<T>) cursor.getLeft();
            BinTreeNodeAA<T> right = (BinTreeNodeAA<T>) cursor.getRight();
            if (((left == null ? 0 : left.getLevel()) < cursor.getLevel() - 1) || ((right == null ? 0 : right.getLevel()) < cursor.getLevel() - 1)) {
                cursor.decLevel();
                if (right != null) {
                    if (right.getLevel() > cursor.getLevel()) {
                        right.setLevel(cursor.getLevel());
                    }
                }
            }
            cursor = skew(cursor);
            skew(rightOf(cursor));
            skew(rightOf(rightOf(cursor)));
            cursor = split(cursor);
            split(rightOf(cursor));
            cursor = (BinTreeNodeAA<T>) cursor.getParent();
        }
    }

    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        boolean result = super.checkTreeNode(thePassEvent, btiLeft, btiRight, currentNode, previousNode);
        BinTreeNodeAA<T> c =(BinTreeNodeAA<T>) currentNode;
        BinTreeNodeAA<T> l =(BinTreeNodeAA<T>) currentNode.getLeft();
        BinTreeNodeAA<T> r = (BinTreeNodeAA<T>) currentNode.getRight();
        if (l == null && r == null) {
            if (c.getLevel() != 1) {
                thePassEvent.setErrorMessage("AATree: Tree structure invalid, leaf level must be 1!");
                result = false;
            }
        }
        if (l != null) {
            if (c.getLevel() == (l.getLevel() - 1)) {
                thePassEvent.setErrorMessage("AATree: Tree structure invalid, left level must be less on 1 then current!");
                result = false;
            }
        }
        if (r != null) {
            if ( !((c.getLevel() == r.getLevel()) || ((c.getLevel() - 1) == r.getLevel())) ) {
                thePassEvent.setErrorMessage("AATree: Tree structure invalid, right level must be equal or less then 1 current!");
                result = false;
            }
            BinTreeNodeAA<T> rr = (BinTreeNodeAA<T>) r.getRight();
            if (rr != null && rr.getLevel() >= c.getLevel()) {
                thePassEvent.setErrorMessage("AATree: Tree structure invalid, right right level must be less current!");
                result = false;
            }
            BinTreeNodeAA<T> rl = (BinTreeNodeAA<T>) r.getLeft();
            if (rl != null && rl.getLevel() >= c.getLevel()) {
                thePassEvent.setErrorMessage("AATree: Tree structure invalid, right left level must be less current!");
                result = false;
            }
        }
        if (l != null && r != null) {
            if (c.getLevel() <= 1) {
                thePassEvent.setErrorMessage("AATree: Tree structure invalid, not leaf node must level greeter then 1!");
                result = false;
            }
        }
        return result;
    }

}
