package don.juan.matus.lib.tree.bintree.rotatetree.scapegoat;

import don.juan.matus.lib.tree.bintree.BinTreeNodeBase;
import don.juan.matus.lib.tree.bintree.BinTreeNodeInterface;

import java.util.Random;

public class RandomRotateBinTree<T extends Comparable<T>> extends ScapegoatTree<T> {

    private Random rnd;

    public RandomRotateBinTree() {
        super();
        alpha = 0.5d;
        rnd = new Random();
        root = new BinTreeNodeBase<>(null, null, null, null);
    }

    protected BinTreeNodeInterface<T> rebalance(BinTreeNodeInterface<T> cursor) {
        boolean isLeft = (cursor == cursor.getParent().getLeft());
        Long clevel = level;
        cursor = cursor.getParent();
        while (cursor != root) {
            if (rnd.nextDouble() < (clevel.doubleValue() / level.doubleValue())) {
                if (isLeft) {
                    cursor = rotateRight(cursor);
                } else {
                    cursor = rotateLeft(cursor);
                }
            }
            isLeft = (cursor == cursor.getParent().getLeft());
            cursor = cursor.getParent();
            clevel--;
        }
        return cursor;
    }

}
