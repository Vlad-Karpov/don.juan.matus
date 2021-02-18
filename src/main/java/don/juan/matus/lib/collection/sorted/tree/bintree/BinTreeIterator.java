package don.juan.matus.lib.collection.sorted.tree.bintree;

/**
 * Iterator for binary tree.
 */
public class BinTreeIterator<T extends Comparable<T>> extends BinTreeIteratorBase<T> {

    public BinTreeIterator(BinTreeInterface<T> theBinTree, BinTreeNodeInterface<T> theRootNode) {
        super(theBinTree, theRootNode);
    }

    public BinTreeIterator(BinTreeInterface<T> theBinTree, BinTreeNodeInterface<T> theRootNode, T theObject) {
        super(theBinTree, theRootNode, theObject);
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
                        currentNode = resultNode;
                    } else {
                        eof = true;

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
                        if (currentNode.getLeft() == null) {
                            result = currentNode.getObjectNode();
                            leftOrRight = 2;
                            size++;
                            quit = false;
                        } else {
                            currentNode = currentNode.getLeft();
                            leftOrRight = 1;
                            level++;
                            if (maxLevel < level) maxLevel = level;
                        }
                        break;
                    }
                    case 2: /*from left*/ {
                        if (currentNode.getRight() == null) {
                            leftOrRight = 3;
                        } else {
                            currentNode = currentNode.getRight();
                            leftOrRight = 1;
                            level++;
                            if (maxLevel < level) maxLevel = level;
                        }
                        break;
                    }
                    case 3/*from right*/: {
                        BinTreeNodeInterface<T> parent = currentNode.getParent();
                        if (parent != null) {
                            if (currentNode == rootNode) {
                                quit = false;
                            } else {
                                outerObject = currentNode.changeOuterObject(outerObject, currentNode, -1);
                                if (currentNode == parent.getLeft()) {
                                    currentNode = parent;
                                    result = currentNode.getObjectNode();
                                    leftOrRight = 2;
                                    size++;
                                    level--;
                                    quit = false;
                                }
                                if (currentNode == parent.getRight()) {
                                    leftOrRight = 3;
                                    currentNode = parent;
                                    level--;
                                }
                            }
                        } else {
                            quit = false;
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
        currentNode = binTree.removeNode(false, removeNode, currentNode);
        removeNode = null;
    }

}
