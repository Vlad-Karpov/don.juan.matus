package don.juan.matus.lib.collection.sorted.tree.bintree;

public class BinTreeDebugPassEvent<T extends Comparable<? extends T>> implements BinTreeCheckPassEvent<T> {

    @Override
    public void onPass(BinTreeIterator<T> leftIterator, BinTreeIterator<T> rightIterator, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
        System.out.println("Error: "
                + "\r\n Current node: " + currentNode
                + "\r\n Left node: " + currentNode.getLeft()
                + "\r\n Right node: " + currentNode.getRight()
                + "\r\n Previous node: " + previousNode
                + "\r\n Left iterator: " + leftIterator
                + "\r\n Right iterator: " + rightIterator);
    }

    @Override
    public void setErrorMessage(String errMsg) {

    }

    @Override
    public String getErrorMessage() {
        return null;
    }

}
