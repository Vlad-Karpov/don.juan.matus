package don.juan.matus.lib.tree.bintree.rotatetree.splay;

import don.juan.matus.lib.tree.bintree.BinTreeBase;
import don.juan.matus.lib.tree.bintree.BinTreeNodeInterface;

import static don.juan.matus.lib.tree.bintree.BinTreeInterface.*;

/*
Splay tree
 */
public class SplayTree<T extends Comparable<T>> extends BinTreeBase<T> {

    @Override
    public void splay(final BinTreeNodeInterface<T> theRoot, final BinTreeNodeInterface<T> currentNode) {
        while (parentOf(currentNode) != theRoot)  {
            if (currentNode == parentOf(currentNode).getLeft()) {
                if (parentOf(parentOf(currentNode)) == theRoot) {
                    rotateRight(parentOf(currentNode));
                } else if (parentOf(currentNode) == parentOf(parentOf(currentNode)).getLeft()) {
                    rotateRight(parentOf(parentOf(currentNode)));
                    rotateRight(parentOf(currentNode));
                } else {
                    rotateRight(parentOf(currentNode));
                    rotateLeft(parentOf(currentNode));
                }
            } else {
                if (parentOf(parentOf(currentNode)) == theRoot) {
                    rotateLeft(parentOf(currentNode));
                } else if (parentOf(currentNode) == parentOf(parentOf(currentNode)).getRight()) {
                    rotateLeft(parentOf(parentOf(currentNode)));
                    rotateLeft(parentOf(currentNode));
                } else {
                    rotateLeft(parentOf(currentNode));
                    rotateRight(parentOf(currentNode));
                }

            }
        }
    }

    private BinTreeNodeInterface<T> mergeSplay(BinTreeNodeInterface<T> left, BinTreeNodeInterface<T> right) {
        BinTreeNodeInterface<T> extreme = null;
        if (left != null) {
            extreme = left;
            while (extreme.getRight() != null)
                extreme = extreme.getRight();
            splay(parentOf(left), extreme);
            extreme.setRight(right);
            if (right != null) right.setParent(extreme);
        } else {
            if (right != null) {
                extreme = right;
                while (extreme.getLeft() != null)
                    extreme = extreme.getLeft();
                splay(parentOf(right), extreme);
                extreme.setLeft(left);
                if (left != null) left.setParent(extreme);
            }
        }
        return extreme;
    }

    private void splitSplay(MergeParts parts, BinTreeNodeInterface<T> node) {
        splay(root, node);
        parts.leftTree = node.getLeft();
        if (parts.leftTree != null) {
            parts.leftTree.setParent(null);
            node.setLeft(null);
        }
        parts.rightTree = node;
    }

    @Override
    public BinTreeNodeInterface<T> removeNode(
            Boolean theDescending,
            BinTreeNodeInterface<T> currentNode,
            BinTreeNodeInterface<T> nextNode) {
        BinTreeNodeInterface<T> result = nextNode;
        splay(root, currentNode);
        result = currentNode.getRight();
        while (result.getLeft() != null)
            result = result.getLeft();
        currentNode = mergeSplay(currentNode.getLeft(), currentNode.getRight());
        currentNode.setParent(root);
        root.setLeft(currentNode);
        size--;
        return result;
    }

        @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        splay(root, currentNode);
        return currentNode;
    }

}
