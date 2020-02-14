package don.juan.matus.lib.collection.tree.bintree.rotatetree.scapegoat;

import don.juan.matus.lib.collection.tree.bintree.BinTreeBase;
import don.juan.matus.lib.collection.tree.bintree.BinTreeNodeBase;
import don.juan.matus.lib.collection.tree.bintree.BinTreeNodeInterface;

public class ScapegoatTree<T extends Comparable<T>> extends BinTreeBase<T> {

    protected double alpha = 0.7d;

    public ScapegoatTree() {
        super();
        root = new BinTreeNodeBase<T>(null, null, null, null);
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeInterface<T> cursor = currentNode;
        if (level > Math.floor(Math.log(size + 1) / Math.log(1 / alpha))) {
            cursor = rebalance(cursor);
        }
        return cursor;
    }

    protected BinTreeNodeInterface<T> rebalance(BinTreeNodeInterface<T> cursor) {
        long size_left = 0L;
        long size_right = 0L;
        long size_node;
        while (cursor != root) {
            size_node = size_left + size_right + 1;
            if (size_left > alpha * size_node) {
                cursor = super.rebalanceTree(cursor);
                break;
            }
            if (size_right > alpha * size_node) {
                cursor = super.rebalanceTree(cursor);
                break;
            }
            if (cursor == cursor.getParent().getLeft()) {
                size_left = size_node;
                size_right = sizeOfNode(cursor.getParent().getRight());
            } else {
                size_left = sizeOfNode(cursor.getParent().getLeft());
                size_right = size_node;
            }
            cursor = cursor.getParent();
        }
        return cursor;
    }

    @Override
    protected void changeNode(BinTreeNodeInterface<T> theCurrentNode) {
        BinTreeNodeInterface<T> cursor;
        cursor = theCurrentNode.getParent();
        Long height = 0L;
        while (cursor != root) {
            cursor = cursor.getParent();
            height++;
        }
        if (height > Math.floor(Math.log(size + 1) / Math.log(1 / alpha))) {
            super.rebalanceTree();
        }
    }


}
