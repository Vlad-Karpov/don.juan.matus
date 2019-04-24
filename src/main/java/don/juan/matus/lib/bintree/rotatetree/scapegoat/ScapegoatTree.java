package don.juan.matus.lib.bintree.rotatetree.scapegoat;

import don.juan.matus.lib.bintree.BinTreeIterator;
import don.juan.matus.lib.bintree.BinTreeNodeBase;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.bintree.rotatetree.RotateBalancedBinTree;

public class ScapegoatTree<T extends Comparable<T>> extends RotateBalancedBinTree<T> {

    private double alpha = 0.6d;

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
        if (level > (Math.log(size) / Math.log(alpha) + 1)) {
            long size_left = 0L;
            long size_right = 0L;
            long size_node;
            while (cursor != root) {
                size_node = size_left + size_right + 1;
                if (size_left > alpha * size_node) {
                    cursor = super.rotateRight(cursor);
                }
                if (size_right > alpha * size_node) {
                    cursor = super.rotateLeft(cursor);
                }
                if (cursor != root) {
                    if (cursor == cursor.getParent().getLeft()) {
                        size_left = size_node;
                        size_right = sizeOfNode(cursor.getParent().getRight());
                    } else {
                        size_left = sizeOfNode(cursor.getParent().getLeft());;
                        size_right = size_node;
                    }
                    cursor = cursor.getParent();
                }
            }
        }
        return cursor;
    }

    private long sizeOfNode(BinTreeNodeInterface<T> cursor) {
        if (cursor != null) {
            BinTreeIterator<T> bti = new BinTreeIterator<T>(this, cursor);
            while (bti.hasNext()) bti.next();
            return bti.getSize();
        } else {
            return 0L;
        }
    }

    @Override
    protected void changeNode(BinTreeNodeInterface<T> theCurrentNode) {
        BinTreeNodeInterface<T> cursor;
        cursor = theCurrentNode.getParent();
        while (cursor != root) {
            cursor = cursor.getParent();
            //if (cursor != root) cursor = rebalance(cursor);
        }
    }


}
