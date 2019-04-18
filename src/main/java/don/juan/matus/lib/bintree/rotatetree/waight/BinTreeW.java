package don.juan.matus.lib.bintree.rotatetree.waight;

import don.juan.matus.lib.bintree.BinTreeCheckPassEvent;
import don.juan.matus.lib.bintree.BinTreeIterator;
import don.juan.matus.lib.bintree.BinTreeNodeInterface;
import don.juan.matus.lib.bintree.rotatetree.RotateBalancedBinTree;

/**
 * Двоичное дерево с балансировкой по весам.
 */
public class BinTreeW<T extends Comparable<T>> extends RotateBalancedBinTree<T> {

    public BinTreeW() {
        super();
        root = new BinTreeNodeW<T>(null, null, null, null);
    }

    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        BinTreeNodeW<T> cursor = (BinTreeNodeW<T>) currentNode;
        do {
            long leftWeight = cursor.getLeft() == null ? 0 : ((BinTreeNodeW<T>) cursor.getLeft()).getWeight();
            long rightWeight = cursor.getRight() == null ? 0 : ((BinTreeNodeW<T>) cursor.getRight()).getWeight();
            if (rightWeight > leftWeight * 4) {
                cursor = rotateLeft(cursor);
            }
            if (leftWeight > rightWeight * 4) {
                cursor = rotateRight(cursor);
            }
            cursor = (BinTreeNodeW<T>) cursor.getParent();
        } while (cursor != null && cursor.getParent() != null);
        return cursor;
    }

    protected BinTreeNodeW<T> rotateRight(BinTreeNodeW<T> currentNode) {
        BinTreeNodeW<T> node2 = (BinTreeNodeW<T>) currentNode.getLeft();
        super.rotateRight(currentNode);
        currentNode.setWeight((currentNode.getLeft() == null ? -1 : ((BinTreeNodeW<T>) currentNode.getLeft()).getWeight()) + (currentNode.getRight() == null ? -1 : ((BinTreeNodeW<T>) currentNode.getRight()).getWeight()) + 2);
        node2.setWeight((node2.getLeft() == null ? -1 : ((BinTreeNodeW<T>) node2.getLeft()).getWeight()) + (node2.getRight() == null ? -1 : ((BinTreeNodeW<T>) node2.getRight()).getWeight()) + 2);
        return node2;
    }

    protected BinTreeNodeW<T> rotateLeft(BinTreeNodeW<T> currentNode) {
        BinTreeNodeW<T> node2 = (BinTreeNodeW<T>) currentNode.getRight();
        super.rotateLeft(currentNode);
        currentNode.setWeight((currentNode.getLeft() == null ? -1 : ((BinTreeNodeW<T>) currentNode.getLeft()).getWeight()) + (currentNode.getRight() == null ? -1 : ((BinTreeNodeW<T>) currentNode.getRight()).getWeight()) + 2);
        node2.setWeight((node2.getLeft() == null ? -1 : ((BinTreeNodeW<T>) node2.getLeft()).getWeight()) + (node2.getRight() == null ? -1 : ((BinTreeNodeW<T>) node2.getRight()).getWeight()) + 2);
        return node2;
    }

    @Override
    public boolean checkTreeNode(BinTreeCheckPassEvent<T> thePassEvent, BinTreeIterator<T> btiLeft, BinTreeIterator<T> btiRight, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        boolean result = super.checkTreeNode(thePassEvent, btiLeft, btiRight, currentNode, previousNode);
        if (result) {
            if (btiLeft != null && btiRight != null) {
                if (btiLeft.getSize() + btiRight.getSize() != ((BinTreeNodeW<T>)currentNode).getWeight()) {
                    thePassEvent.setErrorMessage("BinTreeW: Tree structure invalid, incorrect weight!");
                    result = false;
                }
            }
        }
        return result;
    }

}
