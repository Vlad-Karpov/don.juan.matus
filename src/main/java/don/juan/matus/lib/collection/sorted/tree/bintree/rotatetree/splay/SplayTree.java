package don.juan.matus.lib.collection.sorted.tree.bintree.rotatetree.splay;

import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeBase;
import static don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface.*;
import don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeNodeInterface;

import static don.juan.matus.lib.collection.sorted.tree.bintree.BinTreeInterface.parentOf;

/*
Splay tree
 */
public class SplayTree<T extends Comparable<T>> extends BinTreeBase<T> {

    @Override
    public void splay(final BinTreeNodeInterface<T> theRoot, final BinTreeNodeInterface<T> currentNode) {
        level = 0L;
        while (parentOf(currentNode) != theRoot) {
            splayQuantum(theRoot, currentNode);
            level++;
            if (maxLevel < level) maxLevel = level;
        }
    }

    protected void splayQuantum(BinTreeNodeInterface<T> theRoot, BinTreeNodeInterface<T> currentNode) {
        if (currentNode == parentOf(currentNode).getLeft()) {
            if (parentOf(parentOf(currentNode)) == theRoot) {
                rotateRight(parentOf(currentNode));
            } else if (parentOf(currentNode) == parentOf(parentOf(currentNode)).getLeft()) {
                rotateRight(parentOf(parentOf(currentNode)));
                rotateRight(parentOf(currentNode));
            } else {
                zigZagRightLift(currentNode);
            }
        } else {
            if (parentOf(parentOf(currentNode)) == theRoot) {
                rotateLeft(parentOf(currentNode));
            } else if (parentOf(currentNode) == parentOf(parentOf(currentNode)).getRight()) {
                rotateLeft(parentOf(parentOf(currentNode)));
                rotateLeft(parentOf(currentNode));
            } else {
                zigZagLiftRight(currentNode);
            }
        }
    }

    protected void zigZagRightLift(BinTreeNodeInterface<T> currentNode) {
        rotateRight(parentOf(currentNode));
        rotateLeft(parentOf(currentNode));
    }
    protected void zigZagLiftRight(BinTreeNodeInterface<T> currentNode) {
        rotateLeft(parentOf(currentNode));
        rotateRight(parentOf(currentNode));
    }

    private BinTreeNodeInterface<T> mergeSplay(BinTreeNodeInterface<T> left, BinTreeNodeInterface<T> right) {
        BinTreeNodeInterface<T> extreme;
        if (left == null) return right;
        if (right == null) return left;
        if (choiceLeftOrRight(left, right)) {
            extreme = left;
            while (extreme.getRight() != null)
                extreme = extreme.getRight();
            splay(parentOf(left), extreme);
            onMergeSplay(extreme);
            extreme.setRight(right);
            right.setParent(extreme);
        } else {
            extreme = right;
            while (extreme.getLeft() != null)
                extreme = extreme.getLeft();
            splay(parentOf(right), extreme);
            onMergeSplay(extreme);
            extreme.setLeft(left);
            left.setParent(extreme);
        }
        return extreme;
    }

    protected boolean choiceLeftOrRight(BinTreeNodeInterface<T> left, BinTreeNodeInterface<T> right) {
        return getRandomBoolean();
    }

    protected void onMergeSplay(BinTreeNodeInterface<T> extreme) {

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
        if (currentNode != null) {
            splay(root, currentNode);
//        result = currentNode.getRight();
//        while (result != null && result.getLeft() != null)
//            result = result.getLeft();
            currentNode = mergeSplay(currentNode.getLeft(), currentNode.getRight());
            if (currentNode != null)
                currentNode.setParent(root);
            root.setLeft(currentNode);
            size--;
        }
        return result;
    }

    @Override
    protected BinTreeNodeInterface<T> postAddLoop(final BinTreeNodeInterface<T> currentNode) {
        splay(root, currentNode);
        return currentNode;
    }

}
