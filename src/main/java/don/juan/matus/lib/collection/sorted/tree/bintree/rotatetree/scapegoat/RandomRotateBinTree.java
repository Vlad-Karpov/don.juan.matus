package don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.scapegoat;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeBase;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;

import java.util.Random;

public class RandomRotateBinTree<T extends Comparable<T>> extends ScapegoatTree<T> {

    private Random rnd;

    public RandomRotateBinTree() {
        super();
        alpha = 0.7d;
        rnd = new Random();
        root = new BinTreeNodeBase<>(null, null, null, null);
    }

    protected BinTreeNodeInterface<T> rebalance(BinTreeNodeInterface<T> cursor) {
        boolean isLeft = (cursor == cursor.getParent().getLeft());
        if (cursor.getParent() == root) {
            isLeft = true;
        }
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
