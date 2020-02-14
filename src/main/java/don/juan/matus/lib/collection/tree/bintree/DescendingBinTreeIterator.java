package don.juan.matus.lib.collection.tree.bintree;

/**
 * Descending binary tree iterator.
 */
public class DescendingBinTreeIterator<T extends Comparable<T>> extends BinTreeIteratorBase<T> {

    public DescendingBinTreeIterator(BinTreeInterface<T> theBinTree, BinTreeNodeInterface<T> theRootNode) {
        super(theBinTree, theRootNode);
    }

    public DescendingBinTreeIterator(BinTreeInterface<T> theBinTree, BinTreeNodeInterface<T> theRootNode, T theObject) {
        super(theBinTree, theRootNode, theObject);
        leftOrRight = 3;
    }

    @Override
    protected void init() {
        seekGeneralCall = new GeneralCall<T>() {

            @Override
            public void generalCall(Boolean theResult, Integer theCmp, BinTreeNodeInterface<T> resultNode, Object generalObject) {
                if (theCmp == null) {
                    eof = true;
                } else {
                    if (theCmp == 0) {
                        currentNode = resultNode;
                    } else if (theCmp < 0) {
                        eof = true;
                    } else {
                        currentNode = resultNode;
                    }
                }
            }
        };
    }

    @Override
    protected T tryNext() {
        T result = null;
        if (currentNode != null) {
            boolean quit = true;
            while (quit) {
                switch (leftOrRight) {
                    case 1: /*from parent*/ {
                        outerObject = currentNode.changeOuterObject(outerObject, currentNode, 1);
                        if (currentNode.getRight() == null) {
                            result = currentNode.getObjectNode();
                            leftOrRight = 3;
                            size++;
                            quit = false;
                        } else {
                            currentNode = currentNode.getRight();
                            leftOrRight = 1;
                            level++;
                            if (maxLevel < level) maxLevel = level;
                        }
                        break;
                    }
                    case 2: /*from left*/ {
                        BinTreeNodeInterface<T> parent = currentNode.getParent();
                        if (parent != null) {
                            if (currentNode == rootNode) {
                                quit = false;
                            } else {
                                outerObject = currentNode.changeOuterObject(outerObject, currentNode, -1);
                                if (currentNode == parent.getRight()) {
                                    currentNode = parent;
                                    result = currentNode.getObjectNode();
                                    leftOrRight = 3;
                                    size++;
                                    level--;
                                    quit = false;
                                }
                                if (currentNode == parent.getLeft()) {
                                    leftOrRight = 2;
                                    currentNode = parent;
                                    level--;
                                }
                            }
                        } else {
                            quit = false;
                        }
                        break;
                    }
                    case 3/*from right*/: {
                        if (currentNode.getLeft() == null) {
                            leftOrRight = 2;
                        } else {
                            currentNode = currentNode.getLeft();
                            leftOrRight = 1;
                            level++;
                            if (maxLevel < level) maxLevel = level;
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void remove() {
        currentNode = binTree.removeNode(true, removeNode, currentNode);
        removeNode = null;
    }

}
