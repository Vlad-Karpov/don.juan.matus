package don.juan.matus.lib.tree.bintree.rotatetree.aa;

import don.juan.matus.lib.tree.bintree.BinTreeBase;
import don.juan.matus.lib.tree.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.tree.bintree.BinTreeIterator;
import don.juan.matus.lib.tree.bintree.BinTreeNodeInterface;

public class AATree<T extends Comparable<T>> extends BinTreeBase<T> {

    public AATree() {
        super();
        root = new BinTreeNodeAA<T>(null, null, null, null);
    }

    BinTreeNodeAA<T> skew(BinTreeNodeAA<T> currentNode) {
        BinTreeNodeAA<T> result = currentNode;
        if (currentNode != null) {
            BinTreeNodeAA<T> left = (BinTreeNodeAA<T>) currentNode.getLeft();
            if (left != null && left.getLevel() == currentNode.getLevel()) {
                BinTreeNodeAA<T> b = (BinTreeNodeAA<T>) left.getRight();
                if (b != null) b.setParent(currentNode);
                currentNode.setLeft(b);
                BinTreeNodeAA<T> parent = (BinTreeNodeAA<T>) currentNode.getParent();
                left.setRight(currentNode);
                currentNode.setParent(left);
                left.setParent(parent);
                if (parent.getLeft() == currentNode) {
                    parent.setLeft(left);
                } else {
                    parent.setRight(left);
                }
                result = left;
            }
        }
        return result;
    }

    BinTreeNodeAA<T> split(BinTreeNodeAA<T> currentNode) {
        return null;
    }

    @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        return balanceTree((BinTreeNodeAA<T>) currentNode);
    }

    private BinTreeNodeInterface<T> balanceTree(BinTreeNodeAA<T> currentNode) {
        BinTreeNodeAA<T> cursor = currentNode;
        while (cursor != root) {
            cursor = skew(cursor);
            cursor = split(cursor);
            cursor = (BinTreeNodeAA<T>) cursor.getParent();
            ;
        }
        return cursor;
    }

    @Override
    protected void changeNode(BinTreeNodeInterface<T> theCurrentNode) {
        balanceTree((BinTreeNodeAA<T>) theCurrentNode);
    }

    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        boolean result = super.checkTreeNode(thePassEvent, btiLeft, btiRight, currentNode, previousNode);
        return result;
    }

}
